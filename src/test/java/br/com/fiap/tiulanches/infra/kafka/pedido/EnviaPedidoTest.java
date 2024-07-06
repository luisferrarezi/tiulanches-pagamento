package br.com.fiap.tiulanches.infra.kafka.pedido;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.kafka.core.KafkaTemplate;

import br.com.fiap.tiulanches.adapter.message.pedido.PedidoEvent;
import br.com.fiap.tiulanches.core.enums.Pago;
import br.com.fiap.tiulanches.infra.kafka.pagamento.EnviaPagamento;

class EnviaPedidoTest {

    private EnviaPagamento enviaPagamento;    

    @Mock
    KafkaTemplate<String, Object> kafka;

    @BeforeEach
    @SuppressWarnings("unchecked")    
    void beforeEach(){
        kafka = Mockito.mock(KafkaTemplate.class);
        enviaPagamento = new EnviaPagamento(kafka);        
    }

    @Test
    void constructorEnviaPedidoTest(){
        enviaPagamento = new EnviaPagamento(kafka);

        assertNotEquals(null, enviaPagamento);
    }

    @Test
    void enviaMensagemTest(){
        when(kafka.send(anyString(), any(PedidoEvent.class))).thenReturn(null);
        assertDoesNotThrow(()->enviaPagamento.enviaMensagem(1, Pago.NAO));
    }    
}
