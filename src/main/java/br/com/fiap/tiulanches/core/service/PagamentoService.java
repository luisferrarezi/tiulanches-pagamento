package br.com.fiap.tiulanches.core.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.fiap.tiulanches.adapter.controller.PagamentoController;
import br.com.fiap.tiulanches.adapter.message.EventoEnum;
import br.com.fiap.tiulanches.adapter.message.pedido.PedidoMessage;
import br.com.fiap.tiulanches.adapter.repository.pagamento.PagamentoDto;
import br.com.fiap.tiulanches.adapter.repository.pagamento.PagamentoRepository;
import br.com.fiap.tiulanches.adapter.repository.pedido.PedidoDto;
import br.com.fiap.tiulanches.core.entitie.pagamento.Pagamento;
import br.com.fiap.tiulanches.core.entitie.pedido.Pedido;
import br.com.fiap.tiulanches.core.exception.BusinessException;

@Service
public class PagamentoService implements PagamentoController {

	private final PagamentoRepository pagamentoRepository;
	private final PedidoMessage pedidoMessage;	
	
	public PagamentoService(PagamentoRepository pagamentoRepository, PedidoMessage pedidoMessage) {		
		this.pagamentoRepository = pagamentoRepository;
		this.pedidoMessage = pedidoMessage;
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
	public void registra(PagamentoDto dto) {
		Optional<Pagamento> pagamento = pagamentoRepository.findById(dto.idPagamento());		

		if (pagamento.isPresent()){
			try {			
				pagamento.get().registrar(dto);
				pagamentoRepository.save(pagamento.get());
				pedidoMessage.enviaMensagem(EventoEnum.UPDATE, new PedidoDto(new Pedido(pagamento.get().getIdPedido(), null, new ArrayList<>())));
			} catch (Exception e) {
				throw new BusinessException("Falha ao alterar pagamento!", HttpStatus.BAD_REQUEST, e.getMessage());
			}			
		} else {
			throw new BusinessException("Pagamento não encontrado!", HttpStatus.NOT_FOUND, "Pagamento");
		}		
	}

	@Override
	public void cria(PagamentoDto dto) {
		try {			
			Pagamento pagamento = new Pagamento();
			pagamento.criar(dto);
			pagamentoRepository.save(pagamento);			
		} catch (Exception e) {
			throw new BusinessException("Falha ao alterar pagamento!", HttpStatus.BAD_REQUEST, e.getMessage());
		}			
	}
}
