# 💻 Banco Digital

Simulação simples de um sistema bancário digital desenvolvido em Java, utilizando princípios de orientação a objetos. O projeto permite criação de contas, autenticação de clientes, depósitos, saques e transferências — com uma arquitetura limpa baseada em interfaces e serviços. Utiliza **Lombok** para reduzir o código repetitivo e **Mermaid** para o diagrama UML.

---

## 📦 Funcionalidades

- ✅ Criar contas correntes e poupança
- ✅ Registrar e autenticar clientes
- ✅ Realizar depósitos, saques e transferências
- ✅ Acessar contas por filtros usando Stream
- ✅ Arquitetura desacoplada com interfaces e serviços
- ✅ Diagrama UML com Mermaid
- ✅ Uso do Lombok para gerar getters/setters automaticamente

---

## 🧱 Tecnologias

- Java 17+
- Lombok
- API de Collections (Streams, HashSet)
- Mermaid (UML no GitHub)

---

> Certifique-se de ter o [Lombok](https://projectlombok.org/setup/java) corretamente configurado em seu ambiente.

## 📐 Diagrama UML

```mermaid
        classDiagram
direction TB

class IAuthenticatable {
    <<interface>>
    +authenticate(password: String): boolean
}

class Client {
    -id: String
    -name: String
    -email: String
    -password: String
    +authenticate(email: String, password: String): boolean
}

class Account {
    <<abstract>>
    -number: String
    -balance: double
    -client: Client
    +credit(amount: double)
    +debit(amount: double): double
    +getMoney(): double
    +getNumber(): String
    +getClient(): Client
}

class CheckingAccount {
    +debit(amount: double): double
}

class SavingsAccount {
    +debit(amount: double): double
}

class Bank {
    -clients: Set~Account~
    +createAccount(number: String, client: Client, accountType: AccountTypes)
    +deposit(accountNumber: String, amount: double)
    +withdraw(accountNumber: String, amount: double): double
    +transfer(fromAccount: String, toAccount: String, amount: double)
    +viewAccount(accountNumber: String, name: String, email: String)
    +findAccount(accountNumber: String): Predicate~Account~
    +findAccountByNumber(number: String): Account
    +getAccountsByClient(client: Client): Set~Account~
}

class IAuthenticationService {
    <<interface>>
    +login(email: String, password: String): boolean
    +register(client: Client)
    +getAuthenticatedClient(): Client
}

class AuthenticationService {
    -clients: Set~Client~
    -authenticatedClient: Client
    +login(email: String, password: String): boolean
    +register(client: Client)
    +getAuthenticatedClient(): Client
}

IAuthenticatable <|.. Client
Account <|-- CheckingAccount
Account <|-- SavingsAccount
Bank --> Account : manages >
IAuthenticationService <|.. AuthenticationService



```

## 📄 Licença

Este projeto é open-source e está licenciado sob a [MIT License](LICENSE).

---

> Desenvolvido com ☕ por [Lucas Gabriel](https://github.com/luk-z0)
