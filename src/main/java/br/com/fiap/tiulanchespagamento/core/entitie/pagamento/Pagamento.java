package br.com.fiap.tiulanchespagamento.core.entitie.pagamento;

import java.math.BigDecimal;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "pagamentos")
public class Pagamento {		

	@Id
	private UUID idPagamento;
	private String idMercadoPago;	
	private String ticketUrl;
	private BigDecimal valorAPagar;
	private BigDecimal valorPago;
	
	public void criar() {
		this.idPagamento = UUID.randomUUID();
	}

/*	
	public void registrar(PagamentoDto pagamento) {
		this.idPagamento = pagamento.idPagamento();
		validaIdMercadoPago(pagamento.idMercadoPago());
		validaTicketUrl(pagamento.ticketUrl());
		validaPago(pagamento.pago());
	}

	private void validaIdMercadoPago(String idMercadoPago) {
		if (idMercadoPago != null) {
			this.idMercadoPago = idMercadoPago;
		}				
	}
	
	private void validaTicketUrl(String ticketUrl) {
		if (ticketUrl != null) {
			this.ticketUrl = ticketUrl;
		}				
	}
*/	
}
