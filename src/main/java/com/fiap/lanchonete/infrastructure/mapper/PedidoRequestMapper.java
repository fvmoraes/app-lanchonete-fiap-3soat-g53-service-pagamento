package com.fiap.lanchonete.infrastructure.mapper;

import com.fiap.lanchonete.domain.entity.Pedido;
import com.fiap.lanchonete.infrastructure.requestsdto.PedidoRequest;
import com.fiap.lanchonete.infrastructure.requestsdto.PedidoResponse;

public class PedidoRequestMapper {
	
	public Pedido paraObjetoDominio(PedidoRequest pedidoEntity) {
		return new Pedido(pedidoEntity.getIdPedido(),pedidoEntity.getListaProdutos(),
				 pedidoEntity.getStatusPagamento(), pedidoEntity.getValorTotal());

	}
	
	public PedidoResponse paraResponse(Pedido pedido) {
		return new PedidoResponse(pedido.getId(), pedido.getListaProdutos(), pedido.getStatusPagamento(), pedido.getValorTotal());
	}
	
}
