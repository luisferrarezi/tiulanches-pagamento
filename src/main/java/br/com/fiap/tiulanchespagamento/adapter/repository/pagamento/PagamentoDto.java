package br.com.fiap.tiulanchespagamento.adapter.repository.pagamento;

import java.math.BigDecimal;
import java.util.UUID;

import br.com.fiap.tiulanchespagamento.core.entitie.pagamento.Pagamento;

public record PagamentoDto(UUID idPagamento,
						   String idMercadoPago,
						   String ticketUrl,
						   BigDecimal valorAPagar,
						   BigDecimal valorPago) {
	
	public PagamentoDto(Pagamento pagamento) {
		this(pagamento.getIdPagamento(), 
			 pagamento.getIdMercadoPago(), 
			 pagamento.getTicketUrl(), 
			 pagamento.getValorAPagar(), 
			 pagamento.getValorPago());
	}
}
