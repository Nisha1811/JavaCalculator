import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Calculator extends JFrame implements ActionListener {

    JTextField inputField;
    double num1, num2, result;
    String operator;

    public Calculator() {
        setTitle("Simple Calculator");
        setSize(350, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        inputField = new JTextField();
        inputField.setEditable(false);
        inputField.setFont(new Font("Arial", Font.BOLD, 24));
        add(inputField, BorderLayout.NORTH);

        String[] buttons = {
            "7", "8", "9", "/", 
            "4", "5", "6", "*", 
            "1", "2", "3", "-", 
            "0", ".", "=", "+",
            "C"
        };

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(5, 4, 5, 5));

        for (String text : buttons) {
            JButton button = new JButton(text);
            button.setFont(new Font("Arial", Font.BOLD, 20));
            button.addActionListener(this);
            buttonPanel.add(button);
        }

        add(buttonPanel, BorderLayout.CENTER);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.matches("[0-9.]")) {
            inputField.setText(inputField.getText() + command);
        } else if (command.matches("[+\\-*/]")) {
            try {
                num1 = Double.parseDouble(inputField.getText());
                operator = command;
                inputField.setText("");
            } catch (NumberFormatException ex) {
                inputField.setText("Error");
            }
        } else if (command.equals("=")) {
            try {
                num2 = Double.parseDouble(inputField.getText());
                switch (operator) {
                    case "+": result = num1 + num2; break;
                    case "-": result = num1 - num2; break;
                    case "*": result = num1 * num2; break;
                    case "/": result = num2 != 0 ? num1 / num2 : 0; break;
                }
                inputField.setText("" + result);
            } catch (Exception ex) {
                inputField.setText("Error");
            }
        } else if (command.equals("C")) {
            inputField.setText("");
        }
    }

    public static void main(String[] args) {
        new Calculator();
    }
}
