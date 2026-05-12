import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class StatisticsWindow extends JDialog {

    public StatisticsWindow(JFrame parent, FinanceManager manager) {
        super(parent, "Financial Statistics", true);
        setSize(400, 300);
        setLayout(new BorderLayout());

        Map<String, Double> categoryTotals = new HashMap<>();
        double totalExpenses = 0;
    }
}
