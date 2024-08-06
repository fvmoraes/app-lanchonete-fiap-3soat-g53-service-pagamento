package com.fiap.lanchonete.pagamento.infrastructure.persistence.entity;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.fiap.lanchonete.domain.entity.Produto;
import com.fiap.lanchonete.domain.entity.StatusPagamento;
import com.fiap.lanchonete.infrastructure.persistence.entity.PedidoEntity;

class PedidoEntityTest {

    @Test
    void testGettersAndSetters() {
        Integer id = 1;
        List<Produto> listaProdutosPedido = new ArrayList<>();
        StatusPagamento statusPagamento = StatusPagamento.PENDENTE;
        BigDecimal valorTotal = BigDecimal.TEN;

        PedidoEntity pedidoEntity = new PedidoEntity();
        pedidoEntity.setId(id);
        pedidoEntity.setListaProdutosPedido(listaProdutosPedido);
        pedidoEntity.setStatusPagamento(statusPagamento);
        pedidoEntity.setValorTotal(valorTotal);

        assertEquals(id, pedidoEntity.getId());
        assertEquals(listaProdutosPedido, pedidoEntity.getListaProdutosPedido());
        assertEquals(statusPagamento, pedidoEntity.getStatusPagamento());
        assertEquals(valorTotal, pedidoEntity.getValorTotal());
    }
}
