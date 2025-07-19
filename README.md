## Getting Started

Welcome to the VS Code Java world. Here is a guideline to help you get started to write Java code in Visual Studio Code.

## Folder Structure

The workspace contains two folders by default, where:

-   `src`: the folder to maintain sources
-   `lib`: the folder to maintain dependencies

Meanwhile, the compiled output files will be generated in the `bin` folder by default.

> If you want to customize the folder structure, open `.vscode/settings.json` and update the related settings there.

## Dependency Management

The `JAVA PROJECTS` view allows you to manage your dependencies. More details can be found [here](https://github.com/microsoft/vscode-java-dependency#manage-dependencies).

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
