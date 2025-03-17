<p align="center">
  <img src="https://i.imgur.com/ogXxgso.png" alt="lotocheck" width="150">
  <h1 align="center">LotoCheck</h1>
</p>

Este projeto foi desenvolvido com o objetivo de gerenciar grandes quantias de apostas, focando especificamente em jogos como **Mega Sena** e **Lotofácil**. A aplicação organiza as apostas realizadas, registra os números sorteados em cada concurso e verifica os resultados de forma eficiente e precisa, garantindo uma gestão simples e confiável. Todo o sistema é estruturado com um modelo de dados bem planejado e regras de negócio claramente definidas.

## 🚀 Tecnologias Utilizadas

- Java 17
- Spring Boot
- PostgreSQL
- Swagger
- Docker
- Maven

## 📌 Endpoints
Para uma documentação sólida dos endpoints, execute a aplicação e acesse:
**localhost:8080/swagger-ui/index.html#/**
### BET (Apostas)

- Adicionar nova aposta no bolão
  
  **POST** `/bet/{poolId}`

_Request Body:_
```json
{
  "betNumbers": [0],
  "gameType": "string"
}
```

- Encontrar apostas no bolão (com paginação)
  
  **GET** `/bet/{poolId}/?size={size}&page={page}`

- Deletar aposta

  **DELETE** `/bet/{betId}`

---

### POOL (Bolões)

- Criar Bolão

  **POST** `/pool/create`

_Request Body:_
```json
{
  "title": "string",
  "type": "string"
}
```

- Encontrar todos os bolões

  **GET** `/pool`

- Remover bolão

  **DELETE** `/pool/{poolId}`

---

### CONTEST (Concursos)

- Adicionar concurso ao bolão
  
  **POST** `/contest/{poolID}`

_Request Body:_
```json
{
  "codeContest": "string",
  "drawNumbers": [0]
}
```
## 🚀 Passos para execução  
📥 Pré-requisitos:
Certifique-se de que possui os seguintes requisitos instalados no seu sistema:

- [Docker Compose](https://docs.docker.com/compose/install/)

### 1️⃣ Clone o repositório  
```sh
git clone https://github.com/FilipeKevyn/Loteria-Backend.git
cd seu-repositorio
```

### 2️⃣ Executar aplicação via Docker Compose
```sh
docker-compose up -d
```

## 🌐 Interface Gráfica

Para ter acesso a interface gráfica do projeto, basta acessar o repositório e rodar localmente junto desta aplicação.

👉 [Acesse o repositório](https://github.com/Thoomaz/loteria-frontend)
