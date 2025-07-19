package domain.entities;

public class CheckingAccount extends Account {
    private static final double OVERDRAFT_LIMIT = 200.0;

    public CheckingAccount(String number, Client client) {
        super(number, client);
    }

    @Override
    public double debit(double value) {
        if (value <= 0) {
            System.out.println("Value must be positive.");
            return 0d;
        }
        if (this.money + OVERDRAFT_LIMIT < value) {
            System.out.println("Overdraft limit exceeded.");
            return 0d;
        }
        return super.debit(value);
    }

}
