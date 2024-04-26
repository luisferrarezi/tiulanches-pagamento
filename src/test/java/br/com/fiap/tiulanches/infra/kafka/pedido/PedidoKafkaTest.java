package br.com.fiap.tiulanches.infra.kafka.pedido;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.fiap.tiulanches.adapter.controller.PreferenciaExternoController;
import br.com.fiap.tiulanches.adapter.message.EventoEnum;
import br.com.fiap.tiulanches.adapter.message.pedido.PedidoEvent;
import br.com.fiap.tiulanches.adapter.repository.pedido.PedidoDto;
import br.com.fiap.tiulanches.utils.pedido.PedidoPadrao;

class PedidoKafkaTest {

    @Mock
    private PreferenciaExternoController controller;    
    private AutoCloseable openMocks;

    @BeforeEach
    void beforeEach(){        
        openMocks = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void afterEach() throws Exception {
        openMocks.close();
    }

    @Test
    void testProcessaMensagem() {
        PedidoEvent pedidoEvent = new PedidoEvent();
        pedidoEvent.setEvento(EventoEnum.CREATE);
        pedidoEvent.setPedidoDto(new PedidoPadrao().createPedidoDto());

        PedidoKafka pedidoKafka;
        pedidoKafka = new PedidoKafka(controller);

        doNothing().when(controller).criar(any(PedidoDto.class));
        assertDoesNotThrow(()->pedidoKafka.processaMensagem(pedidoEvent));
    }
}
