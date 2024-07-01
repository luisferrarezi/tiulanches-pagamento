package br.com.fiap.tiulanches.adapter.message.pagamento;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.fiap.tiulanches.core.enums.Pago;

class PagamentoEventTest {

    private PagamentoEvent pagamentoEvent;
    
    @BeforeEach
    void beforeEach(){
        this.pagamentoEvent = new PagamentoEvent(1, Pago.NAO);
    }

    @Test
    void createTest(){
        pagamentoEvent.setIdPedido(1);
        pagamentoEvent.setPago(Pago.NAO);

        assertEquals(1, pagamentoEvent.getIdPedido());
        assertEquals(Pago.NAO, pagamentoEvent.getPago());
    }

    @Test
    void constructorAllArgumentsTest(){
        assertEquals(1, pagamentoEvent.getIdPedido());
        assertEquals(Pago.NAO, pagamentoEvent.getPago());
    }    

    @Test
    void noArgumentsTest(){
        pagamentoEvent = new PagamentoEvent();

        assertEquals(null, pagamentoEvent.getPago());
    }        

    @Test
    void equalsTest(){
        PagamentoEvent pagamentoEvent2 = new PagamentoEvent(1, Pago.NAO);

        assertDoesNotThrow(()->pagamentoEvent.equals(pagamentoEvent2));
    }

    @Test
    void hashCodeTest(){
        assertDoesNotThrow(()->pagamentoEvent.hashCode());
    }

    @Test
    void toStringTest(){
        assertDoesNotThrow(()->pagamentoEvent.toString());
    }
}
