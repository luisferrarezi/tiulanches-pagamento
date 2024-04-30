package br.com.fiap.tiulanches.utils.produto;

import java.math.BigDecimal;

import br.com.fiap.tiulanches.adapter.repository.produto.ProdutoDto;
import br.com.fiap.tiulanches.core.entity.produto.Produto;


public class ProdutoPadrao {

    public Produto createProduto(){
        return new Produto((Long) ProdutoEnum.ID_PRODUTO.getValor(), 
                           (String) ProdutoEnum.NOME.getValor(),
                           (BigDecimal) ProdutoEnum.PRECO.getValor());
    }

    public ProdutoDto createProdutoDto(){
        return new ProdutoDto((Long) ProdutoEnum.ID_PRODUTO.getValor(),
                              (String) ProdutoEnum.NOME.getValor(), 
                              (BigDecimal) ProdutoEnum.PRECO.getValor());
    }    
}
