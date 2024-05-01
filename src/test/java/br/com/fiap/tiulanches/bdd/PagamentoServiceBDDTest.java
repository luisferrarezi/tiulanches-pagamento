package br.com.fiap.tiulanches.bdd;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.*;

import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.fiap.tiulanches.adapter.message.EventoEnum;
import br.com.fiap.tiulanches.adapter.message.pedido.PedidoMessage;
import br.com.fiap.tiulanches.adapter.repository.pagamento.PagamentoRepository;
import br.com.fiap.tiulanches.adapter.repository.pedido.PedidoDto;
import br.com.fiap.tiulanches.core.entity.pagamento.Pagamento;

import br.com.fiap.tiulanches.core.service.PagamentoService;
import br.com.fiap.tiulanches.utils.pagamento.PagamentoPadrao;

class PagamentoServiceBDDTest {

    @Mock
    private PagamentoRepository repository;    

    @Mock
    private PedidoMessage message;

    private PagamentoService service;
    private AutoCloseable openMocks;
    private Optional<Pagamento> pagamento;
    private PagamentoPadrao pagamentoPadrao;

    @BeforeEach
    void beforeEach(){        
        openMocks = MockitoAnnotations.openMocks(this);

        service = new PagamentoService(repository, message);

        pagamentoPadrao = new PagamentoPadrao();

        pagamento = Optional.ofNullable(pagamentoPadrao.createPagamento());
    }

    @AfterEach
    void afterEach() throws Exception {
        openMocks.close();
    }

    @Test
    void testRegistra() {
        given(repository.findById(anyString())).willReturn(pagamento);
        given(repository.save(any(Pagamento.class))).willReturn(pagamentoPadrao.createPagamento());
        doNothing().when(message).enviaMensagem(any(EventoEnum.class), any(PedidoDto.class));
        assertDoesNotThrow(()-> service.registra(pagamentoPadrao.createPagamentoDto()));
    }
}
