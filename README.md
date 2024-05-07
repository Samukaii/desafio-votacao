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
# Aguardar a inicialização finalizar
```
### WEB
```sh
cd ../web
docker build -t voting-challenge-web .
docker run -p 8090:80 voting-challenge-web
```

## Rodando a aplicação

Depois de executar a instalação acima, basta acessar as seguintes urls:

[Url da WEB](http://localhost:8090)

![image](https://github.com/Samukaii/desafio-votacao/assets/54710691/da98a340-838f-4c43-ae87-ee84aad788dd)

[Url da documentação da API](http://localhost:8080/swagger-ui/index.html#)

![image](https://github.com/Samukaii/desafio-votacao/assets/54710691/e12aa178-124a-4fc3-9880-76a9740eff7c)

