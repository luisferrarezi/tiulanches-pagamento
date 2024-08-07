package br.com.fiap.tiulanches.infra.mercadopago;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.payment.PaymentClient;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.payment.Payment;

import br.com.fiap.tiulanches.adapter.controller.PagamentoController;
import br.com.fiap.tiulanches.adapter.controller.PagamentoExternoController;
import br.com.fiap.tiulanches.adapter.message.pagamento.PagamentoMessage;
import br.com.fiap.tiulanches.adapter.repository.pagamento.PagamentoDto;
import br.com.fiap.tiulanches.core.enums.Pago;
import br.com.fiap.tiulanches.core.exception.BusinessException;

@Component
public class PagamentoMP implements PagamentoExternoController{

	private static final String PAYMENT = "payment";
	private static final String STATUS = "approved";
	
	private final PagamentoController controller;
	private final PagamentoMessage pagamentoMessage;
	
	public PagamentoMP(PagamentoController controller, PagamentoMessage pagamentoMessage){
		this.controller = controller;
		this.pagamentoMessage = pagamentoMessage;
	}
	
	@Override
	public boolean processar(long idPagamento, String type) {
		
		if (type.equalsIgnoreCase(PAYMENT)) {
			try {
				MercadoPagoConfig.setAccessToken(System.getenv("ACCESS_TOKEN_MP"));				
				
				PaymentClient client = new PaymentClient();
				Payment payment = client.get(idPagamento);
				
				if (payment.getStatus().equalsIgnoreCase(STATUS)) {
					controller.registra(new PagamentoDto(payment.getExternalReference(), 0, String.valueOf(idPagamento), null, Pago.SIM));
				} else {
					enviaMensagemFalhaPagamento(payment.getExternalReference());
				}
			} catch (MPApiException | MPException e) {
				StringBuilder erro = new StringBuilder();
		    	erro.append("Falha integração Mercado Pago: ");
		    	erro.append(e.getMessage());

		    	throw new BusinessException(e.getMessage(), HttpStatus.BAD_REQUEST, new String(erro));
			} 
		} else {
			return false;
		}
		
		return true;
	}

	private void enviaMensagemFalhaPagamento(String idPagamento){
		PagamentoDto dto = controller.consulta(idPagamento);
		pagamentoMessage.enviaMensagem(dto.idPedido(), Pago.NAO);
	}
}
