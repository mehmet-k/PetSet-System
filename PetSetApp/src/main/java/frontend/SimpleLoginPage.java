package frontend;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimpleLoginPage extends JFrame {

    private JTextField userIdField;
    private JPasswordField passwordField;

    public SimpleLoginPage() {
        setTitle("Login Page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null);

        createLoginPanel();

        setVisible(true);
    }

    private void createLoginPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));

        JLabel userIdLabel = new JLabel("User ID:");
        userIdField = new JTextField();
        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField();

        JButton loginButton = new JButton("Log In");
        JButton signUpButton = new JButton("Sign Up");

        // Add action listener to the Log In button
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userId = userIdField.getText();
                char[] password = passwordField.getPassword();

                // Perform authentication or other relevant actions here
                // For simplicity, we are just displaying the entered credentials
                System.out.println("User ID: " + userId);
                System.out.println("Password: " + new String(password));
                
                // USER CONTROL 
            }
        });

        // Add action listener to the Sign Up button
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open the SignUpPage
                dispose(); // Close the current login page
                new SignUpPage();
            }
        });

        panel.add(userIdLabel);
        panel.add(userIdField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(loginButton);
        panel.add(signUpButton);

        add(panel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new SimpleLoginPage();
            }
        });
    }
}