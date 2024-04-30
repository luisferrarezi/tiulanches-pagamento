package br.com.fiap.tiulanches.utils.pagamento;

import br.com.fiap.tiulanches.adapter.repository.pagamento.PagamentoDto;
import br.com.fiap.tiulanches.core.entity.pagamento.Pagamento;
import br.com.fiap.tiulanches.core.enums.Pago;

public class PagamentoPadrao {    

    public Pagamento createPagamento(){
        return new Pagamento((String) PagamentoEnum.ID_PAGAMENTO.getValor(), 
                             (Long) PagamentoEnum.ID_PEDIDO.getValor(), 
                             (String) PagamentoEnum.ID_MERCADO_LIVRE.getValor(),
                             (String) PagamentoEnum.TICKET_URL.getValor(),
                             Pago.NAO);
    }

    public PagamentoDto createPagamentoDto(){
        return new PagamentoDto((String) PagamentoEnum.ID_PAGAMENTO.getValor(), 
                                (Long) PagamentoEnum.ID_PEDIDO.getValor(), 
                                (String) PagamentoEnum.ID_MERCADO_LIVRE.getValor(),
                                (String) PagamentoEnum.TICKET_URL.getValor(),
                                Pago.NAO);
    }
}
