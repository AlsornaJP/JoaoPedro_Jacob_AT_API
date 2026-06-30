# 🏆 Campeonato API — Gerenciamento de Equipes

API REST desenvolvida em **Java + Spring Boot** para o gerenciamento de equipes de um campeonato esportivo. O projeto implementa um CRUD completo de equipes, com validações de negócio, tratamento centralizado de exceções e persistência em banco de dados.

> Projeto desenvolvido como Avaliação Técnica (AT) da disciplina de Java Web.

---

## 📋 Índice

- [Sobre o Projeto](#-sobre-o-projeto)
- [Tecnologias Utilizadas](#-tecnologias-utilizadas)
- [Arquitetura e Práticas](#-arquitetura-e-práticas)
- [Modelo de Dados](#-modelo-de-dados)
- [Endpoints da API](#-endpoints-da-api)
- [Regras de Negócio](#-regras-de-negócio)
- [Tratamento de Erros](#-tratamento-de-erros)
- [Como Executar](#-como-executar)
- [Banco de Dados](#-banco-de-dados)
- [Testes](#-testes)
- [Estrutura de Pastas](#-estrutura-de-pastas)
- [Autor](#-autor)

---

## 📖 Sobre o Projeto

A **Campeonato API** permite cadastrar, listar, buscar, atualizar e remover equipes participantes de um campeonato. Cada equipe possui informações como nome, sigla, modalidade esportiva, categoria, capitão, técnico, quantidade de atletas, data de fundação e status de participação.

A aplicação foi construída seguindo boas práticas de desenvolvimento, com separação clara de responsabilidades em camadas (Controller → Service → Repository) e uso de DTOs para isolar a camada de persistência da camada de exposição.

---

## 🛠 Tecnologias Utilizadas

| Tecnologia | Descrição |
|------------|-----------|
| **Java 25** | Linguagem de programação |
| **Spring Boot 4.1.0** | Framework principal da aplicação |
| **Spring Web MVC** | Construção da API REST |
| **Spring Data JPA** | Persistência e abstração de acesso a dados |
| **Hibernate** | Implementação JPA / ORM |
| **H2 Database** | Banco de dados em memória |
| **Lombok** | Redução de código boilerplate (getters, construtores, etc.) |
| **Spring Boot DevTools** | Hot reload durante o desenvolvimento |
| **Maven** | Gerenciamento de dependências e build |
| **JUnit / Spring Boot Test** | Testes automatizados |

---

## 🏗 Arquitetura e Práticas

O projeto adota uma **arquitetura em camadas** com responsabilidades bem definidas:

```
Controller  →  Service  →  Repository  →  Database
                  ↓
              Validator / Mapper
```

### Boas práticas aplicadas

- **Separação em camadas (Layered Architecture):** Controller, Service, Repository, Model.
- **Padrão DTO (Data Transfer Object):** `EquipeDTO` isola a entidade JPA da camada web, evitando expor diretamente o modelo de persistência.
- **Padrão Mapper:** `EquipeMapper` centraliza a conversão entre `EquipeEntity` e `EquipeDTO`.
- **Injeção de dependência via construtor:** abordagem recomendada pelo Spring, favorecendo imutabilidade e testabilidade.
- **Validação isolada:** `EquipeValidator` concentra as regras de negócio, mantendo o Service enxuto.
- **Tratamento centralizado de exceções:** `@RestControllerAdvice` (`EquipeExceptionHandler`) trata erros de forma global e padronizada.
- **Exceções customizadas:** cada cenário de erro possui sua própria exceção semântica.
- **Entidade encapsulada:** a `EquipeEntity` controla a própria atualização através do método `atualizar()`, protegendo campos imutáveis (nome, sigla e modalidade).
- **Uso de Enums:** `Modalidade` e `Status` garantem integridade dos valores possíveis.
- **Programação funcional:** uso de Streams e referências de método na transformação de coleções.

---

## 🗃 Modelo de Dados

### Entidade `Equipe`

| Campo | Tipo | Restrições |
|-------|------|------------|
| `id` | Integer | PK, auto-incremento |
| `nome` | String | Obrigatório, único |
| `sigla` | String | Obrigatório, único, máx. 3 caracteres |
| `modalidade` | Enum | Obrigatório (`FUTEBOL`, `BASQUETE`, `HANDEBOL`) |
| `categoria` | String | Obrigatório |
| `capitao` | String | Obrigatório |
| `tecnico` | String | Obrigatório |
| `quantidadeDeAtletas` | int | Obrigatório, maior que zero |
| `fundacao` | LocalDate | Opcional |
| `status` | Enum | Obrigatório (`PARTICIPANDO`, `ELIMINADO`, `NAO_RELACIONADO`) |

### Enums

- **Modalidade:** `FUTEBOL`, `BASQUETE`, `HANDEBOL`
- **Status:** `PARTICIPANDO`, `ELIMINADO`, `NAO_RELACIONADO`

---

## 🔌 Endpoints da API

Base path: **`/equipes`**

| Método | Endpoint | Descrição | Status de sucesso |
|--------|----------|-----------|-------------------|
| `POST` | `/equipes` | Registra uma nova equipe | `201 CREATED` |
| `GET` | `/equipes` | Lista todas as equipes | `200 OK` |
| `GET` | `/equipes/{id}` | Busca uma equipe por ID | `200 OK` |
| `PUT` | `/equipes/{id}` | Atualiza os dados de uma equipe | `200 OK` |
| `DELETE` | `/equipes/{id}` | Remove uma equipe | `204 NO CONTENT` |

### Exemplo de corpo (POST / PUT)

```json
{
  "nome": "Flamengo",
  "sigla": "FLA",
  "modalidade": "FUTEBOL",
  "categoria": "Sub-20",
  "capitao": "Pedro",
  "tecnico": "Filipe Luís",
  "quantidadeDeAtletas": 22,
  "fundacao": "1895-11-17",
  "status": "PARTICIPANDO"
}
```

> ⚠️ Na atualização (`PUT`), os campos **nome**, **sigla** e **modalidade** são imutáveis — apenas categoria, capitão, técnico, quantidade de atletas, fundação e status são alterados.

---

## 📐 Regras de Negócio

- O **nome** de uma equipe não pode se repetir dentro da mesma modalidade.
- A **sigla** de uma equipe não pode se repetir dentro da mesma modalidade.
- A **quantidade de atletas** deve ser maior que zero.
- Campos obrigatórios (categoria, capitão, técnico, status) não podem ser nulos ou vazios na atualização.
- Não é possível buscar, atualizar ou deletar uma equipe inexistente.

---

## ⚠️ Tratamento de Erros

Erros são tratados de forma centralizada e retornam respostas HTTP apropriadas:

| Exceção | Situação | HTTP Status |
|---------|----------|-------------|
| `NomeAlreadyExistsException` | Nome já cadastrado na modalidade | `409 CONFLICT` |
| `SiglaAlreadyExistsException` | Sigla já cadastrada na modalidade | `409 CONFLICT` |
| `EquipeNotFoundException` | Equipe não encontrada | `404 NOT FOUND` |
| `EquipesIsEmptyException` | Nenhuma equipe cadastrada | `404 NOT FOUND` |
| `EquipeInvalidaException` | Dados inválidos na requisição | `400 BAD REQUEST` |

---

## ▶️ Como Executar

### Pré-requisitos

- **JDK 25** instalado
- **Maven** (ou utilize o wrapper `./mvnw` incluído no projeto)

### Passos

```bash
# Clone o repositório
git clone https://github.com/AlsornaJP/JoaoPedro_Jacob_AT_API.git
cd JoaoPedro_Jacob_AT_API

# Execute a aplicação
./mvnw spring-boot:run
```

A aplicação ficará disponível em:

```
http://localhost:8080
```

---

## 🗄 Banco de Dados

O projeto utiliza o **H2 Database em memória**, populado automaticamente na inicialização através do arquivo `data.sql` (5 equipes de exemplo).

### Console H2

Acesse o console web do banco em:

```
http://localhost:8080/h2
```

**Credenciais de conexão:**

| Campo | Valor |
|-------|-------|
| JDBC URL | `jdbc:h2:mem:campeonato` |
| Usuário | `jp` |
| Senha | `123` |

> Por ser um banco em memória, os dados são reiniciados a cada execução da aplicação.

---

## 🧪 Testes

O projeto inclui testes automatizados (ex.: `EquipeControllerTest`). Para executá-los:

```bash
./mvnw test
```

---

## 📁 Estrutura de Pastas

```
src/main/java/org/example/joaopedro_jacob_at_java/
├── Application.java                # Classe principal (entry point)
├── controller/
│   └── EquipeController.java       # Endpoints REST
├── service/
│   └── EquipeService.java          # Regras de aplicação
├── repository/
│   └── EquipeRepository.java       # Acesso a dados (Spring Data JPA)
├── model/
│   ├── EquipeEntity.java           # Entidade JPA
│   ├── Modalidade.java             # Enum
│   ├── Status.java                 # Enum
│   └── DTO/
│       └── EquipeDTO.java          # Data Transfer Object
├── utils/
│   ├── EquipeMapper.java           # Conversão Entity <-> DTO
│   ├── EquipeValidator.java        # Validações de negócio
│   └── EquipeExceptionHandler.java # Tratamento global de exceções
└── exception/                      # Exceções customizadas

src/main/resources/
├── application.yaml                # Configurações da aplicação
└── data.sql                        # Carga inicial de dados
```

---

## 👤 Autor

**João Pedro Jacob**

- GitHub: [@AlsornaJP](https://github.com/AlsornaJP)