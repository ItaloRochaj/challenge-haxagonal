
# 🧱 Challenge Hexagonal API (Spring Boot + Docker)

Este projeto é uma aplicação de exemplo para estudo e demonstração da **Arquitetura Hexagonal** (também conhecida como Ports and Adapters), desenvolvida com **Java 21**, **Spring Boot 3.5**, **PostgreSQL**, **Docker** e **Swagger**.

Ele implementa um sistema de gerenciamento de **clientes** e **pedidos**, incorporando regras de negócio relevantes como atribuição automática de tipo de cliente e restrições de data nos pedidos.

---

## 📐 Arquitetura Utilizada: Hexagonal (Ports and Adapters)

A Arquitetura Hexagonal tem como objetivo central **desacoplar a lógica de negócio das interfaces externas**, tais como banco de dados, interfaces web e APIs REST. Ela se baseia nos seguintes pilares:

### 🎯 Conceitos-chave

- **Domínio independente de tecnologia**  
  A lógica de negócio (entidades, regras e serviços) não conhece nenhuma biblioteca/framework externo.

- **Portas (Interfaces)**  
  Definem como o núcleo se comunica com o mundo externo (entrada) e o que ele exige do exterior (saída).

- **Adaptadores (Implementações)**  
  Conectam o domínio às tecnologias reais, como o banco de dados e a camada REST.

---

## 🧱 Estrutura de Pastas

```

arqui\_hexagonal\_spring/
├── gradle/
├── src/
│   ├── main/
│   │   ├── java/arqui\_hexagonal\_spring/
│   │   │   ├── adapters/
│   │   │   │   ├── inbound/
│   │   │   │   │   ├── controllers/
│   │   │   │   │   │   ├── ClienteController.java
│   │   │   │   │   │   └── PedidoController.java
│   │   │   │   │   └── dtos/
│   │   │   │   │       ├── ClienteDTO.java
│   │   │   │   │       └── PedidoDTO.java
│   │   │   │   └── outbound/
│   │   │   │       ├── adapters/
│   │   │   │       │   ├── ClienteRepositoryAdapter.java
│   │   │   │       │   └── PedidoRepositoryAdapter.java
│   │   │   │       ├── entities/
│   │   │   │       │   ├── ClienteEntity.java
│   │   │   │       │   └── PedidoEntity.java
│   │   │   │       └── repositories/
│   │   │   │           ├── ClienteJpaRepository.java
│   │   │   │           └── PedidoJpaRepository.java
│   │   │   ├── application/
│   │   │   │   └── usecases/
│   │   │   │       ├── CreatePedidoUseCase.java
│   │   │   │       └── UpdateClienteUseCase.java
│   │   │   ├── core/
│   │   │   │   ├── domain/
│   │   │   │   │   ├── Cliente.java
│   │   │   │   │   └── Pedido.java
│   │   │   │   ├── ports/
│   │   │   │   │   ├── inbound/
│   │   │   │   │   │   ├── ClienteServicePort.java
│   │   │   │   │   │   └── PedidoServicePort.java
│   │   │   │   │   └── outbound/
│   │   │   │   │       ├── ClienteRepositoryPort.java
│   │   │   │   │       └── PedidoRepositoryPort.java
│   │   │   ├── services/
│   │   │   │   ├── ClienteServiceImpl.java
│   │   │   │   └── PedidoServiceImpl.java
│   │   │   └── ArquiHexagonalSpringApplication.java
│   │   └── resources/
│   │       ├── application.properties
│   │       └── application.yml
│   └── test/
│       └── java/arqui\_hexagonal\_spring/
├── .gitattributes
├── .gitignore
├── Dockerfile
├── README.md
├── build.gradle
├── docker-compose.yml
├── gradlew
├── gradlew\.bat
└── settings.gradle

````

---

## 🧠 Regras de Negócio

### Clientes

- Clientes com email `@company.com` são automaticamente classificados como **PREMIUM**.
- Demais clientes são classificados como **REGULAR**.

### Pedidos

- Pedidos com mais de **30 dias** não podem ser criados.
- Clientes do tipo **PREMIUM** recebem **10% de desconto automático**.

---

## 📄 Endpoints da API

### 📁 Clientes

- `POST /clientes` – Criar cliente  
  ```json
  {
    "nome": "Italo Rocha",
    "email": "italo@company.com"
  }
````

* `DELETE /clientes/{id}` – Remover cliente

### 📁 Pedidos

* `POST /pedidos` – Criar pedido

  ```json
  {
    "descricao": "Compra de livro",
    "data": "2025-06-10",
    "clienteId": 1
  }
  ```

* `GET /pedidos` – Listar pedidos

* `GET /pedidos/{id}` – Buscar por ID

* `PUT /pedidos/{id}` – Atualizar pedido

  ```json
  {
    "descricao": "Pedido ajustado",
    "data": "2025-06-11",
    "clienteId": 1
  }
  ```

* `DELETE /pedidos/{id}` – Remover pedido

---

## 🐳 Docker e Execução

### Subir os containers

```bash
docker compose up --build
```

### Containers disponíveis

* `hexagonal_app` – Aplicação Java/Spring
* `postgres_hexagonal` – Banco de dados PostgreSQL
* `pgadmin_hexagonal` – Interface web de gerenciamento de DB

---

## 🐘 pgAdmin - Acesso

* Acesse: [http://localhost:5051](http://localhost:5051)

* **Login:**

  * Email: `italo@outlook.com`
  * Senha: `admin`

* **Novo servidor:**

  * Host: `postgres_hexagonal`
  * Porta: `5432`
  * Usuário: `postgres`
  * Senha: `postgres`

---

## 📚 Swagger e Documentação

* Documentação interativa: [http://localhost:8082/swagger-ui.html](http://localhost:8082/swagger-ui.html)
* OpenAPI Spec (JSON/YAML): [http://localhost:8082/v3/api-docs](http://localhost:8082/v3/api-docs)

---

## 🧪 Testes Unitários

Os testes da camada de serviço utilizam **JUnit 5 + Mockito**, cobrindo:

* Lógica de classificação de clientes
* Regras de desconto em pedidos
* Validações de data

### Rodar os testes

```bash
./gradlew test
```

### Relatório visual

* `build/reports/tests/test/index.html`

---

## 🔧 Melhorias aplicadas durante o desenvolvimento

* Remoção de `BeanConfig` duplicado que causava conflito na injeção de dependência
* Correção do `bootJar` com atributo `mainClass` no manifest
* Alinhamento da porta interna/externa no `docker-compose.yml`
* Integração de Swagger via Springdoc
* Testes unitários + relatório HTML
* Postman separado por método e estrutura clara dos endpoints

---

## 🌍 Aplicação na Vida Real

Este projeto serve como exemplo para:

* Sistemas **CRM**
* Serviços **REST** modulares
* Bases para **microserviços**
* Projetos acadêmicos com separação de camadas e boas práticas

Com sua estrutura, ele está pronto para evoluir com segurança para contextos mais complexos.

---

## 👨‍💻 Autor

Desenvolvido por **Ítalo Rocha**


