package com.fiap.lanchonete.application.usecases;

import java.util.List;

import com.fiap.lanchonete.application.usecases.exceptions.PedidoNaoEncontradoException;
import com.fiap.lanchonete.domain.entity.Pedido;

public interface PedidoUseCases {

	public Pedido buscaPedidoId(Integer id) throws PedidoNaoEncontradoException;
	public Pedido criaPedido(Pedido pedido);
	public Pedido atualizaPedidoPagamento(String topic, Integer id);
	public List<Pedido> buscaPedidos();
	
}
