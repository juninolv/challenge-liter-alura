# Liter Alura

**LiterAlura** é uma aplicação de linha de comando (CLI) desenvolvida em Java com Spring Boot, que permite buscar, registrar e listar livros e autores de domínio público utilizando a API [Gutendex](https://gutendex.com/). O projeto foi criado como parte do Challenge Backend da Alura em parceria com o programa Oracle ONE.

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-F2F4F9?style=for-the-badge&logo=spring-boot)
![Maven](https://img.shields.io/badge/Apache_Maven-C71A36?style=for-the-badge&logo=Apache-Maven&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-316192?style=for-the-badge&logo=postgresql&logoColor=white)

## Sobre o Projeto

O LiterAlura facilita a busca e gestão de livros e autores, permitindo que os usuários:
- Busquem livros por título da Gutendex API.
- Funcionalidades para livros:
  - Top 10 Downloads
  - Todos
  - Por idioma
  - Por assunto
- Funcionalidades para autores:
  - Todos
  - Todos vivos - em um determinado ano
  - Todos mortos - em um determinado ano
  - Por nome

---

## Tecnologias Utilizadas

- **Java 21**
- **Maven**
- **Spring Boot**
- **Spring Data JPA**
- **PostgreSQL**
- **Gutendex API**

---

## Pré-requisitos

Antes de executar o projeto, certifique-se de ter instalado:
- [Java 21](https://adoptium.net/pt-BR/temurin/releases?version=21&os=any&arch=any)
- [Maven](hhttps://maven.apache.org/download.cgi)
- [PostgreSQL](https://www.postgresql.org/)

---

## Como Executar o Projeto

1. **Clone o repositório:**
   ```bash
   git clone https://github.com/juninolv/challenge-liter-alura.git
   cd challenge-liter-alura
   git checkout dev
   ```

2. **Configure o banco de dados:**
- Crie um banco de dados PostgreSQL chamado `literalura`.
- Crie um arquivo `src/main/resources/application-dev.properties` e adicione as propriedades:
  ```properties
  db.database=postgres
  db.username=postgres
  db.passwd=12345
  ```

3. **Execute a aplicação:**
   ```bash
   mvn spring-boot:run
   ```

4. **Acesse a aplicação:**
   - A aplicação é executada no terminal e apresenta um menu interativo.

---

## Funcionalidades

| Opção | Descrição |
|-------|-----------|
| 1 | Buscar livro por título |
| 2 | Funcionalidades para livros |
| 2.1 | Top 10 Downloads |
| 2.2 | Todos |
| 2.3 | Por idioma |
| 2.4 | Por assunto |
| 3 | Funcionalidades para autores |
| 3.1 | Todos |
| 3.2 | Todos vivos - em um determinado ano |
| 3.3 | Todos mortos - em um determinado ano |
| 3.4 | Por nome |
| 0 | Sair |

---

## Estrutura do Projeto

```
.
├── mvnw
├── mvnw.cmd
├── pom.xml
├── README.md
└── src
    └── main
        ├── java
        │   └── com.oracle.cli
        │       ├── dto
        │       │   ├── AuthorDto.java
        │       │   ├── BookDto.java
        │       │   └── BookResDto.java
        │       ├── model
        │       │   ├── Author.java
        │       │   ├── Book.java
        │       │   └── ScreenSelector.java
        │       ├── service
        │       │   ├── AuthorService.java
        │       │   ├── BookService.java
        │       │   ├── ScreenService.java
        │       │   └── SearchBookService.java
        │       ├── util
        │       │   ├── config
        │       │   │   ├── ApplicationConfig.java
        │       │   │   ├── AuthorScreensConfig.java
        │       │   │   ├── BookScreensConfig.java
        │       │   │   ├── ScreensConfig.java
        │       │   │   └── SearchScreensConfig.jav
        │       │   ├── exception
        │       │   │   └── ExitException.java
        │       │   ├── mapper
        │       │   │   ├── AuthorMapper.java
        │       │   │   └── BookMapper.java
        │       │   └── Http.java
        │       ├── view
        │       │   ├── author
        │       │   │   ├── AuthorsAllDeadView.java
        │       │   │   ├── AuthorsAllLivingView.java
        │       │   │   ├── AuthorsAllView.java
        │       │   │   ├── AuthorsByNameView.java
        │       │   │   └── AuthorsView.java
        │       │   ├── book
        │       │   │   ├── BookMostDownloadView.java
        │       │   │   ├── BooksAllView.java
        │       │   │   ├── BooksByLanguageView.java
        │       │   │   ├── BooksBySubjectView.java
        │       │   │   └── BooksView.java
        │       │   ├── search
        │       │   │   └── SearchBooksView.java
        │       │   ├── HomeView.java
        │       │   └── ScreenBase.java
        │       └── MainApplication.java
        └── resources
            ├── application-dev.properties
            └── application.properties
```

---

## Contato
**Juninho Oliveira** – [GitHub](https://github.com/juninolv) | [LinkedIn](https://www.linkedin.com/in/juninholv/)

---
**Star este repositório** se achou útil!
