# Craque da partida API

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![Postgres](https://img.shields.io/badge/postgres-%23316192.svg?style=for-the-badge&logo=postgresql&logoColor=white)
![Swagger](https://img.shields.io/badge/-Swagger-%23Clojure?style=for-the-badge&logo=swagger&logoColor=white)
![RabbitMQ](https://img.shields.io/badge/Rabbitmq-FF6600?style=for-the-badge&logo=rabbitmq&logoColor=white)
![Docker](https://img.shields.io/badge/docker-%230db7ed.svg?style=for-the-badge&logo=docker&logoColor=white)

<strong>📌 Sobre o projeto</strong>

Projeto criado para simular um esquema de votação de melhor jogador da partida, semelhante ao apresentado nas grandes emissoras de televisão. A api pode ser usada para criar uma enquete ou desativá-la, adicionar jogadores, realizar votos e desativar enquetes. Como é um projeto de estudo para aprender a utilizar o rabbitmq e sistemas com mensageria, a parte de editar e deletar um jogador não foi implementada, o foco principal do projeto foi na relaização das enquetes e voto.

🔧 <strong>Tecnologias utilizadas</strong>
- Java
- Spring
- RabbitMQ
- PostgreSQL
- Docker
- Swagger

<strong>🚀 Como utlizar</strong>

1 - Para iniciar o projeto clone o repositório e acesse o diretório criado
  ```bash
    git clone https://github.com/gabrielferreira02/craque-da-partida.git
    cd craque-da-partida
  ```

2 - Gere o arquivo jar usando
```bash
  mvn clean install
```

3 - Com o arquivo jar gerado basta iniciar a aplicação com docker
```bash
  docker-compose up
```

>[!NOTE]
> A aplicação conta com documentação dos endpoints usando swagger para maior compreensão da estrutura das requisições no seguinte endpoint
```bash
  http://localhost:8080/swagger-ui/index.html#/
```

  
