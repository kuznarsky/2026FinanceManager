import javax.swing.*;
import java.awt.*;

public class AddTransactionDialog extends JDialog {
    private JTextField amountField = new JTextField(10);
    private JComboBox<String> typeCombo = new JComboBox<>(new String[]{"Income", "Expense"});
    private JTextField categoryField = new JTextField(10);
    private JTextField descField = new JTextField(10);

    private Transaction resultTransaction = null;
    private boolean confirmed = false;

    public addTransactionDialog(JFrame parent) {
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
    }
}
