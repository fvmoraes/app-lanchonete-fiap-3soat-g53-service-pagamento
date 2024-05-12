package com.fiap.lanchonete.pagamento.infrastructure.gateway;

import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fiap.lanchonete.application.usecases.PedidoUseCases;
import com.fiap.lanchonete.application.usecases.exceptions.PedidoNaoEncontradoException;
import com.fiap.lanchonete.domain.entity.Pedido;
import com.fiap.lanchonete.domain.entity.event.PedidoRealizadoEvent;
import com.fiap.lanchonete.infrastructure.gateway.PagamentoConsumerGateway;

@ExtendWith(MockitoExtension.class)
class PagamentoConsumerGatewayTest {

    @Mock
    private PedidoUseCases pedidoUseCases;

    @InjectMocks
    private PagamentoConsumerGateway pagamentoConsumerGateway;

    @Test
    void testPagamentoRecebido() throws PedidoNaoEncontradoException {
        Pedido pedido = new Pedido();
        PedidoRealizadoEvent evento = new PedidoRealizadoEvent(pedido);

        pagamentoConsumerGateway.pagamentoRecebido(evento);

        verify(pedidoUseCases).criaPedido(pedido);
    }
}
