package com.fiap.lanchonete.pagamento.infrastructure.requestsdto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.fiap.lanchonete.domain.entity.Produto;
import com.fiap.lanchonete.domain.entity.StatusPagamento;
import com.fiap.lanchonete.infrastructure.requestsdto.PedidoResponse;

class PedidoResponseTest {

    @Test
    void testGettersAndSetters() {
        Integer idPedido = 1;
        List<Produto> listaProdutos = new ArrayList<>();
        StatusPagamento statusPagamento = StatusPagamento.PENDENTE;
        BigDecimal valorTotal = BigDecimal.TEN;

        PedidoResponse pedidoResponse = new PedidoResponse(idPedido, listaProdutos, statusPagamento, valorTotal);

        assertEquals(idPedido, pedidoResponse.getIdPedido());
        assertEquals(listaProdutos, pedidoResponse.getListaProdutos());
        assertEquals(statusPagamento, pedidoResponse.getStatusPagamento());
        assertEquals(valorTotal, pedidoResponse.getValorTotal());
    }
    

    @Test
    void testGetSetIdPedido() {
        Integer idPedido = 1;
        PedidoResponse pedidoResponse = new PedidoResponse();
        pedidoResponse.setIdPedido(idPedido);
        assertEquals(idPedido, pedidoResponse.getIdPedido());
    }

    @Test
    void testGetSetListaProdutos() {
        List<Produto> listaProdutos = new ArrayList<>();
        listaProdutos.add(new Produto());
        PedidoResponse pedidoResponse = new PedidoResponse();
        pedidoResponse.setListaProdutos(listaProdutos);
        assertEquals(listaProdutos, pedidoResponse.getListaProdutos());
    }

    @Test
    void testGetSetStatusPagamento() {
        StatusPagamento statusPagamento = StatusPagamento.PAGO;
        PedidoResponse pedidoResponse = new PedidoResponse();
        pedidoResponse.setStatusPagamento(statusPagamento);
        assertEquals(statusPagamento, pedidoResponse.getStatusPagamento());
    }

    @Test
    void testGetSetValorTotal() {
        BigDecimal valorTotal = BigDecimal.valueOf(10.0);
        PedidoResponse pedidoResponse = new PedidoResponse();
        pedidoResponse.setValorTotal(valorTotal);
        assertEquals(valorTotal, pedidoResponse.getValorTotal());
    }
}
