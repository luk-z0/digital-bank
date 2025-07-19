package domain.entities;

import lombok.Data;

@Data
public abstract class Account {
    protected String number;
    protected double money;
    protected Client client;

    Account(String number, Client client) {
        this.number = number;
        this.client = client;
    }
    
    void credit(double amount) {
        this.money += amount;
    }

    double debit(double amount) {
        this.money -= amount;
        return this.money;
    }

    // TODO Implement to verify Account Type to new business rule
    // abstract AccountTypes getAccountType();
}
