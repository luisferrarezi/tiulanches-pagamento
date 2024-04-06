package br.com.fiap.tiulanchespagamento.adapter.repository.pagamento;

import java.util.UUID;

import org.springframework.data.mongodb.repository.MongoRepository;
import br.com.fiap.tiulanchespagamento.core.entitie.pagamento.Pagamento;

public interface PagamentoRepository extends MongoRepository <Pagamento, UUID>{	
}
