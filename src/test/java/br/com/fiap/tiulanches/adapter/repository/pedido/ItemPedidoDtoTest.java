package br.com.fiap.tiulanches.adapter.repository.pedido;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.fiap.tiulanches.adapter.repository.produto.ProdutoDto;
import br.com.fiap.tiulanches.core.entity.pedido.ItemPedido;
import br.com.fiap.tiulanches.utils.pedido.ItemPedidoEnum;
import br.com.fiap.tiulanches.utils.pedido.ItemPedidoPadrao;
import br.com.fiap.tiulanches.utils.produto.ProdutoPadrao;

class ItemPedidoDtoTest {

    private ItemPedidoDto itemPedidoDto;
    private ItemPedidoPadrao itemPedidoPadrao;        
    private ProdutoDto produtoDto;

    private final Long idItem = (Long) ItemPedidoEnum.ID_ITEM.getValor();
    private final Long idPedido = (Long) ItemPedidoEnum.ID_PEDIDO.getValor();
    private final BigDecimal precoUnitario = (BigDecimal) ItemPedidoEnum.PRECO_UNITARIO.getValor();
    private final Integer quantidade = (Integer) ItemPedidoEnum.QUANTIDADE.getValor();

    @BeforeEach
    void beforeEach(){
        ProdutoPadrao produtoPadrao;
        itemPedidoPadrao = new ItemPedidoPadrao();

        produtoPadrao = new ProdutoPadrao();
        produtoDto = produtoPadrao.createProdutoDto();
    }

    @Test
    void constructorAllArgumentsTest(){
        itemPedidoDto = itemPedidoPadrao.createItemPedidoDto();
        
        assertEquals(idItem, itemPedidoDto.idItem());
        assertEquals(idPedido, itemPedidoDto.idPedido());
        assertEquals(produtoDto, itemPedidoDto.produto());
        assertEquals(precoUnitario, itemPedidoDto.precoUnitario());
        assertEquals(quantidade, itemPedidoDto.quantidade());
    }

    @Test
    void constructorByProdutoTest(){
        ItemPedido itemPedido = itemPedidoPadrao.createItemPedido();
        itemPedidoDto = new ItemPedidoDto(itemPedido);

        assertEquals(idItem, itemPedidoDto.idItem());
        assertEquals(idPedido, itemPedidoDto.idPedido());
        assertEquals(produtoDto, itemPedidoDto.produto());
        assertEquals(precoUnitario, itemPedidoDto.precoUnitario());
        assertEquals(quantidade, itemPedidoDto.quantidade());
    }        
}
