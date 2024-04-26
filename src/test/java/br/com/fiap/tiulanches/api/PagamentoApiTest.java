package br.com.fiap.tiulanches.api;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import br.com.fiap.tiulanches.adapter.controller.PagamentoController;
import br.com.fiap.tiulanches.adapter.repository.pagamento.PagamentoDto;
import br.com.fiap.tiulanches.core.exception.ExceptionErros;
import br.com.fiap.tiulanches.utils.pagamento.PagamentoPadrao;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class PagamentoApiTest {
    private MockMvc mockMvc;    
    private PagamentoPadrao pagamentoPadrao;

    @Mock
    PagamentoController controller;

    AutoCloseable openMocks;

    @BeforeEach
    void beforeEach() {
        pagamentoPadrao = new PagamentoPadrao();

        openMocks = MockitoAnnotations.openMocks(this);
        PagamentoApi api = new PagamentoApi(controller);
      
        mockMvc = MockMvcBuilders.standaloneSetup(api)
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
    void testConsultaPagamento() throws Exception {
        PagamentoDto dto = pagamentoPadrao.createPagamentoDto();

        when(controller.consulta(anyString())).thenReturn(dto);
        mockMvc.perform(get("/pagamento/status/{id}", dto.idPagamento())
                    .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idPagamento").value(dto.idPagamento()))
                .andExpect(jsonPath("$.idPedido").value(dto.idPedido()))
                .andExpect(jsonPath("$.idMercadoPago").value(dto.idMercadoPago()))
                .andExpect(jsonPath("$.ticketUrl").value(dto.ticketUrl()))
                .andExpect(jsonPath("$.pago").value("NAO"));
    }
}
