package br.com.fiap.tiulanches.core.service;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

import br.com.fiap.tiulanches.adapter.message.pagamento.PagamentoMessage;
import br.com.fiap.tiulanches.adapter.repository.pagamento.PagamentoDto;
import br.com.fiap.tiulanches.adapter.repository.pagamento.PagamentoRepository;
import br.com.fiap.tiulanches.core.entity.pagamento.Pagamento;
import br.com.fiap.tiulanches.core.enums.Pago;
import br.com.fiap.tiulanches.core.exception.BusinessException;
import br.com.fiap.tiulanches.utils.pagamento.PagamentoEnum;
import br.com.fiap.tiulanches.utils.pagamento.PagamentoPadrao;

class PagamentoServiceTest {

    @Mock
    private PagamentoRepository repository;    

    @Mock
    private PagamentoMessage message;

    private PagamentoService service;
    private AutoCloseable openMocks;
    private Optional<Pagamento> pagamento;
    private PagamentoPadrao pagamentoPadrao;
    private BusinessException exception;

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
    void testConsulta() {

        when(repository.findById(anyString())).thenReturn(pagamento);
        PagamentoDto dto = service.consulta((String) PagamentoEnum.ID_PAGAMENTO.getValor());

        assertEquals(PagamentoEnum.ID_PAGAMENTO.getValor(), dto.idPagamento());
        assertEquals(PagamentoEnum.ID_PEDIDO.getValor(), dto.idPedido());
        assertEquals(PagamentoEnum.ID_MERCADO_LIVRE.getValor(), dto.idMercadoPago());
        assertEquals(PagamentoEnum.TICKET_URL.getValor(), dto.ticketUrl());
        assertEquals(Pago.NAO, dto.pago());

        Optional.of(new Pagamento());
        when(repository.findById(anyString())).thenReturn(Optional.empty());
        exception = assertThrows(BusinessException.class, ()-> service.consulta((String) PagamentoEnum.ID_PAGAMENTO.getValor()));
        assertEquals("Pagamento não encontrado!", exception.getMessage());
        assertEquals(HttpStatus.NOT_FOUND, exception.getHttpStatus());
    }

    @Test
    void testCria() {
        when(repository.save(any(Pagamento.class))).thenReturn(pagamentoPadrao.createPagamento());
        assertDoesNotThrow(()-> service.cria(pagamentoPadrao.createPagamentoDto()));
        
        doThrow(new RuntimeException("erro")).when(repository).save(any(Pagamento.class));
        exception = assertThrows(BusinessException.class, ()-> service.cria(pagamentoPadrao.createPagamentoDto()));
        assertEquals("Falha ao alterar pagamento!", exception.getMessage());
        assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
    }

    @Test
    void testRegistra() {

        Optional.of(new Pagamento());
        when(repository.findById(anyString())).thenReturn(Optional.empty());
        exception = assertThrows(BusinessException.class, ()-> service.registra(pagamentoPadrao.createPagamentoDto()));
        assertEquals("Pagamento não encontrado!", exception.getMessage());
        assertEquals(HttpStatus.NOT_FOUND, exception.getHttpStatus());

        when(repository.findById(anyString())).thenReturn(pagamento);
        doThrow(new RuntimeException("erro")).when(repository).save(any(Pagamento.class));
        exception = assertThrows(BusinessException.class, ()-> service.registra(pagamentoPadrao.createPagamentoDto()));
        assertEquals("Falha ao alterar pagamento!", exception.getMessage());
        assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());

        when(repository.findById(anyString())).thenReturn(pagamento);
        when(repository.save(any(Pagamento.class))).thenReturn(pagamentoPadrao.createPagamento());
        doNothing().when(message).enviaMensagem(anyInt(), any(Pago.class));
        assertDoesNotThrow(()-> service.registra(pagamentoPadrao.createPagamentoDto()));
    }
}
