package frontend;
import javax.swing.*;
import backend.services.AdServices;
import backend.models.PetType;
import backend.models.User;
import backend.util.db.repositories.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdoptAPet extends JFrame {

    private JTextField petNameField;
    private JTextField petTypeField;
    private JTextField lastNameField;
    private JTextField addressField;
    private JTextField passwordField;
    private String petname;
    private String pettype;
    private PetType petypee;
    private User userr;

    public AdoptAPet(User user) {
        setTitle("Apply for adoption");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 600);
        setLocationRelativeTo(null);

        createSignUpPanel();
        userr = user;
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
        JLabel petTypeLabel = new JLabel("Pet Type:");
        panel.add(petTypeLabel, gbc);
        gbc.gridx++;
        petTypeField = new JTextField(15);
        panel.add(petTypeField, gbc);
        
        
        gbc.gridx = 0;
        gbc.gridy++;
        JLabel petCityLabel = new JLabel("Pet Location:");
        panel.add(petCityLabel, gbc);
        gbc.gridx++;
        petTypeField = new JTextField(15);
        panel.add(petTypeField, gbc);


        gbc.gridx = 0;
        gbc.gridy++;

        gbc.gridx = 0;
        gbc.gridy++;
        JLabel status = new JLabel("");
        panel.add(status, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        JButton listType = new JButton("list");
        listType.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Perform sign-up logic here
                // For simplicity, we are just displaying the entered information

                pettype = petTypeField.getText();
                
                // o türe sahip tüm listeyi döndürecek fonksiyon

            }
        });

        // Add the publish button to the panel
        panel.add(listType, gbc);
        
        gbc.gridx = 0;
        gbc.gridy++;
        JLabel petsLabel = new JLabel("Pets:");
        panel.add(petsLabel, gbc);
       
        
        
        
        
        gbc.gridx = 0;
        gbc.gridy=6+ gbc.gridy;
        JLabel addressLabel = new JLabel("Pet Id:");
        panel.add(addressLabel, gbc);
        gbc.gridx++;
        addressField = new JTextField(15);
        panel.add(addressField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        JLabel passwordLabel = new JLabel("Pet Name:");
        panel.add(passwordLabel, gbc);
        gbc.gridx++;
        passwordField = new JTextField(15);
        panel.add(passwordField, gbc);

      
        
        
        gbc.gridx = 0;
        gbc.gridy++;
        JButton applyToAdopt = new JButton(" Apply ");
        applyToAdopt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Perform sign-up logic here
                // For simplicity, we are just displaying the entered information

               
            }
        });

        // Add the publish button to the panel
        panel.add(applyToAdopt, gbc);
        
        
        
        
        
        
        
        gbc.gridx = 0;
        gbc.gridy++;        
        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new MainMenu(userr);
            }
        });
        panel.add(backButton, gbc);


        // Set up the frame with the panel
        getContentPane().add(panel);
    }
}
