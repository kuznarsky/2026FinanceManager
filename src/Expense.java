import java.time.LocalDate;

public class Expense extends Transaction {

    public Expense(double amount, String category, String description, LocalDate date) {
        super(amount, category, description, date);
    }

    @Override
    public double getBalanceChange() {
        return -amount;
    }
}
