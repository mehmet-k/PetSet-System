

package frontend;
import javax.swing.*;

import backend.models.User;
import backend.util.db.repositories.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateProfile extends JFrame {

    private JTextField usernameField;
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField addressField;
    private JTextField passwordField;
    private User userr;


    public UpdateProfile(User user) {
        setTitle("Update Profile");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
        userr = user;
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
        JButton signUpButton = new JButton("Update");
        signUpButton.addActionListener(new ActionListener() {
            @Override
            // Add action listener to the Sign Up button        
                public void actionPerformed(ActionEvent e) {
                    // Perform sign-up logic here
                    // For simplicity, we are just displaying the entered information
                	 User newUser = new User(
                              userr.getUserName(),
                              firstNameField.getText(),
                              lastNameField.getText(),
                              addressField.getText(),
                              passwordField.getText());
                	 if (  	    !firstNameField.getText().isEmpty() &&
                			    !lastNameField.getText().isEmpty() &&
                			    !addressField.getText().isEmpty() &&
                			    !passwordField.getText().isEmpty()) {
    		    		 
                		 
                		 // updateUser userRepository.insertUser(newUser);
                		 
                		 status.setText("Updated succesfully!");
                			
                	}
                	 else {
                 		status.setText("Please fill all the blanks ");

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
                new MainMenu(userr);
            }
        });
        panel.add(backButton, gbc);

        add(panel);
    }
}



