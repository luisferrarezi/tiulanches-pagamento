package br.com.fiap.tiulanches.adapter.message.pagamento;

import br.com.fiap.tiulanches.adapter.message.EventoEnum;
import br.com.fiap.tiulanches.adapter.repository.pagamento.PagamentoDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PagamentoEvent {

    private EventoEnum evento;
    private PagamentoDto pagamentoDto;
}
