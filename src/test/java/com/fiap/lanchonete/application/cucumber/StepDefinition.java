package com.fiap.lanchonete.application.cucumber;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.CoreMatchers.containsString;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.http.MediaType;

import com.fiap.lanchonete.domain.entity.Pedido;
import com.fiap.lanchonete.domain.entity.Produto;
import com.fiap.lanchonete.domain.entity.StatusPagamento;
import com.fiap.lanchonete.infrastructure.requestsdto.PedidoResponse;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import io.restassured.response.Response;

public class StepDefinition {
	private final String ENDPOINT_API = "http://localhost:8080/pagamento/api/v1/pedido";

	private Response response;

	private Integer id;
	Pedido pedidoRequest = StepDefinition.gerarPedidoAleatorio();

	@Quando("efeturar uma requisicao para criar um Pedido")
	public PedidoResponse efeturar_uma_requisicao_para_criar_um_pedido() {

		response = given().contentType(MediaType.APPLICATION_JSON_VALUE).body(pedidoRequest).when().post(ENDPOINT_API);

		return response.then().extract().as(PedidoResponse.class);

	}

	@Dado("que um pedido ja foi registrado")
	public void que_um_pedido_ja_foi_registrado() {
		id = efeturar_uma_requisicao_para_criar_um_pedido().getIdPedido();
	}

	@Quando("efetuar requisicao de pagamento de Pedido")
	public void efetuar_requisicao_de_pagamento_de_pedido() {
		response = when().post(ENDPOINT_API + "/pagamento/mercadopago/{topico}/{id}", "pago", id);

	}

	@Entao("deve retornar o Pedido")
	public void deve_retornar_o_pedido() {
		response.then().body(containsString("\"idPedido\" : " + pedidoRequest.getId() + ""));
	}

	@Entao("o Pedido é pago com sucesso")
	public void o_pedido_é_atualizado_com_sucesso() {
		response.then().body(containsString("Pedido pago com sucesso!"));
	}

	@Quando("efetuar requisicao de cancelamento de agamento Pedido")
	public void efetuar_requisicao_de_cancelamento_de_agamento_pedido() {
		response = when().post(ENDPOINT_API + "/pagamento/mercadopago/{topico}/{id}", "chargebacks", id);
	}

	@Entao("o Pedido é cancelado com sucesso")
	public void o_pedido_é_cancelado_com_sucesso() {
		response.then().body(containsString("Pedido Cancelado!"));

	}
	
	// Método para gerar um Pedido aleatório
	public static Pedido gerarPedidoAleatorio() {
		Pedido pedido = new Pedido();
		pedido.setId(new Random().nextInt(250)); // Gerando um ID aleatório entre 0 e 999

		// Gerando uma lista de produtos aleatórios
		pedido.setListaProdutos(gerarListaProdutosAleatoria());

		// Gerando um StatusPagamento aleatório
		pedido.setStatusPagamento(StatusPagamento.PENDENTE);

		// Gerando um valor total aleatório
		pedido.setValorTotal(gerarValorTotalAleatorio());

		return pedido;
	}

	// Método para gerar uma lista de produtos aleatórios
	private static List<Produto> gerarListaProdutosAleatoria() {
		List<Produto> listaProdutos = new ArrayList<>();
		// Gerando um número aleatório de produtos entre 1 e 10
		int numProdutos = new Random().nextInt(3) + 1;
		for (int i = 0; i < numProdutos; i++) {
			Produto produto = new Produto();
			produto.setNome("Produto " + (i + 1));
			produto.setValor(BigDecimal.valueOf(new Random().nextDouble() * 100)); // Preços aleatórios entre 0 e 100
			listaProdutos.add(produto);
		}
		return listaProdutos;
	}

	// Método para gerar um valor total aleatório
	private static BigDecimal gerarValorTotalAleatorio() {
		// Gerando um valor total aleatório entre 0 e 1000
		return BigDecimal.valueOf(new Random().nextDouble() * 250);
	}
}
