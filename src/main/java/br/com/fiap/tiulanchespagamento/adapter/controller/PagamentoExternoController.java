package br.com.fiap.tiulanchespagamento.adapter.controller;

public interface PagamentoExternoController {
	public boolean processar(long idPagamento, String type);
}
