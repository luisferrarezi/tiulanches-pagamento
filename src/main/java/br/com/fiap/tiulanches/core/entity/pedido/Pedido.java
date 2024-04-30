package br.com.fiap.tiulanches.core.entity.pedido;

import java.util.List;

import br.com.fiap.tiulanches.core.entity.cliente.Cliente;

import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Pedido {
	private long idPedido;
	private Cliente cliente;
	private List<ItemPedido> listItemPedido = new ArrayList<>();	
}
