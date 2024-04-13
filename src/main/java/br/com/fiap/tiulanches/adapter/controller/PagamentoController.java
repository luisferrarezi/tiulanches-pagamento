package br.com.fiap.tiulanches.adapter.controller;

import br.com.fiap.tiulanches.adapter.repository.pagamento.PagamentoDto;

public interface PagamentoController {
	public void cria(PagamentoDto dto);
	public void registra(PagamentoDto dto);
	public PagamentoDto consulta(String idPagamento);
}
