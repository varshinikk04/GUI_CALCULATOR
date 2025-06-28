import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CalculatorApp extends JFrame implements ActionListener {
    private JTextField display;
    private double num1 = 0, num2 = 0;
    private char operator = ' ';

    public CalculatorApp() {
        setTitle("Calculator");
        setSize(300, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Display box
        display = new JTextField();
        display.setEditable(false);
        display.setFont(new Font("Segoe UI", Font.PLAIN, 24));
        add(display, BorderLayout.NORTH);

        // Button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(5, 4));

        String[] buttons = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", ".", "=", "+",
            "C", "X", "^", "√"
        };

        for (String text : buttons) {
            JButton button = new JButton(text);
            button.setFont(new Font("Arial", Font.BOLD, 18));
            button.addActionListener(this);
            buttonPanel.add(button);
        }

        add(buttonPanel, BorderLayout.CENTER);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String input = e.getActionCommand();

        if (input.matches("[0-9]") || input.equals(".")) {
            display.setText(display.getText() + input);
        }
        else if ("+-*/%^".contains(input)) {
            try {
                num1 = Double.parseDouble(display.getText());
                operator = input.charAt(0);
                display.setText(""); // clear for next number
            } catch (NumberFormatException ex) {
                display.setText("Error");
            }
        }
        else if (input.equals("=")) {
            try {
                num2 = Double.parseDouble(display.getText());
                double result = 0;

                if (operator == '+') result = num1 + num2;
                else if (operator == '-') result = num1 - num2;
                else if (operator == '*') result = num1 * num2;
                else if (operator == '/') {
                    if (num2 == 0) {
                        display.setText("Divide by 0");
                        return;
                    }
                    result = num1 / num2;
                }
                else if (operator == '%') result = num1 % num2;
                else if (operator == '^') result = Math.pow(num1, num2);

                display.setText(String.valueOf(result));
                operator = ' ';
            } catch (NumberFormatException ex) {
                display.setText("Error");
            }
        }
        else if (input.equals("√")) {
            try {
                double value = Double.parseDouble(display.getText());
                if (value < 0) display.setText("Invalid");
                else display.setText(String.valueOf(Math.sqrt(value)));
            } catch (NumberFormatException ex) {
                display.setText("Error");
            }
        }
        else if (input.equals("C")) {
            display.setText("");
            num1 = num2 = 0;
            operator = ' ';
        }
        else if (input.equals("X")) { // ⌫ backspace
            String current = display.getText();
            if (!current.isEmpty()) {
                display.setText(current.substring(0, current.length() - 1));
            }
        }
    }

    public static void main(String[] args) {
        new CalculatorApp();
    }
}
