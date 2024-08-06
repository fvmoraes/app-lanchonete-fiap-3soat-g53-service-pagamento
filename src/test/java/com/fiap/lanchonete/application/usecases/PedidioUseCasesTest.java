package com.fiap.lanchonete.application.usecases;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fiap.lanchonete.application.gateways.PedidoGateway;
import com.fiap.lanchonete.application.usecases.exceptions.PedidoNaoEncontradoException;
import com.fiap.lanchonete.domain.entity.Categoria;
import com.fiap.lanchonete.domain.entity.Pedido;
import com.fiap.lanchonete.domain.entity.Produto;
import com.fiap.lanchonete.domain.entity.StatusPagamento;

public class PedidioUseCasesTest {

    private PedidoGateway pedidoGateway;
    private PedidoUseCases pedidoUseCases;

    @BeforeEach
    void setUp() {
        pedidoGateway = mock(PedidoGateway.class);
        pedidoUseCases = new PedidoUseCasesImp(pedidoGateway);
    }

    @Test
    void testBuscaPedidos() {
        List<Pedido> expectedPedidos = new ArrayList<>();
        when(pedidoGateway.buscaPedidos()).thenReturn(expectedPedidos);

        List<Pedido> result = pedidoUseCases.buscaPedidos();

        assertEquals(expectedPedidos, result);
    }

    @Test
    void testCriaPedido() {
        List<Produto> listaProdutos = new ArrayList<>();
        Produto produto = new Produto(Categoria.Lanche, "Hambúrguer", "Delicioso hambúrguer", BigDecimal.valueOf(10.0));
        listaProdutos.add(produto);
        Pedido pedido = new Pedido(1, listaProdutos, StatusPagamento.PENDENTE, BigDecimal.valueOf(10.0));
        when(pedidoGateway.criaPedido(pedido)).thenReturn(pedido);

        Pedido result = pedidoUseCases.criaPedido(pedido);

        assertEquals(pedido, result);
    }

    @Test
    void testBuscaPedidoId() throws PedidoNaoEncontradoException {
        List<Produto> listaProdutos = new ArrayList<>();
        Produto produto = new Produto(Categoria.Lanche, "Hambúrguer", "Delicioso hambúrguer", BigDecimal.valueOf(10.0));
        listaProdutos.add(produto);
        Pedido pedido = new Pedido(1, listaProdutos, StatusPagamento.PENDENTE, BigDecimal.valueOf(10.0));
        Integer id = 1;
        when(pedidoGateway.buscaPedidoId(id)).thenReturn(pedido);

        Pedido result = pedidoUseCases.buscaPedidoId(id);

        assertEquals(pedido, result);
    }

    @Test
    void testAtualizaPedidoPagamento() {
        List<Produto> listaProdutos = new ArrayList<>();
        Produto produto = new Produto(Categoria.Lanche, "Hambúrguer", "Delicioso hambúrguer", BigDecimal.valueOf(10.0));
        listaProdutos.add(produto);
        Pedido pedido = new Pedido(1, listaProdutos, StatusPagamento.PENDENTE, BigDecimal.valueOf(10.0));
        String topic = "chargebacks";
        when(pedidoGateway.buscaPedidoId(1)).thenReturn(pedido);

        Pedido result = pedidoUseCases.atualizaPedidoPagamento(topic, 1);

        assertEquals(StatusPagamento.CANCELADO, result.getStatusPagamento());
    }
    
 
    @Test
    void testBuscaPedidoId_ThrowException() {
        Integer id = 1;
        when(pedidoGateway.buscaPedidoId(id)).thenReturn(null);

        assertThrows(PedidoNaoEncontradoException.class, () -> {
            pedidoUseCases.buscaPedidoId(id);
        });
    }

    @Test
    void testAtualizaPedidoPagamento_Chargebacks() {
        List<Produto> listaProdutos = new ArrayList<>();
        Produto produto = new Produto(Categoria.Lanche, "Hambúrguer", "Delicioso hambúrguer", BigDecimal.valueOf(10.0));
        listaProdutos.add(produto);
        Pedido pedido = new Pedido(1, listaProdutos, StatusPagamento.PENDENTE, BigDecimal.valueOf(10.0));
        String topic = "chargebacks";
        when(pedidoGateway.buscaPedidoId(1)).thenReturn(pedido);

        Pedido result = pedidoUseCases.atualizaPedidoPagamento(topic, 1);

        assertEquals(StatusPagamento.CANCELADO, result.getStatusPagamento());
    }

    @Test
    void testAtualizaPedidoPagamento_NotChargebacks() {
        List<Produto> listaProdutos = new ArrayList<>();
        Produto produto = new Produto(Categoria.Lanche, "Hambúrguer", "Delicioso hambúrguer", BigDecimal.valueOf(10.0));
        listaProdutos.add(produto);
        Pedido pedido = new Pedido(1, listaProdutos, StatusPagamento.PENDENTE, BigDecimal.valueOf(10.0));
        String topic = "other";
        when(pedidoGateway.buscaPedidoId(1)).thenReturn(pedido);

        Pedido result = pedidoUseCases.atualizaPedidoPagamento(topic, 1);

        assertEquals(StatusPagamento.PAGO, result.getStatusPagamento());
    }
}