package br.com.fiap.tiulanchespagamento.core.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import br.com.fiap.tiulanchespagamento.adapter.controller.PagamentoController;
import br.com.fiap.tiulanchespagamento.adapter.repository.pagamento.PagamentoDto;
import br.com.fiap.tiulanchespagamento.adapter.repository.pagamento.PagamentoRepository;
import br.com.fiap.tiulanchespagamento.core.entitie.pagamento.Pagamento;
import br.com.fiap.tiulanchespagamento.core.exception.BusinessException;

@Service
public class PagamentoService implements PagamentoController {

	private final PagamentoRepository pagamentoRepository;	
	
	public PagamentoService(PagamentoRepository pagamentoRepository) {		
		this.pagamentoRepository = pagamentoRepository;
	}
	
	@Override
	public PagamentoDto consulta(UUID idPagamento) {
		Optional<Pagamento> pagamento = pagamentoRepository.findById(idPagamento);

		if (pagamento.isPresent()){
			return new PagamentoDto(pagamento.get());
		} else {
			throw new BusinessException("Pagamento não encontrado!", HttpStatus.NOT_FOUND, "Pagamento");
		}		
	}	

	@Override
	public void registra(PagamentoDto dto) {
		throw new UnsupportedOperationException("Unimplemented method 'cria'");
		/*Pagamento pagamento = pagamentoRepository.findById(dto.idPagamento()).orElseThrow(() -> new EntityNotFoundException());
				
		try {			
			pagamento.registrar(dto);
			pagamentoRepository.save(pagamento);			
		} catch (Exception e) {
			throw new BusinessException("Falha ao alterar pagamento!", HttpStatus.BAD_REQUEST, e.getMessage());
		}*/			
	}

	@Override
	public void cria(PagamentoDto dto) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'cria'");
	}
}
