package com.fiap.lanchonete.pagamento.infrastructure.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.fiap.lanchonete.domain.entity.Pedido;
import com.fiap.lanchonete.domain.entity.Produto;
import com.fiap.lanchonete.domain.entity.StatusPagamento;
import com.fiap.lanchonete.infrastructure.mapper.PedidoRequestMapper;
import com.fiap.lanchonete.infrastructure.requestsdto.PedidoRequest;
import com.fiap.lanchonete.infrastructure.requestsdto.PedidoResponse;

class PedidoRequestMapperTest {

    @Test
    void testParaObjetoDominio() {
        PedidoRequestMapper mapper = new PedidoRequestMapper();
        Integer idPedido = 1;
        List<Produto> listaProdutos = new ArrayList<>();
        StatusPagamento statusPagamento = StatusPagamento.PAGO;
        BigDecimal valorTotal = BigDecimal.valueOf(10.0);

        PedidoRequest pedidoRequest = new PedidoRequest(idPedido, listaProdutos, statusPagamento, valorTotal);
        Pedido pedido = mapper.paraObjetoDominio(pedidoRequest);

        assertNotNull(pedido);
        assertEquals(idPedido, pedido.getId());
        assertEquals(listaProdutos, pedido.getListaProdutos());
        assertEquals(statusPagamento, pedido.getStatusPagamento());
        assertEquals(valorTotal, pedido.getValorTotal());
    }

    @Test
    void testParaResponse() {
        PedidoRequestMapper mapper = new PedidoRequestMapper();
        Integer idPedido = 1;
        List<Produto> listaProdutos = new ArrayList<>();
        StatusPagamento statusPagamento = StatusPagamento.PAGO;
        BigDecimal valorTotal = BigDecimal.valueOf(10.0);

        Pedido pedido = new Pedido(idPedido, listaProdutos, statusPagamento, valorTotal);
        PedidoResponse pedidoResponse = mapper.paraResponse(pedido);

        assertNotNull(pedidoResponse);
        assertEquals(idPedido, pedidoResponse.getIdPedido());
        assertEquals(listaProdutos, pedidoResponse.getListaProdutos());
        assertEquals(statusPagamento, pedidoResponse.getStatusPagamento());
        assertEquals(valorTotal, pedidoResponse.getValorTotal());
    }


}
