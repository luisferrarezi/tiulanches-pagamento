package br.com.fiap.tiulanches.infra.mercadopago;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

import br.com.fiap.tiulanches.adapter.controller.PagamentoController;
import br.com.fiap.tiulanches.core.exception.BusinessException;
import br.com.fiap.tiulanches.utils.pedido.PedidoPadrao;
import uk.org.webcompere.systemstubs.environment.EnvironmentVariables;
import uk.org.webcompere.systemstubs.jupiter.SystemStub;
import uk.org.webcompere.systemstubs.jupiter.SystemStubsExtension;

@ExtendWith(SystemStubsExtension.class)
class PreferenciaMPTest {

    private static final String TOKEN = "TEST-4953705567002684-123456-4bz6pugwjuhzuj72jhef3g9bv03jxqfp-87654321";

    @Mock
	private PagamentoController controller;

    @SystemStub
    private EnvironmentVariables environmentVariables;

    private AutoCloseable openMocks;
    private PreferenciaMP preferenciaMP;    
    private PedidoPadrao pedidoPadrao;    
    
    @BeforeEach
    void beforeEach(){        
        preferenciaMP = new PreferenciaMP(controller);
        openMocks = MockitoAnnotations.openMocks(this);
        pedidoPadrao = new PedidoPadrao();        
    }

    @AfterEach
    void afterEach() throws Exception {
        openMocks.close();
    }        

    @Test
    void testCriar() {
        environmentVariables.set("ACCESS_TOKEN_MP", TOKEN);

        BusinessException exception = assertThrows(BusinessException.class, ()-> preferenciaMP.criar(pedidoPadrao.createPedidoDto()));
        assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());        
    }
}
