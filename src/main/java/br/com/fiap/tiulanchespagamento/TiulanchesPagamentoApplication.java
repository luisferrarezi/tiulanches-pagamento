package br.com.fiap.tiulanchespagamento;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(title="API Tiu Lanches - Pagamento", version = "24.01.20", description = "Tech Challenge para conclusão Pós Graduação Software Architecture pela FIAP"),
		servers = { @Server(url="http://localhost:8083") }
)
public class TiulanchesPagamentoApplication {

	public static void main(String[] args) {
		SpringApplication.run(TiulanchesPagamentoApplication.class, args);
	}

}
