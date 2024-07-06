package br.com.fiap.tiulanches.infra.mercadopago;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

import br.com.fiap.tiulanches.adapter.controller.PagamentoController;
import br.com.fiap.tiulanches.adapter.message.pagamento.PagamentoMessage;
import br.com.fiap.tiulanches.core.exception.BusinessException;
import uk.org.webcompere.systemstubs.environment.EnvironmentVariables;
import uk.org.webcompere.systemstubs.jupiter.SystemStub;
import uk.org.webcompere.systemstubs.jupiter.SystemStubsExtension;

@ExtendWith(SystemStubsExtension.class)
class PagamentoMPTest {

	private static final String PAYMENT = "payment";
    private static final String TOKEN = "TEST-4953705567002684-123456-4bz6pugwjuhzuj72jhef3g9bv03jxqfp-87654321";
    private static final long idPagamento = 10l;
	
    @Mock
	private PagamentoController controller;

    @Mock
	private PagamentoMessage pagamentoMessage;

    @SystemStub
    private EnvironmentVariables environmentVariables;

    private AutoCloseable openMocks;
    private PagamentoMP pagamentoMP;    
    
    @BeforeEach
    void beforeEach(){        
        pagamentoMP = new PagamentoMP(controller, pagamentoMessage);
        openMocks = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void afterEach() throws Exception {
        openMocks.close();
    }    

    @Test
    void testProcessarPaymentFalse() {        
        assertFalse(pagamentoMP.processar(idPagamento, "teste"));
    }

    @Test
    void testProcessarException() {
        environmentVariables.set("ACCESS_TOKEN_MP", TOKEN);

        BusinessException exception = assertThrows(BusinessException.class, ()-> pagamentoMP.processar(idPagamento, PAYMENT));
        assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
    }    
}
