# Sistema de Pedidos para Lanchonete - FIAP 3SOAT (Grupo 53)
---
---
## Estrutura e tecnologias utilizadas
### Qual a proposta?
>Este projeto é um microserviço para a aplicação de uma lanchonete, desenvolvida como parte do curso de pós graduação em Software Architeture da FIAP. É encarregado da parte de realização de pagamentos dos pedidos.

Os microserviços fazem a comunicação entre si utilizando o RabbitMQ

### Tecnologias utilizadas:
- [Java](https://dev.java/learn/)
- [Docker](https://docs.docker.com/get-started/)
- [Cassandra](https://cassandra.apache.org/_/index.html)
- [PostgreSQL](https://www.postgresql.org/about/)
- [PGAdmin](https://www.pgadmin.org/docs/)
- [Swagger](https://swagger.io/solutions/api-documentation/)

### Informações Básicas:

 Para interação básica, você pode usar o Swagger (via navegador) ou o Postman (importando a collection, o arquivo FIAP.postman_collection.json que contém a configuração essencial para iniciar o uso).

 O banco de dados Cassandra e o banco de dados Postgress já estão configuradosdentro do docker-compose prontos para uso  dos três microserviços

 O projeto possui boas praticas de programação com base em DDD e CleanCode.

### Banco de Dados:

Esse microserviço utiliza o Postgress quee utiliza a tabelas a seguir:

  +--------------------+
  |       Pedido       |
  +--------------------+
  | id (PK)            |
  | statusPedido       |
  | listaProdutosPedido|
  | statusPagamento    |
  | valorTotal         |
  +--------------------+

 

Essa estrutura de banco de dados possui uma tabela principal: Pedido , representando a entidade PedidoEntity:

    A tabela Pedido armazena informações sobre cada pedido, como seu identificador (id),uma lista de produtos relacionado ao pedido (listaProdutosPedido), status do pagamento (statusPagamento), e o valor total (valorTotal).

    
A chave primária (PK) de cada tabela está indicada.



## Como Usar
### O que preciso ter instalado no meu computador?
> Qualquer sistema operacional Linux ou subsistema Linux, Windows ou MacOs

> Docker, Docker Desktop e Docker Compose instalados na sua máquina

- [Docker](https://docs.docker.com/get-started/)
- [Docker Compose](https://docs.docker.com/compose/install/)

### Como executar o projeto no meu computador?
> Não é necessario criar databases, ou rodar o projeto de forma local, e sim apenas utilizar um docker-compose que existe neste repositorio. Esse Docker-compose já está configurado para rodar os três microserviços.

> Dentro do repositorio da aplicação, o comando inicial pode ser o seguinte para uso com Docker Compose:
```sh
docker-compose up
``````
> Após é só rodar localmente o projeto!

---
## Mais informações sobre a API
### Lista de endpoints
GET http://localhost:8080/api/v1/pedido
POST localhost:8080/api/v1/pedido/pagamento/mercadopago/payment/1
DELETE http://localhost:8080/api/v1/produto
GET http://localhost:8080/api/v1/pedido/{id}

> Swagger
- GET http://localhost:8080/swagger-ui/index.html
---
---
## Referências
### Este projeto foi criado usando os seguintes pacotes Java
- [spring boot starter data jpa](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-data-jpa)
- [spring boot starter web](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-web)
- [springdoc openapi starter webmvc ui](https://mvnrepository.com/artifact/org.springdoc/springdoc-openapi-starter-webmvc-ui)
- [postgresql](https://mvnrepository.com/artifact/org.postgresql/postgresql)
- [hibernate validator](https://mvnrepository.com/artifact/org.hibernate.validator/hibernate-validator)
- [spring boot](https://spring.io/projects/spring-boot/)

---
---
_fim do README.md_
