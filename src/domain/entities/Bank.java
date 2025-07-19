package domain.entities;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import domain.enums.AccountTypes;
import domain.services.AuthenticationService;

public class Bank {

    private Set<Account> clients;

    public Bank() {
        this.clients = new HashSet<>();
    }

    public void createAccount(String number, Client client, AccountTypes accountTypes) {
        if (AccountTypes.CHECKING == accountTypes) {
            this.clients.add(new CheckingAccount(number, client));
        }
        this.clients.add(new SavingsAccount(number, client));
    }

    public void deposit(String acc, double value) {
        clients.stream()
                .filter(this.findAccount(acc))
                .forEach(account -> account.credit(value));
    }

    public void withdraw(String acc, double value) {
        clients.stream()
                .filter(this.findAccount(acc))
                .findFirst()
                .map(a -> {
                    return a.debit(value);
                })
                .orElse(0.0d);

    }

    public void transfer(String start, String destination, double value) {
        Account from = findAccountByNumber(start);

        if (from.getMoney() < value) {
            System.out.println("Insufficient balance in the source account.");
            return;
        }

        this.withdraw(start, value);
        this.deposit(destination, value);
    }

    public void viewAccount(String acc, String name, String email) {
        clients.stream()
                .filter(this.findAccount(acc)
                        .and(a -> a.getClient().getName().contains(name) &&
                                a.getClient().getEmail().contains(email)))
                .forEach(System.out::println);
    }

    public Predicate<Account> findAccount(String acc) {
        return account -> account.getNumber().contains(acc);

    }

    public Account findAccountByNumber(String number) {
        return clients.stream()
                .filter(this.findAccount(number))
                .findFirst().get();
    }

    public Set<Account> getAccountsByClient(Client client) {
        return clients.stream()
                .filter(acc -> acc.getClient().equals(client))
                .collect(Collectors.toSet());
    }

    public static void main(String[] args) {
        Bank bank = new Bank();
        AuthenticationService auth = new AuthenticationService();

        Client lucas = new Client("1", "Lucas", "lucas@teste.com", "pass");
        Client debora = new Client("2", "Débora", "debora@teste.com", "pass");

        auth.register(lucas);
        auth.register(debora);

        bank.createAccount("1", lucas, AccountTypes.CHECKING);
        bank.createAccount("2", debora, AccountTypes.SAVINGS);

        if (auth.login("lucas@teste.com", "pass")) {
            Client loggedIn = auth.getAuthenticatedClient();

            Set<Account> accounts = bank.getAccountsByClient(loggedIn);

            accounts.forEach(acc -> {
                bank.deposit(acc.getNumber(), 500.0);
                System.out.println("Deposited to account: " + acc.getNumber());
            });
        } else {
            System.out.println("Login failed.");
        }
    }
}
