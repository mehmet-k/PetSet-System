
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
        panel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5); // Add some padding

        JLabel usernameLabel = new JLabel("Username:");
        panel.add(usernameLabel, gbc);
        gbc.gridx++;
        usernameField = new JTextField(15);  // Set the preferred width of the text field
        panel.add(usernameField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        JLabel firstNameLabel = new JLabel("First Name:");
        panel.add(firstNameLabel, gbc);
        gbc.gridx++;
        firstNameField = new JTextField(15);
        panel.add(firstNameField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        JLabel lastNameLabel = new JLabel("Last Name:");
        panel.add(lastNameLabel, gbc);
        gbc.gridx++;
        lastNameField = new JTextField(15);
        panel.add(lastNameField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        JLabel addressLabel = new JLabel("Address:");
        panel.add(addressLabel, gbc);
        gbc.gridx++;
        addressField = new JTextField(15);
        panel.add(addressField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        JLabel passwordLabel = new JLabel("Password:");
        panel.add(passwordLabel, gbc);
        gbc.gridx++;
        passwordField = new JTextField(15);
        panel.add(passwordField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        JLabel status = new JLabel("");
        panel.add(status, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        JButton signUpButton = new JButton("Sign Up");
        signUpButton.addActionListener(new ActionListener() {
            @Override
            // Add action listener to the Sign Up button        
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
        panel.add(signUpButton, gbc);

        gbc.gridx++;
        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new SimpleLoginPage();
            }
        });
        panel.add(backButton, gbc);

        add(panel);
    }
}



