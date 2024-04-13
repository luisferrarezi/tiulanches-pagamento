package br.com.fiap.tiulanches.adapter.message.pagamento;

import br.com.fiap.tiulanches.adapter.message.EventoEnum;
import br.com.fiap.tiulanches.adapter.repository.pagamento.PagamentoDto;

public interface PagamentoMessage {
    public void enviaMensagem(EventoEnum evento, PagamentoDto pagamento);
}
