package br.com.fiap.tiulanches.core.entity.cliente;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {
	private String cpf;
	private String nome;
	private String email;
}
