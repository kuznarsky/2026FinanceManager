import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Main extends JFrame {
    private FinanceManager manager = new FinanceManager();
    private JLabel balanceLabel;
    private JTable table;
    private DefaultTableModel tableModel;

    public Main() {
        setTitle("Personal Finance Manager");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Main().setVisible(true);
        });

    }
}