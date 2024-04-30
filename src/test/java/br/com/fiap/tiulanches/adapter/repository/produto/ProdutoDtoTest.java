package br.com.fiap.tiulanches.adapter.repository.produto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.fiap.tiulanches.core.entity.produto.Produto;
import br.com.fiap.tiulanches.utils.produto.ProdutoEnum;
import br.com.fiap.tiulanches.utils.produto.ProdutoPadrao;

class ProdutoDtoTest {

    private ProdutoDto produtoDto;
    private ProdutoPadrao produtoPadrao;

    private final Long idProdutoPadrao = (Long) ProdutoEnum.ID_PRODUTO.getValor();
    private final String nomePadrao = (String) ProdutoEnum.NOME.getValor();
    private final BigDecimal precoPadrao = (BigDecimal) ProdutoEnum.PRECO.getValor();
    
    @BeforeEach
    void beforeEach(){
        produtoPadrao = new ProdutoPadrao();
    }    

    @Test
    void constructorAllArgumentsTest(){
        produtoDto = produtoPadrao.createProdutoDto();
        
        assertEquals(idProdutoPadrao, produtoDto.idProduto());
        assertEquals(nomePadrao, produtoDto.nome());
        assertEquals(precoPadrao, produtoDto.preco());
    }

    @Test
    void constructorByProdutoTest(){
        Produto produto = produtoPadrao.createProduto();
        produtoDto = new ProdutoDto(produto);

        assertEquals(idProdutoPadrao, produtoDto.idProduto());
        assertEquals(nomePadrao, produtoDto.nome());
        assertEquals(precoPadrao, produtoDto.preco());
    }    
}
