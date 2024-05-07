# Desafio de votação
Este projeto tem como objetivo a criação de um ambiente virtual para decidir pautas em uma assembleia
Segue link com as instruções do desafio: 

## Requisitos
- Ter o `docker` instalado

## Tecnologias utilizadas
- API
  - Spring Boot
  - Postgresql
  - Flyway
  - Swagger
- WEB
  - Angular
  - Prime NG

## Instruções de instalação
```sh
git clone https://github.com/Samukaii/desafio-votacao.git
cd desafio-votacao
```
### API
```sh
cd api
./mvnw clean package -DskipTests
docker compose up
# Aguardar a inicialização completa
```
### WEB
```sh
cd ../web
docker build -t voting-challenge-web .
docker run -p 8090:80 voting-challenge-web
```

## Rodando a aplicação
Acesse a url [http://localhost:8090](http://localhost:8090)

## Documentação da API
Acesse a url [http://localhost:8080/swagger-ui/index.html#](http://localhost:8080/swagger-ui/index.html#)
