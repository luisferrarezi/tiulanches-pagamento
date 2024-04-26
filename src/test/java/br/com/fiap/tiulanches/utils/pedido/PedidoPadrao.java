package br.com.fiap.tiulanches.utils.pedido;

import java.util.ArrayList;
import java.util.List;

import br.com.fiap.tiulanches.adapter.repository.pedido.ItemPedidoDto;
import br.com.fiap.tiulanches.adapter.repository.pedido.PedidoDto;
import br.com.fiap.tiulanches.core.entitie.pedido.ItemPedido;
import br.com.fiap.tiulanches.core.entitie.pedido.Pedido;
import br.com.fiap.tiulanches.utils.cliente.ClientePadrao;

public class PedidoPadrao {

    private ItemPedidoPadrao itemPedidoPadrao;
    private ClientePadrao clientePadrao;
    private List<ItemPedido> listItemPedido = new ArrayList<>();
    private List<ItemPedidoDto> listItemPedidoDto = new ArrayList<>();

    public PedidoPadrao(){
        itemPedidoPadrao = new ItemPedidoPadrao();

        
        listItemPedido.add(itemPedidoPadrao.createItemPedido());


        listItemPedidoDto.add(itemPedidoPadrao.createItemPedidoDto());

        clientePadrao = new ClientePadrao();
    }

    public Pedido createPedido(){
        return new Pedido((Long) PedidoEnum.ID_PEDIDO.getValor(),
                           clientePadrao.createClient(),
                           listItemPedido);
    }

    public PedidoDto createPedidoDto(){
        return new PedidoDto((Long) PedidoEnum.ID_PEDIDO.getValor(),
                              clientePadrao.createClientDto(),
                              listItemPedidoDto);
    }    
}
