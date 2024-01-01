package frontend;
import javax.swing.*;

import backend.models.User;
import backend.util.db.repositories.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignUpPage extends JFrame {

    private JTextField usernameField;
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField addressField;
    private JTextField passwordField;


    public SignUpPage() {
        setTitle("Sign Up Page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        createSignUpPanel();

        setVisible(true);
    }

    private void createSignUpPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 2));

        JLabel usernameLabel = new JLabel("Username:");
        usernameField = new JTextField();
        JLabel firstNameLabel = new JLabel("First Name:");
        firstNameField = new JTextField();
        JLabel lastNameLabel = new JLabel("Last Name:");
        lastNameField = new JTextField();
        JLabel addressLabel = new JLabel("Address:");
        addressField = new JTextField();
        
        JLabel PasswordLabel = new JLabel("Password:");
        passwordField = new JTextField();
        
        
        JLabel status = new JLabel("");
        JLabel status1 = new JLabel("");

        JButton signUpButton = new JButton("Sign Up");
        JButton backButton = new JButton("Back");

        // Add action listener to the Sign Up button
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Perform sign-up logic here
                // For simplicity, we are just displaying the entered information
            	 User newUser = new User(
                          usernameField.getText(),
                          firstNameField.getText(),
                          lastNameField.getText(),
                          addressField.getText(),
                          passwordField.getText());
            	 if (!usernameField.getText().isEmpty() &&
            			    !firstNameField.getText().isEmpty() &&
            			    !lastNameField.getText().isEmpty() &&
            			    !addressField.getText().isEmpty()) {

            			if(!userRepository.isUserExists(usernameField.getText())) {
        		    		 status.setText("Signed up succesfully!");
        		    		 userRepository.insertUser(newUser);
            			}
            			else {
                    		status.setText("Username exist with another user ");
            			}
            			
            	}
            	 else {
             		status.setText("Pleas fill all the blanks ");

            	 }

            }
        });

        // Add action listener to the Back button
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Close the current sign-up page and go back to the login page
                dispose();
                new SimpleLoginPage();
            }
        });

        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(firstNameLabel);
        panel.add(firstNameField);
        panel.add(lastNameLabel);
        panel.add(lastNameField);
        panel.add(addressLabel);
        panel.add(addressField);
        panel.add(status);
        panel.add(status1);
        panel.add(signUpButton);
        panel.add(backButton);

        add(panel);
    }
}
