package br.com.fiap.tiulanches.adapter.repository.pedido;

import java.math.BigDecimal;

import br.com.fiap.tiulanches.adapter.repository.produto.ProdutoDto;
import br.com.fiap.tiulanches.core.entity.pedido.ItemPedido;

public record ItemPedidoDto(long idItem,
							long idPedido,
							ProdutoDto produto,
							BigDecimal precoUnitario,
							int quantidade) {
	public ItemPedidoDto(ItemPedido itemPedido) {
		this(itemPedido.getIdItem(), 
			 itemPedido.getIdPedido(), 
			 new ProdutoDto(itemPedido.getProduto()), 
			 itemPedido.getPrecoUnitario(), 
			 itemPedido.getQuantidade());
	}
}
