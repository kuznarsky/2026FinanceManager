import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class FinanceManager {
    private List<Transaction> transactions;
    private static final String DATA_FILE = "finance_data.txt";

    public FinanceManager() {
        this.transactions = new ArrayList<>();
    }

    public void addTransaction(Transaction t) {
        transactions.add(t);
    }

    public double getTotalBalance() {
        double balance = 0;
        for (Transaction t : transactions) {
            balance += t.getBalanceChange();
        }
        return balance;
    }

    public List<Transaction> getTransactions() {
        return new ArrayList<>(transactions);
    }

    public void saveToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(DATA_FILE))) {
            for (Transaction t : transactions) {
                String type = (t instanceof Income) ? "INCOME" : "EXPENSE";
                writer.println(type + "|" + t.getAmount() + "|" + t.getDescription() + "|" + t.getDate());
            }
        } catch (IOException e) {
            System.err.println("Error saving data: " + e.getMessage());
        }
    }
}
