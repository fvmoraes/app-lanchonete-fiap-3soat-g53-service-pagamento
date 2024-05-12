package com.fiap.lanchonete.application.usecases;

import java.util.List;

import com.fiap.lanchonete.application.gateways.PedidoGateway;
import com.fiap.lanchonete.application.usecases.exceptions.PedidoNaoEncontradoException;
import com.fiap.lanchonete.domain.entity.Pedido;
import com.fiap.lanchonete.domain.entity.StatusPagamento;

public class PedidoUseCasesImp implements PedidoUseCases {
	
	private final PedidoGateway pedidoGateway;

	public PedidoUseCasesImp(PedidoGateway pedidoGateway) {
		this.pedidoGateway = pedidoGateway;
	}

	@Override
	public Pedido criaPedido(Pedido pedido) {
		return pedidoGateway.criaPedido(pedido);
	}
	
	@Override
	public Pedido buscaPedidoId(Integer id) throws PedidoNaoEncontradoException {
		Pedido pedido = pedidoGateway.buscaPedidoId(id);
		if (pedido == null)
			throw new PedidoNaoEncontradoException();

		return pedido;
	}
	
	
	
	@Override
	public Pedido atualizaPedidoPagamento(String topic, Integer id) {
		Pedido pedidoParaAtualizar = pedidoGateway.buscaPedidoId(id);
		Pedido pedidoAtaulizado;
		if (topic.equals("chargebacks")) {
			pedidoAtaulizado = new Pedido(pedidoParaAtualizar.getId(), 
					pedidoParaAtualizar.getListaProdutos(), StatusPagamento.CANCELADO,pedidoParaAtualizar.getValorTotal());
			pedidoGateway.atualizaPedido(pedidoAtaulizado);
			
			return pedidoAtaulizado;
		} else {
			pedidoAtaulizado = new Pedido(pedidoParaAtualizar.getId(), 
					pedidoParaAtualizar.getListaProdutos(), StatusPagamento.PAGO, pedidoParaAtualizar.getValorTotal());
			pedidoGateway.atualizaPedido(pedidoAtaulizado);
			return pedidoAtaulizado;
		}

	}

	@Override
	public List<Pedido> buscaPedidos() {
		return pedidoGateway.buscaPedidos();
		
	}

}
