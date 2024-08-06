package com.fiap.lanchonete.pagamento.infrastructure.requestsdto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.fiap.lanchonete.domain.entity.Produto;
import com.fiap.lanchonete.domain.entity.StatusPagamento;
import com.fiap.lanchonete.infrastructure.requestsdto.PedidoRequest;

class PedidoRequestTest {

    @Test
    void testGettersAndSetters() {
        Integer idPedido = 1;
        List<Produto> listaProdutos = new ArrayList<>();
        StatusPagamento statusPagamento = StatusPagamento.PENDENTE;
        BigDecimal valorTotal = BigDecimal.TEN;

        PedidoRequest pedidoRequest = new PedidoRequest();
        pedidoRequest.setIdPedido(idPedido);
        pedidoRequest.setListaProdutos(listaProdutos);
        pedidoRequest.setStatusPagamento(statusPagamento);
        pedidoRequest.setValorTotal(valorTotal);

        assertEquals(idPedido, pedidoRequest.getIdPedido());
        assertEquals(listaProdutos, pedidoRequest.getListaProdutos());
        assertEquals(statusPagamento, pedidoRequest.getStatusPagamento());
        assertEquals(valorTotal, pedidoRequest.getValorTotal());
    }
}
