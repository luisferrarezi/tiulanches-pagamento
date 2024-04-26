package br.com.fiap.tiulanches.adapter.repository.pagamento;

import org.junit.jupiter.api.Test;

import br.com.fiap.tiulanches.core.enums.Pago;
import br.com.fiap.tiulanches.utils.pagamento.PagamentoEnum;
import br.com.fiap.tiulanches.utils.pagamento.PagamentoPadrao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;

class PagamentoDtoTest {
    private PagamentoPadrao pagamentoPadrao;
    private PagamentoDto pagamentoDto;
    
    @BeforeEach
    void beforeEach(){
        pagamentoPadrao = new PagamentoPadrao();
    }

@Test
    void constructorAllArgumentsTest(){
        pagamentoDto = pagamentoPadrao.createPagamentoDto();
        assertEquals(PagamentoEnum.ID_PAGAMENTO.getValor(), pagamentoDto.idPagamento());
        assertEquals(PagamentoEnum.ID_PEDIDO.getValor(), pagamentoDto.idPedido());
        assertEquals(PagamentoEnum.ID_MERCADO_LIVRE.getValor(), pagamentoDto.idMercadoPago());
        assertEquals(PagamentoEnum.TICKET_URL.getValor(), pagamentoDto.ticketUrl());
        assertEquals(Pago.NAO, pagamentoDto.pago());
    }

    @Test
    void constructorByClienteTest(){
        pagamentoDto = new PagamentoDto(pagamentoPadrao.createPagamento());
        assertEquals(PagamentoEnum.ID_PAGAMENTO.getValor(), pagamentoDto.idPagamento());
        assertEquals(PagamentoEnum.ID_PEDIDO.getValor(), pagamentoDto.idPedido());
        assertEquals(PagamentoEnum.ID_MERCADO_LIVRE.getValor(), pagamentoDto.idMercadoPago());
        assertEquals(PagamentoEnum.TICKET_URL.getValor(), pagamentoDto.ticketUrl());
        assertEquals(Pago.NAO, pagamentoDto.pago());
    }    
}
