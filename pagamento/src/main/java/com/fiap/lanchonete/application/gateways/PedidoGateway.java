package com.fiap.lanchonete.application.gateways;

import java.util.List;

import com.fiap.lanchonete.domain.entity.Pedido;

public interface PedidoGateway {
	Pedido criaPedido(Pedido pedido);

	void atualizaPedido(Pedido pedido);

	Pedido buscaPedidoId(Integer id);

	List<Pedido> buscaPedidos();

}
