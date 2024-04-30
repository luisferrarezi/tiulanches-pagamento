package br.com.fiap.tiulanches.adapter.repository.cliente;

import br.com.fiap.tiulanches.core.entity.cliente.Cliente;

public record ClienteDto(String cpf, 
				 		 String nome, 
				  		 String email) {
	
	public ClienteDto(Cliente cliente) {
		this(cliente.getCpf(), cliente.getNome(), cliente.getEmail());
	}
}
