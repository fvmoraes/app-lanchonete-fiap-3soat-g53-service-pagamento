package com.fiap.lanchonete.application.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fiap.lanchonete.application.usecases.PedidoUseCases;
import com.fiap.lanchonete.application.usecases.exceptions.PedidoNaoEncontradoException;
import com.fiap.lanchonete.domain.entity.Pedido;
import com.fiap.lanchonete.domain.entity.StatusPagamento;
import com.fiap.lanchonete.infrastructure.controller.PedidoController;
import com.fiap.lanchonete.infrastructure.mapper.PedidoRequestMapper;

class PedidoControllerTest {

    private PedidoUseCases pedidoUseCases;
    private PedidoRequestMapper mapper;
    private PedidoController pedidoController;
    private MockMvc mockMvc;
    private RabbitTemplate rabbit;

    @BeforeEach
    void setUp() {
        pedidoUseCases = mock(PedidoUseCases.class);
        mapper = mock(PedidoRequestMapper.class);
        rabbit = mock(RabbitTemplate.class);
        pedidoController = new PedidoController(pedidoUseCases, mapper, rabbit);
        mockMvc = MockMvcBuilders.standaloneSetup(pedidoController).build();
    }

    @Test
    void testBuscaPedidos() throws Exception {
        List<Pedido> expectedPedidos = new ArrayList<>();
        when(pedidoUseCases.buscaPedidos()).thenReturn(expectedPedidos);

        mockMvc.perform(get("/api/v1/pedido"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void testBuscaPedidosPorId_Existente() throws Exception {
    	  int id = 1;
    	  Pedido pedido = new Pedido(id, new ArrayList<>(), StatusPagamento.PENDENTE, BigDecimal.TEN);
    	  when(pedidoUseCases.buscaPedidoId(id)).thenReturn(pedido);

    	    mockMvc.perform(get("/api/v1/pedido/{id}", id))
    	            .andExpect(status().isOk());
    }

    @Test
    void testBuscaPedidosPorId_NaoExistente() throws Exception {
        int id = 1;
        when(pedidoUseCases.buscaPedidoId(id)).thenThrow(PedidoNaoEncontradoException.class);

        mockMvc.perform(get("/api/v1/pedido/{id}", id))
                .andExpect(status().isNoContent());
    }

  
    @Test
    void testWebHookMercadoPagoSimulator_Pago() throws Exception {
        int id = 1;
        Pedido pedido = new Pedido(id, new ArrayList<>(), StatusPagamento.PAGO, BigDecimal.TEN);
        when(pedidoUseCases.atualizaPedidoPagamento(any(), anyInt())).thenReturn(pedido);

        mockMvc.perform(post("/api/v1/pedido/pagamento/mercadopago/chargebacks/{id}", id))
                .andExpect(status().isOk())
                .andExpect(content().string("Pedido pago com sucesso!"));
    }

    @Test
    void testWebHookMercadoPagoSimulator_Cancelado() throws Exception {
        int id = 1;
        Pedido pedido = new Pedido(id, new ArrayList<>(), StatusPagamento.PENDENTE, BigDecimal.TEN);
        when(pedidoUseCases.atualizaPedidoPagamento(any(), anyInt())).thenReturn(pedido);

        mockMvc.perform(post("/api/v1/pedido//pagamento/mercadopago/other/{id}", id))
                .andExpect(status().isOk())
                .andExpect(content().string("Pedido Cancelado!"));
    }
}