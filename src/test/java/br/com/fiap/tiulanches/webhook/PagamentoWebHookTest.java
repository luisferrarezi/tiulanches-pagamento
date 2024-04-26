package br.com.fiap.tiulanches.webhook;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import br.com.fiap.tiulanches.adapter.controller.PagamentoExternoController;
import br.com.fiap.tiulanches.core.exception.ExceptionErros;
import br.com.fiap.tiulanches.utils.pagamento.PagamentoEnum;

class PagamentoWebHookTest {
    private MockMvc mockMvc;

    @Mock
    PagamentoExternoController controller;

    AutoCloseable openMocks;

    @BeforeEach
    void beforeEach() {
        openMocks = MockitoAnnotations.openMocks(this);
        PagamentoWebHook pagamentoWebHook = new PagamentoWebHook(controller);
      
        mockMvc = MockMvcBuilders.standaloneSetup(pagamentoWebHook)
            .setControllerAdvice(new ExceptionErros())
            .addFilter((request, response, chain) -> {
                    response.setCharacterEncoding("UTF-8");
                    chain.doFilter(request, response);
                }, "/*")
            .build();
    }  

    @AfterEach
    void afterEach() throws Exception {
      openMocks.close();
    }

    @Test
    void testProcessa() throws Exception {
        mockMvc.perform(post("/pagamentowb/processa")
                    .contentType(MediaType.APPLICATION_JSON)
                    .param("data.id", PagamentoEnum.ID_MERCADO_LIVRE.getValor().toString())
                    .param("type", "payment"))
                .andExpect(status().isBadRequest());
    }
}
