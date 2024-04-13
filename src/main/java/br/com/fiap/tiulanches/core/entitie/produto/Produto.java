package br.com.fiap.tiulanches.core.entitie.produto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Produto {
	private long idProduto;
	private String nome;
	private BigDecimal preco;
}
