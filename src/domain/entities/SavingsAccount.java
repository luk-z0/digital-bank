package domain.entities;

public class SavingsAccount extends Account {

    private static final int MAX_WITHDRAWALS_PER_MONTH = 3;
    private int withdrawalsThisMonth = 0;

    public SavingsAccount(String number, Client client) {
        super(number, client);
    }

    public void resetMonthlyWithdrawals() {
        this.withdrawalsThisMonth = 0;
    }

    @Override
    public double debit(double value) {
        if (MAX_WITHDRAWALS_PER_MONTH >= withdrawalsThisMonth) {
            System.out.println("Withdrawal limit exceeded in monthly.");
            return 0.0d;
        }
        if (value <= 0) {
            System.out.println("Value must be positive.");
            return 0.0d;
        }
        if (this.money < value) {
            System.out.println("Insufficient funds.");
            return 0.0d;
        }
        this.withdrawalsThisMonth++;
        return super.debit(value);
    }

}
