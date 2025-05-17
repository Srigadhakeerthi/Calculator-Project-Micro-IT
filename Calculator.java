import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Calculator extends JFrame implements ActionListener {
    private JTextField display;
    private double num1 = 0, num2 = 0;
    private String operator = "";

    public Calculator() {
        setTitle("Calculator");
        setSize(400, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        display = new JTextField();
        display.setFont(new Font("Arial", Font.PLAIN, 24));
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setEditable(false);

        String[] buttons = {
            "C", "√", "x²", "1/x",
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", ".", "=", "+"
        };

        JPanel panel = new JPanel(new GridLayout(5, 4, 5, 5));
        for (String text : buttons) {
            JButton btn = new JButton(text);
            btn.setFont(new Font("Arial", Font.BOLD, 18));
            btn.addActionListener(this);
            panel.add(btn);
        }

        add(display, BorderLayout.NORTH);
        add(panel, BorderLayout.CENTER);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();

        try {
            if (cmd.matches("[0-9]") || cmd.equals(".")) {
                display.setText(display.getText() + cmd);
            } else if (cmd.equals("C")) {
                display.setText("");
                num1 = num2 = 0;
                operator = "";
            } else if (cmd.equals("√")) {
                double val = Double.parseDouble(display.getText());
                display.setText(String.valueOf(Math.sqrt(val)));
            } else if (cmd.equals("x²")) {
                double val = Double.parseDouble(display.getText());
                display.setText(String.valueOf(val * val));
            } else if (cmd.equals("1/x")) {
                double val = Double.parseDouble(display.getText());
                if (val != 0)
                    display.setText(String.valueOf(1 / val));
                else
                    display.setText("Error");
            } else if (cmd.equals("=")) {
                num2 = Double.parseDouble(display.getText());
                double result = switch (operator) {
                    case "+" -> num1 + num2;
                    case "-" -> num1 - num2;
                    case "*" -> num1 * num2;
                    case "/" -> num2 != 0 ? num1 / num2 : Double.NaN;
                    default -> 0;
                };
                display.setText(String.valueOf(result));
            } else {
                num1 = Double.parseDouble(display.getText());
                operator = cmd;
                display.setText("");
            }
        } catch (Exception ex) {
            display.setText("Error");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Calculator::new);
    }
}
