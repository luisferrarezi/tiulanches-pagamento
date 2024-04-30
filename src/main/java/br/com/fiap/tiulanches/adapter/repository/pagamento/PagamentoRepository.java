package br.com.fiap.tiulanches.adapter.repository.pagamento;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.fiap.tiulanches.core.entity.pagamento.Pagamento;

public interface PagamentoRepository extends MongoRepository <Pagamento, String>{	
}
