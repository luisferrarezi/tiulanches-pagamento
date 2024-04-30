package br.com.fiap.tiulanches.core.entity.pedido;

import java.math.BigDecimal;

import br.com.fiap.tiulanches.core.entity.produto.Produto;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemPedido {
	
	private long idItem;
	private long idPedido;
	private Produto produto;
	private BigDecimal precoUnitario;
	private int quantidade;
}
