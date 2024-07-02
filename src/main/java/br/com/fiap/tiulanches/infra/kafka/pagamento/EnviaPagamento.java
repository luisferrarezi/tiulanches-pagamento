package br.com.fiap.tiulanches.infra.kafka.pagamento;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import br.com.fiap.tiulanches.adapter.message.pagamento.PagamentoEvent;
import br.com.fiap.tiulanches.adapter.message.pagamento.PagamentoMessage;
import br.com.fiap.tiulanches.core.enums.Pago;

@Component
public class EnviaPagamento implements PagamentoMessage {

    private final KafkaTemplate<String, Object> kafka;

    public EnviaPagamento(KafkaTemplate<String, Object> kafka) {
	    this.kafka = kafka;
	}

    @Override
    public void enviaMensagem(long idPedido, Pago pago) {
        PagamentoEvent pagamentoEvent = new PagamentoEvent(idPedido, pago);

        kafka.send("topico-pagamento-pedido", pagamentoEvent);
    }    
}
