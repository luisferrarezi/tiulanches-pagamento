package br.com.fiap.tiulanches.adapter.repository.pedido;

import java.util.List;
import java.util.stream.Collectors;

import br.com.fiap.tiulanches.adapter.repository.cliente.ClienteDto;
import br.com.fiap.tiulanches.core.entitie.pedido.Pedido;

public record PedidoDto (long idPedido,
						 ClienteDto cliente,
						 List<ItemPedidoDto> listItemPedido){	
	public PedidoDto(Pedido pedido) {
		this(pedido.getIdPedido(), 
			 pedido.getCliente()!= null ? new ClienteDto(pedido.getCliente()) : null, 
			 pedido.getListItemPedido().stream().map(ItemPedidoDto::new).collect(Collectors.toList()));		
	}	
}
