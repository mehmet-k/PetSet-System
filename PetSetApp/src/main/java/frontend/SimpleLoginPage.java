package frontend;

import backend.models.User;
import backend.util.db.repositories.*;

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
        panel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5); // Add some padding

        JLabel userNameLabel = new JLabel("User ID:");
        panel.add(userNameLabel, gbc);
        gbc.gridx++;
        userIdField = new JTextField(15);
        panel.add(userIdField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        JLabel passwordLabel = new JLabel("Password:");
        panel.add(passwordLabel, gbc);
        gbc.gridx++;
        passwordField = new JPasswordField(15);
        panel.add(passwordField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        JLabel status = new JLabel("");
        panel.add(status, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        JButton loginButton = new JButton("Log In");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userName = userIdField.getText();
                char[] password = passwordField.getPassword();

                // Perform authentication or other relevant actions here
                // For simplicity, we are just displaying the entered credentials
                System.out.println("User ID: " + userName);
                System.out.println("Password: " + new String(password));

                if (userRepository.areCredientialsCorrect(userName, new String(password))) {
                	 User loggedUser = userRepository.getUserFromUserName(userName);
                	dispose(); // Close the current login page
                    new MainMenu(loggedUser);
                } else {
                    System.out.println("false");
                    status.setText("Wrong Credentials!");
                }
                // USER CONTROL
            }
        });
        panel.add(loginButton, gbc);

        gbc.gridx++;
        JButton signUpButton = new JButton("Sign Up");
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open the SignUpPage
                dispose(); // Close the current login page
                new SignUpPage();
            }
        });
        panel.add(signUpButton, gbc);

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
