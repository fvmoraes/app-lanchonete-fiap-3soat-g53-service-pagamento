package com.fiap.lanchonete.pagamento.infrastructure.gateway.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.fiap.lanchonete.domain.entity.Pedido;
import com.fiap.lanchonete.domain.entity.Produto;
import com.fiap.lanchonete.domain.entity.StatusPagamento;
import com.fiap.lanchonete.infrastructure.gateway.mapper.PedidoEntityMapper;
import com.fiap.lanchonete.infrastructure.persistence.entity.PedidoEntity;

class PedidoEntityMapperTest {

    @Test
    void testParaPedidoEntity() {
        PedidoEntityMapper mapper = new PedidoEntityMapper();
        Integer idPedido = 1;
        List<Produto> listaProdutos = new ArrayList<>();
        StatusPagamento statusPagamento = StatusPagamento.PENDENTE;
        BigDecimal valorTotal = BigDecimal.TEN;

        Pedido pedido = new Pedido(idPedido, listaProdutos, statusPagamento, valorTotal);

        PedidoEntity pedidoEntity = mapper.paraPedidoEntity(pedido);

        assertEquals(idPedido, pedidoEntity.getId());
        assertEquals(listaProdutos, pedidoEntity.getListaProdutosPedido());
        assertEquals(statusPagamento, pedidoEntity.getStatusPagamento());
        assertEquals(valorTotal, pedidoEntity.getValorTotal());
    }

    @Test
    void testParaObjetoDominio() {
        PedidoEntityMapper mapper = new PedidoEntityMapper();
        List<Produto> listaProdutos = new ArrayList<>();
        StatusPagamento statusPagamento = StatusPagamento.PENDENTE;
        BigDecimal valorTotal = BigDecimal.TEN;

        PedidoEntity pedidoEntity = new PedidoEntity(listaProdutos, statusPagamento, valorTotal);

        Pedido pedido = mapper.paraObjetoDominio(pedidoEntity);

        assertEquals(listaProdutos, pedido.getListaProdutos());
        assertEquals(statusPagamento, pedido.getStatusPagamento());
        assertEquals(valorTotal, pedido.getValorTotal());
    }
}
