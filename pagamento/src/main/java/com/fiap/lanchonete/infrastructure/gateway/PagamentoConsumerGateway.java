package com.fiap.lanchonete.infrastructure.gateway;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.fiap.lanchonete.application.usecases.PedidoUseCases;
import com.fiap.lanchonete.application.usecases.exceptions.PedidoNaoEncontradoException;
import com.fiap.lanchonete.domain.entity.event.PedidoRealizadoEvent;

@Component
public class PagamentoConsumerGateway {
	private static final String PAGAMENTO_QUEUE_1 = "pagamento-queue";
	private static final Logger log = LoggerFactory.getLogger(PagamentoConsumerGateway.class);


	final PedidoUseCases pedidoUseCases;
	
	PagamentoConsumerGateway(PedidoUseCases pedidoUseCases){
		this.pedidoUseCases = pedidoUseCases;
	}
	
	@RabbitListener(queues = PAGAMENTO_QUEUE_1)
	public void pagamentoRecebido(PedidoRealizadoEvent pedidoRealizado) throws PedidoNaoEncontradoException {
		pedidoUseCases.criaPedido(pedidoRealizado.getPedidoRealizado());
		log.info(String.format("Pedido com id {} Cadastrado com sucesso! A Espera do Pagamento!"),pedidoRealizado.getPedidoRealizado().getId());
	}
	
}
