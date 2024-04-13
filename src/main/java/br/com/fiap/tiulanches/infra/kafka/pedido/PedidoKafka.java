package br.com.fiap.tiulanches.infra.kafka.pedido;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import br.com.fiap.tiulanches.adapter.controller.PreferenciaExternoController;
import br.com.fiap.tiulanches.adapter.message.pedido.PedidoEvent;
import br.com.fiap.tiulanches.adapter.message.pedido.PedidoListener;

@Service
public class PedidoKafka implements PedidoListener {
    
	private final PreferenciaExternoController controller; 	

	public PedidoKafka(PreferenciaExternoController controller) {
		this.controller = controller;		
	}
    
    @Override    
    @KafkaListener(topics = "topico-pedido-producao", groupId = "grupo-pagamento")
    public void processaMensagem(PedidoEvent pedidoEvent) {
        controller.criar(pedidoEvent.getPedidoDto());
    }
}
