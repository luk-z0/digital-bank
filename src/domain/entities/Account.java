package domain.entities;

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

    double getBalance() {
        return this.money;
    }

    String getNumber() {
        return this.number;
    }

    Client getClient() {
        return this.client;
    }

    // TODO Implement to verify Account Type to new business rule
    // abstract AccountTypes getAccountType();

    @Override
    public String toString() {
        return "Account [number=" + number + ", money=" + money + ", client=" + client + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((number == null) ? 0 : number.hashCode());
        long temp;
        temp = Double.doubleToLongBits(money);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        result = prime * result + ((client == null) ? 0 : client.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Account other = (Account) obj;
        if (number == null) {
            if (other.number != null)
                return false;
        } else if (!number.equals(other.number))
            return false;
        if (Double.doubleToLongBits(money) != Double.doubleToLongBits(other.money))
            return false;
        if (client == null) {
            if (other.client != null)
                return false;
        } else if (!client.equals(other.client))
            return false;
        return true;
    }

}
