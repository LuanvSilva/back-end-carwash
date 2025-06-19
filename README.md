# CarWash API

![Badge](https://img.shields.io/badge/status-em%20desenvolvimento-yellow)
![Badge](https://img.shields.io/badge/linguagem-TypeScript-blue)
![Badge](https://img.shields.io/badge/framework-Node.js%20/%20Express-green)

## 📝 Breve descrição

**CarWash API** é um sistema de back-end para gerenciamento de um lava-rápido. A API permite o controle de clientes, serviços oferecidos e o agendamento desses serviços, fornecendo uma base sólida para a construção de uma aplicação cliente (web ou mobile).

## 🚀 Sobre o Projeto

Este projeto foi criado para centralizar e otimizar a gestão de um lava-rápido. A principal motivação é oferecer uma solução escalável e robusta que organize os agendamentos, facilite o cadastro de serviços e gerencie os usuários do sistema de forma segura.

## ✨ Principais Características

-   **Autenticação de Usuários**: Sistema de login seguro utilizando JSON Web Tokens (JWT).
-   **Gerenciamento de Usuários**: Operações de CRUD (Criar, Ler, Atualizar, Deletar) para usuários.
-   **Gerenciamento de Serviços**: CRUD completo para os serviços oferecidos pelo lava-rápido.
-   **Agendamento de Serviços**: Permite criar, visualizar, atualizar e cancelar agendamentos.

## 📚 Documentação

A documentação detalhada das rotas da API pode ser encontrada na coleção do Postman ou Insomnia, que pode ser gerada a partir das rotas definidas no código.

*(Esta seção pode ser expandida com links para documentações mais detalhadas, como Swagger/OpenAPI, se implementado no futuro).*

## 🏁 Guia de Início Rápido (Quick Start)

Siga os passos abaixo para configurar e executar o projeto em seu ambiente local.

### Pré-requisitos

-   [Node.js](https://nodejs.org/en/) (versão 16 ou superior)
-   [Docker](https://www.docker.com/get-started) e [Docker Compose](https://docs.docker.com/compose/install/)
-   [Git](https://git-scm.com/)
-   Um cliente de API como [Postman](https://www.postman.com/) ou [Insomnia](https://insomnia.rest/) para testar os endpoints.

### Instalação e Execução

1.  **Clone o repositório:**
    ```bash
    git clone <URL_DO_REPOSITORIO>
    cd carwash
    ```

2.  **Instale as dependências:**
    ```bash
    npm install
    ```

3.  **Configure as variáveis de ambiente:**
    -   Renomeie o arquivo `.env.example` para `.env`.
    -   Preencha as variáveis, especialmente `DATABASE_URL` e `JWT_SECRET`.

4.  **Inicie o banco de dados com Docker:**
    ```bash
    docker-compose up -d
    ```

5.  **Execute as migrações do banco de dados com Prisma:**
    ```bash
    npx prisma migrate dev
    ```

6.  **Inicie o servidor de desenvolvimento:**
    ```bash
    npm run dev
    ```

O servidor estará em execução em `http://localhost:3000`.

## 🏗️ Arquitetura da Solução

O sistema segue uma arquitetura em camadas para garantir a separação de responsabilidades, manutenibilidade e escalabilidade. Abaixo está um diagrama que ilustra o fluxo de comunicação entre os componentes:

```mermaid
graph TD
    subgraph "Cliente"
        A[Cliente API / Frontend]
    end

    subgraph "Servidor Node.js / Express"
        B(Rotas)
        C{Middleware de Autenticação}
        D[Controller]
        E[Service (Regras de Negócio)]
        F[Repository (Acesso a Dados)]
    end

    subgraph "Banco de Dados"
        G[(PostgreSQL com Prisma ORM)]
    end

    A -- Requisição HTTP --> B
    B --> C
    C -- Válido --> D
    D --> E
    E --> F
    F -- Query --> G
    G -- Resposta --> F
    F --> E
    E --> D
    D -- Resposta JSON --> A
```

### Tecnologias Utilizadas

-   **Back-end**: Node.js, TypeScript
-   **Framework**: Express.js
-   **Banco de Dados**: PostgreSQL (gerenciado via Docker)
-   **ORM**: Prisma
-   **Autenticação**: JSON Web Token (JWT)

### Estrutura do Projeto

O projeto está organizado da seguinte forma:

```
.
├── prisma/                 # Schema e migrações do Prisma
├── src/
│   ├── controllers/        # Controladores (lógica de requisição/resposta)
│   ├── middlewares/        # Middlewares do Express (ex: autenticação)
│   ├── repositories/       # Camada de acesso aos dados (comunicação com o BD)
│   ├── routes/             # Definição das rotas da API
│   ├── services/           # Camada de serviços (regras de negócio)
│   ├── utils/              # Funções utilitárias
│   └── server.ts           # Ponto de entrada da aplicação
├── .env.example            # Exemplo de variáveis de ambiente
├── docker-compose.yml      # Configuração do container do banco de dados
└── package.json            # Dependências e scripts do projeto
```

### Fluxo de uma Requisição

O fluxo de uma requisição HTTP, conforme ilustrado no diagrama, segue os seguintes passos:

1.  O **Cliente** (ex: Postman, Frontend) envia uma requisição para um endpoint da API.
2.  O **Express** recebe a requisição e a direciona para a **Rota** correspondente.
3.  O **Middleware** de autenticação intercepta a requisição para verificar se o usuário possui um token válido e as permissões necessárias.
4.  O **Controller** é acionado, validando os dados de entrada (body, params, query) e chamando o serviço apropriado.
5.  O **Service** executa a lógica de negócio principal da operação.
6.  O **Repository** é responsável por se comunicar com o banco de dados, executando as queries através do **Prisma ORM**.
7.  A resposta do banco de dados retorna pela mesma cadeia (Repository → Service → Controller), e o Controller formata a resposta JSON final para o cliente.

