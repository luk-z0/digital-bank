package domain.entities;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

import domain.enums.AccountTypes;

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

    public void withdrawal(String acc, double value) {
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

        if (from.getBalance() < value) {
            System.out.println("Insufficient balance in the source account.");
            return;
        }

        this.withdrawal(start, value);
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

    public static void main(String[] args) {
        Bank bank = new Bank();
        bank.createAccount("1", new Client("1", "Lucas", "lucas@teste.com", "pass"), AccountTypes.CHECKING);
        bank.createAccount("2", new Client("2", "Débora", "debora@teste.com", "pass"), AccountTypes.SAVINGS);

        bank.deposit("2", 100);
        bank.viewAccount("2", "Débora", "debora@teste.com");

    }
}
