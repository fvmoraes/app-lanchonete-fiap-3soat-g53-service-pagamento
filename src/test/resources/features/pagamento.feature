# language: pt

Funcionalidade: Pedido

  Cenario: Registrar Pedido
    Quando efeturar uma requisicao para criar um Pedido
    Entao  deve retornar o Pedido
    
  Cenario: Pagar Pedido
    Dado que um pedido ja foi registrado
	Quando efetuar requisicao de pagamento de Pedido
	Entao o Pedido é pago com sucesso
	    
  Cenario: Caclear Pagamento Pedido
    Dado que um pedido ja foi registrado
	Quando efetuar requisicao de cancelamento de agamento Pedido
	Entao o Pedido é cancelado com sucesso
	