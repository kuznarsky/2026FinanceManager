import java.time.LocalDate;

public abstract class Transaction {
    protected double amount;
    protected String category;
    protected String description;
    protected LocalDate date;

    public Transaction(double amount, String category, String description, LocalDate date) {
        this.amount = amount;
        this.category = category;
        this.description = description;
        this.date = date;
    }

    public abstract double getBalanceChange();

    public double getAmount() {
        return amount;
    }

    public String getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getDate() {
        return date;
    }
}
