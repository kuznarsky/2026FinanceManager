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

        manager.addTransaction(new Income(5000, "Salary", "Monthly pay", LocalDate.now()));

        balanceLabel = new JLabel("Total balance: 0.00 $");
        balanceLabel.setFont(new Font("Arial", Font.BOLD, 18));
        updateBalanceDisplay();

        String[] columns = {"Type", "Amount", "Description", "Date"};
        tableModel = new DefaultTableModel(columns, 0);
        table = new JTable(tableModel);

        setLayout(new BorderLayout(10, 10));

        JPanel topPanel = new JPanel();
        topPanel.add(balanceLabel);
        add(topPanel, BorderLayout.NORTH);
    }

    private void updateBalanceDisplay() {
        double balance = manager.getTotalBalance();

        balanceLabel.setText(String.format("Total balance: %.2f $", balance));
        balanceLabel.setForeground(balance >= 0 ? new Color(0,120,0) : Color.RED);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Main().setVisible(true);
        });
    }
}