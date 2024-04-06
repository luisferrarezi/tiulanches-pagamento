package br.com.fiap.tiulanchespagamento.adapter.controller;

import java.util.UUID;

import br.com.fiap.tiulanchespagamento.adapter.repository.pagamento.PagamentoDto;

public interface PagamentoController {
	public void registra(PagamentoDto dto);
	public void cria(PagamentoDto dto);
	public PagamentoDto consulta(UUID idPagamento);
}
