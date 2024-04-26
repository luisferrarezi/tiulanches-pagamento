package br.com.fiap.tiulanches.core.entitie.pagamento;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.fiap.tiulanches.core.enums.Pago;
import br.com.fiap.tiulanches.utils.pagamento.PagamentoEnum;
import br.com.fiap.tiulanches.utils.pagamento.PagamentoPadrao;

class PagamentoTest {

    private Pagamento pagamento;
    private PagamentoPadrao pagamentoPadrao;

    @BeforeEach
    void beforeEach(){
        pagamento = new Pagamento();
        pagamentoPadrao = new PagamentoPadrao();
    }

    @Test
    void testCriar(){
        pagamento.criar(pagamentoPadrao.createPagamentoDto());

        assertEquals(PagamentoEnum.ID_PAGAMENTO.getValor(), pagamento.getIdPagamento());
        assertEquals(PagamentoEnum.ID_PEDIDO.getValor(), pagamento.getIdPedido());
        assertEquals(PagamentoEnum.ID_MERCADO_LIVRE.getValor(), pagamento.getIdMercadoPago());
        assertEquals(PagamentoEnum.TICKET_URL.getValor(), pagamento.getTicketUrl());
        assertEquals(Pago.NAO, pagamento.getPago());
    }

    @Test
    void testRegistrar(){
        pagamento.registrar(pagamentoPadrao.createPagamentoDto());    

        assertEquals(null, pagamento.getIdPagamento());
        assertEquals(0, pagamento.getIdPedido());
        assertEquals(PagamentoEnum.ID_MERCADO_LIVRE.getValor(), pagamento.getIdMercadoPago());
        assertEquals(null, pagamento.getTicketUrl());
        assertEquals(Pago.NAO, pagamento.getPago());
    }    
}
