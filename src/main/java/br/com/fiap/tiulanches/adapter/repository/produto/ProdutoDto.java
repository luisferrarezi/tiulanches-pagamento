package br.com.fiap.tiulanches.adapter.repository.produto;

import java.math.BigDecimal;

import br.com.fiap.tiulanches.core.entity.produto.Produto;

public record ProdutoDto(long idProduto,
						 String nome,
						 BigDecimal preco) {
	public ProdutoDto(Produto produto) {
		this(produto.getIdProduto(), 
			 produto.getNome(),
			 produto.getPreco());
	}	
}
