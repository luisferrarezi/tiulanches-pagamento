package br.com.fiap.tiulanches.core.entitie.pagamento;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import br.com.fiap.tiulanches.adapter.repository.pagamento.PagamentoDto;
import br.com.fiap.tiulanches.core.enums.Pago;
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
	private String idPagamento;
	private long idPedido;
	private String idMercadoPago;	
	private String ticketUrl;
	private Pago pago;
	
	public void criar(PagamentoDto pagamento) {
		this.idPagamento = pagamento.idPagamento();
		this.idPedido = pagamento.idPedido();
		this.idMercadoPago = pagamento.idMercadoPago();
		this.ticketUrl = pagamento.ticketUrl();
		this.pago = pagamento.pago();
	}

	public void registrar(PagamentoDto pagamento) {
		this.idMercadoPago = pagamento.idMercadoPago();
		this.pago = pagamento.pago();
	}	
}
