import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;

public class Main extends JFrame {
    private FinanceManager manager = new FinanceManager();
    private JLabel balanceLabel;
    private JTable table;
    private DefaultTableModel tableModel;

    public Main() {
        setTitle("Personal Finance Manager");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        manager.addTransaction(new Income(500, "Salary", "Monthly pay", LocalDate.now()));
        manager.addTransaction(new Expense(1200, "Rent", "Apartment rent", LocalDate.now()));

        balanceLabel = new JLabel("Total balance: 0.00 $");
        balanceLabel.setFont(new Font("Arial", Font.BOLD, 18));
        updateBalanceDisplay();

        String[] columns = {"Type", "Amount", "Description", "Date"};
        tableModel = new DefaultTableModel(columns, 0);
        table = new JTable(tableModel);
        refreshTableData();

        setLayout(new BorderLayout(10, 10));

        JPanel topPanel = new JPanel();
        topPanel.add(balanceLabel);
        add(topPanel, BorderLayout.NORTH);

        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("Add Transaction");
        JButton statsButton = new JButton("View Statistics");
        JButton refreshButton = new JButton("Refresh");

        addButton.addActionListener(e -> openAddTransactionWindow());
        statsButton.addActionListener(e -> { StatisticsWindow stats = new StatisticsWindow(this, manager); stats.setVisible(true); });
        refreshButton.addActionListener(e -> refreshStats());

        buttonPanel.add(addButton);
        buttonPanel.add(statsButton);
        buttonPanel.add(refreshButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void updateBalanceDisplay() {
        double balance = manager.getTotalBalance();

        balanceLabel.setText(String.format("Total balance: %.2f $", balance));
        balanceLabel.setForeground(balance >= 0 ? new Color(0,120,0) : Color.RED);
    }

    private void refreshTableData() {
        tableModel.setRowCount(0);
        for (Transaction t : manager.getTransactions()) {
            String type = (t instanceof Income) ? "Income" : "Expense";
            Object[] row = {type, t.getAmount(), t.getDescription(), t.getDate()};
            tableModel.addRow(row);
        }
    }

    private void openAddTransactionWindow() {
        AddTransactionDialog dialog = new AddTransactionDialog(this);
        dialog.setVisible(true);

        if (dialog.isConfirmed()) {
            Transaction newT = dialog.getResult();

            manager.addTransaction(newT);
            manager.saveToFile();
            refreshTableData();
            updateBalanceDisplay();
        }
    }

    private void refreshStats() {
        refreshTableData();
        updateBalanceDisplay();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Main().setVisible(true);
        });
    }
}