# IBGEClient

## Descrição do Projeto

API REST feita em Java para consulta da API pública do IBGE e geração de relatórios em diferentes formatos. Foram usadas as seguintes tecnologias:

* Maven
* Spring Boot
* Swagger
* Lombok
* Hystrix (Circuit Breaker)
* Cache
* OpenFeign

É possível acessar a documentação da API (Swagger-ui) através do link http://localhost:8080/swagger-ui.html quando a aplicação está em execução. Foram implementados testes básicos de integração.
O projeto também disponibiliza o hystrix dashboard no endereço http://localhost:8080/hystrix. O endereço a ser inserido no dashboard para monitorar os circuitos é http://localhost:8080/actuator/hystrix.stream.

## Execução

É necessário que tenha instalado o **maven** na máquina.
No terminal de seu Sistema, navegue até a pasta do projeto e digite:
>mvn spring-boot:run

O back-end será executado em http://localhost:8080.
