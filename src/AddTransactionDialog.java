import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

public class AddTransactionDialog extends JDialog {
    private JTextField amountField = new JTextField(10);
    private JComboBox<String> typeCombo = new JComboBox<>(new String[]{"Income", "Expense"});
    private JTextField categoryField = new JTextField(10);
    private JTextField descField = new JTextField(10);

    private Transaction resultTransaction = null;
    private boolean confirmed = false;

    public AddTransactionDialog(JFrame parent) {
        super(parent, "Add New Transaction", true);
        setLayout(new GridLayout(5, 2, 10, 10));

        add(new JLabel("Type:"));
        add(typeCombo);

        add(new JLabel("Amount:"));
        add(amountField);

        add(new JLabel("Category:"));
        add(categoryField);

        add(new JLabel("Description:"));
        add(descField);

        JButton saveBtn = new JButton("Save");
        saveBtn.addActionListener(e -> handleSave());

        JButton cancelBtn = new JButton("Cancel");
        cancelBtn.addActionListener(e -> dispose());

        add(saveBtn);
        add(cancelBtn);

        pack();

        setLocationRelativeTo(parent);
    }

    private void handleSave() {
        try {
            double amount = Double.parseDouble(amountField.getText());
            if (amount <= 0) throw new IllegalArgumentException("Amount must be positive.");

            String category = categoryField.getText();
            String desc = descField.getText();
            LocalDate date = LocalDate.now();

            if (typeCombo.getSelectedItem().equals("Income")) {
                resultTransaction = new Income(amount, category, desc, date);
            } else {
                resultTransaction = new Expense(amount, category, desc, date);
            }

            confirmed = true;
            dispose();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number for amount", "Input Error", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public Transaction getResult() {
        return resultTransaction;
    }

    public boolean isConfirmed() {
        return confirmed;
    }
}
