package br.com.fiap.tiulanches.adapter.message.pagamento;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.fiap.tiulanches.adapter.message.EventoEnum;

class PagamentoEventTest {

    private PagamentoEvent pagamentoEvent;
    
    @BeforeEach
    void beforeEach(){
        this.pagamentoEvent = new PagamentoEvent(EventoEnum.CREATE, null);
    }

    @Test
    void createTest(){
        pagamentoEvent.setPagamentoDto(null);
        pagamentoEvent.setEvento(EventoEnum.CREATE);

        assertEquals(EventoEnum.CREATE, pagamentoEvent.getEvento());
        assertEquals(null, pagamentoEvent.getPagamentoDto());
    }

    @Test
    void constructorAllArgumentsTest(){
        assertEquals(EventoEnum.CREATE, pagamentoEvent.getEvento());
    }    

    @Test
    void noArgumentsTest(){
        pagamentoEvent = new PagamentoEvent();

        assertEquals(null, pagamentoEvent.getEvento());
    }        

    @Test
    void equalsTest(){
        PagamentoEvent pagamentoEvent2 = new PagamentoEvent(EventoEnum.CREATE, null);

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
