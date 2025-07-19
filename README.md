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

    class IAutenticavel {
        <<interface>>
        +autenticar(senha: String): boolean
    }

    class Cliente {
        -id: String
        -nome: String
        -email: String
        -senha: String
        +autenticar(email:String, senha: String): boolean
    }

    class Conta {
        <<abstract>>
        -numero: String
        -saldo: double
        -cliente: Cliente
        +depositar(valor: double)
        +sacar(valor: double)
        +transferir(destino: Conta, valor: double)
        +consultarSaldo(): double
    }

    class ContaCorrente {
        +sacar(valor: double)
    }

    class ContaPoupanca {
        +sacar(valor: double)
    }

    class Transacao {
        -id: String
        -data: Date
        -valor: double
        -tipo: String
        -contaOrigem: Conta
        -contaDestino: Conta
    }

    class IServicoTransferencia {
        <<interface>>
        +executar(origem: Conta, destino: Conta, valor: double)
    }

    class ServicoTransferenciaImpl {
        +executar(origem: Conta, destino: Conta, valor: double)
    }

    class IAutenticacaoService {
        <<interface>>
        +login(email: String, senha: String): boolean
        +registrar(cliente: Cliente)
    }

    class AutenticacaoServiceImpl {
        +login(email: String, senha: String): boolean
        +registrar(cliente: Cliente)
    }

    IAutenticavel <|.. Cliente
    Conta <|-- ContaCorrente
    Conta <|-- ContaPoupanca
    IServicoTransferencia <|.. ServicoTransferenciaImpl
    IAutenticacaoService <|.. AutenticacaoServiceImpl


```
