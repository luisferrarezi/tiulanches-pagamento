package br.com.fiap.tiulanches.adapter.message.pagamento;

import br.com.fiap.tiulanches.core.enums.Pago;

public interface PagamentoMessage {
    public void enviaMensagem(long idPedido, Pago pago);
}
