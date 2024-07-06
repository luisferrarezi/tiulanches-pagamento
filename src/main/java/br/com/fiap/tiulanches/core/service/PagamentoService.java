package br.com.fiap.tiulanches.core.service;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.fiap.tiulanches.adapter.controller.PagamentoController;
import br.com.fiap.tiulanches.adapter.message.pagamento.PagamentoMessage;
import br.com.fiap.tiulanches.adapter.repository.pagamento.PagamentoDto;
import br.com.fiap.tiulanches.adapter.repository.pagamento.PagamentoRepository;
import br.com.fiap.tiulanches.core.entity.pagamento.Pagamento;
import br.com.fiap.tiulanches.core.enums.Pago;
import br.com.fiap.tiulanches.core.exception.BusinessException;

@Service
public class PagamentoService implements PagamentoController {

	private final PagamentoRepository pagamentoRepository;
	private final PagamentoMessage pagamentoMessage;	
	
	public PagamentoService(PagamentoRepository pagamentoRepository, PagamentoMessage pagamentoMessage) {		
		this.pagamentoRepository = pagamentoRepository;
		this.pagamentoMessage = pagamentoMessage;
	}
	
	@Override
	public PagamentoDto consulta(String idPagamento) {
		Optional<Pagamento> pagamento = pagamentoRepository.findById(idPagamento);

		if (pagamento.isPresent()){
			return new PagamentoDto(pagamento.get());
		} else {
			throw new BusinessException("Pagamento não encontrado!", HttpStatus.NOT_FOUND, "Pagamento");
		}		
	}	

	@Override
	@Transactional
	public void registra(PagamentoDto dto) {
		Optional<Pagamento> pagamento = pagamentoRepository.findById(dto.idPagamento());		

		if (pagamento.isPresent()){
			try {			
				pagamento.get().registrar(dto);
				pagamentoRepository.save(pagamento.get());
				pagamentoMessage.enviaMensagem(pagamento.get().getIdPedido(), Pago.SIM);
			} catch (Exception e) {
				pagamentoMessage.enviaMensagem(pagamento.get().getIdPedido(), Pago.NAO);
				throw new BusinessException("Falha ao alterar pagamento!", HttpStatus.BAD_REQUEST, e.getMessage());
			}			
		} else {
			throw new BusinessException("Pagamento não encontrado!", HttpStatus.NOT_FOUND, "Pagamento");
		}		
	}

	@Override
	@Transactional	
	public void cria(PagamentoDto dto) {
		try {			
			Pagamento pagamento = new Pagamento();
			pagamento.criar(dto);
			pagamentoRepository.save(pagamento);			
		} catch (Exception e) {
			pagamentoMessage.enviaMensagem(dto.idPedido(), Pago.NAO);
			throw new BusinessException("Falha ao alterar pagamento!", HttpStatus.BAD_REQUEST, e.getMessage());
		}			
	}
}
