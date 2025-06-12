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
arqui_hexagonal_spring/ ├── core/ │ ├── domain/ # Entidades puras do negócio (Cliente, Pedido) │ ├── ports/ # Interfaces de entrada e saída │ └── services/ # Regras de negócio implementadas │ ├── adapters/ │ ├── inbound/ # Entrada do sistema (Controllers, DTOs) │ └── outbound/ # Saída do sistema (repositórios JPA, entidades DB) │ ├── adapters/ # Implementações dos repositórios de domínio │ ├── entities/ # Entidades JPA │ └── repositories/ # Interfaces JpaRepository │ ├── config/ # Configurações gerais (ex: Swagger) └── ArquiHexagonalSpringApplication.java
```

## 🧠 Regras de Negócio

