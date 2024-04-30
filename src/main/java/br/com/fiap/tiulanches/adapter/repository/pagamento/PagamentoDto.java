package br.com.fiap.tiulanches.adapter.repository.pagamento;

import br.com.fiap.tiulanches.core.entity.pagamento.Pagamento;
import br.com.fiap.tiulanches.core.enums.Pago;

public record PagamentoDto(String idPagamento,
						   long idPedido,
						   String idMercadoPago,
						   String ticketUrl,
						   Pago pago) {
	
	public PagamentoDto(Pagamento pagamento) {
		this(pagamento.getIdPagamento(),
			 pagamento.getIdPedido(),	 
			 pagamento.getIdMercadoPago(), 
			 pagamento.getTicketUrl(),
			 pagamento.getPago());
	}
}
