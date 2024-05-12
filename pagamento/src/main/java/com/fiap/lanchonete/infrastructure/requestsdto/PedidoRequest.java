package com.fiap.lanchonete.infrastructure.requestsdto;

import java.math.BigDecimal;
import java.util.List;

import com.fiap.lanchonete.domain.entity.Produto;
import com.fiap.lanchonete.domain.entity.StatusPagamento;

public class PedidoRequest {
	
	Integer idPedido;
	List<Produto> listaProdutos;
	StatusPagamento statusPagamento;
	BigDecimal valorTotal;

	public PedidoRequest() {
		
	}
	
	public PedidoRequest(Integer idPedido, List<Produto> listaProdutos,
			StatusPagamento statusPagamento, BigDecimal valorTotal) {
		this.idPedido = idPedido;
		this.listaProdutos = listaProdutos;
		this.statusPagamento = statusPagamento;
		this.valorTotal = valorTotal;
	}
	public Integer getIdPedido() {
		return idPedido;
	}
	public void setIdPedido(Integer idPedido) {
		this.idPedido = idPedido;
	}
	public List<Produto> getListaProdutos() {
		return listaProdutos;
	}
	public void setListaProdutos(List<Produto> listaProdutos) {
		this.listaProdutos = listaProdutos;
	}
	
	public StatusPagamento getStatusPagamento() {
		return statusPagamento;
	}
	public void setStatusPagamento(StatusPagamento statusPagamento) {
		this.statusPagamento = statusPagamento;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	
}
