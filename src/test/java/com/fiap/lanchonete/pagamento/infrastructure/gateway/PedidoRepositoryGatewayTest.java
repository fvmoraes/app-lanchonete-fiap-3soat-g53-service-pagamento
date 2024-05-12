package com.fiap.lanchonete.pagamento.infrastructure.gateway;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fiap.lanchonete.application.gateways.PedidoGateway;
import com.fiap.lanchonete.domain.entity.Pedido;
import com.fiap.lanchonete.infrastructure.gateway.PedidoRepositoryGateway;
import com.fiap.lanchonete.infrastructure.gateway.mapper.PedidoEntityMapper;
import com.fiap.lanchonete.infrastructure.persistence.PedidoRepository;
import com.fiap.lanchonete.infrastructure.persistence.entity.PedidoEntity;

class PedidoRepositoryGatewayTest {

    private PedidoRepository repository;
    private PedidoEntityMapper mapper;
    private PedidoGateway pedidoGateway;

    @BeforeEach
    void setUp() {
        repository = mock(PedidoRepository.class);
        mapper = mock(PedidoEntityMapper.class);
        pedidoGateway = new PedidoRepositoryGateway(repository, mapper);
    }

    @Test
    void testCriaPedido() {
        Pedido pedido = new Pedido();
        PedidoEntity pedidoEntity = new PedidoEntity();
        when(mapper.paraPedidoEntity(pedido)).thenReturn(pedidoEntity);
        when(repository.save(pedidoEntity)).thenReturn(pedidoEntity);
        when(mapper.paraObjetoDominio(pedidoEntity)).thenReturn(pedido);

        Pedido result = pedidoGateway.criaPedido(pedido);

        assertEquals(pedido, result);
    }
    @Test
    void testBuscaPedidos() {
        List<PedidoEntity> pedidoEntities = new ArrayList<>();
        when(repository.findAll()).thenReturn(pedidoEntities);

        pedidoGateway.buscaPedidos();

        verify(repository).findAll();
    }
    
    @Test
    void testAtualizaPedido() {
        Pedido pedido = new Pedido();
        PedidoEntity pedidoEntity = new PedidoEntity();
        when(mapper.paraPedidoEntity(pedido)).thenReturn(pedidoEntity);

        pedidoGateway.atualizaPedido(pedido);

        verify(repository, times(1)).save(pedidoEntity);
    }

    @Test
    void testBuscaPedidoId_Existente() {
        int id = 1;
        PedidoEntity pedidoEntity = new PedidoEntity();
        Pedido pedido = new Pedido();
        when(repository.findById(id)).thenReturn(Optional.of(pedidoEntity));
        when(mapper.paraObjetoDominio(pedidoEntity)).thenReturn(pedido);

        Pedido result = pedidoGateway.buscaPedidoId(id);

        assertEquals(pedido, result);
    }

    @Test
    void testBuscaPedidoId_NaoExistente() {
        int id = 1;
        when(repository.findById(id)).thenReturn(Optional.empty());

        Pedido result = pedidoGateway.buscaPedidoId(id);

        assertNull(result);
    }

}
