package frontend;
import javax.swing.*;
import backend.services.AdServices;
import backend.models.Pet;
import backend.models.PetType;
import backend.models.User;
import backend.util.db.repositories.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import backend.services.AdServices;

public class PublishAd extends JFrame {

    private JTextField petNameField;
    private JTextField petTypeField;
    private JTextField lastNameField;
    private JTextField addressField;
    private JTextField passwordField;
    private String petname;
    private String pettype;
    private User userr;
    private Pet pet;

    public PublishAd(User user) {
        setTitle("Publish ad");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
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

        JLabel petNameLabel = new JLabel("Pet Name:");
        panel.add(petNameLabel, gbc);
        gbc.gridx++;
        petNameField = new JTextField(15);  // Set the preferred width of the text field
        panel.add(petNameField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        JLabel petTypeLabel = new JLabel("Pet type:");
        panel.add(petTypeLabel, gbc);
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
        JButton publish = new JButton("publish");
        publish.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Perform sign-up logic here
                // For simplicity, we are just displaying the entered information

                petname = petNameField.getText();
                pettype = petTypeField.getText();
              
                Pet pet = AdServices.createPetAd(pettype, petname);
                
                AdServices.publishAd(userr, pet);
                
                /*
                switch (pettype) {
                    case "Cat":
                        petypee.setPetType("Cat");
                        petypee.setId(1);
                        break;
                    case "Dog":
                        petypee.setPetType("Dog");
                        petypee.setId(2);
                        break;
                    case "Fish":
                        petypee.setPetType("Fish");
                        petypee.setId(3);
                        break;
                }
*/
                //AdServices.publishAd(userr, AdServices.createPetAd(petypee, petname));
            }
        });

        // Add the publish button to the panel
        panel.add(publish, gbc);
        
        
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


        // Set up the frame with the panel
        getContentPane().add(panel);
    }
    
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
            	
            	
            }
        });
    }
    
    
}
