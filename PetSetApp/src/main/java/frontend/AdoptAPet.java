
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
import java.util.List;

public class AdoptAPet extends JFrame {

    private JList<String> petList;
    private DefaultListModel<String> listModel;
    private JTextField petTypeField;
    private JTextField petCityField;

    private JTextField petIdField;
    private JTextField petNameField;
    private String pettype;
    private String petcity;
    private Pet selectedPet; // Variable to store the selected pet

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
        gbc.gridy++;
        petTypeField = new JTextField(15);
        panel.add(petTypeField, gbc);
        
        
        gbc.gridx = 0;
        gbc.gridy++;
        JLabel petCityLabel = new JLabel("Pet Location:");
        panel.add(petCityLabel, gbc);
        gbc.gridy++;
        petCityField = new JTextField(15);
        panel.add(petCityField, gbc);


        gbc.gridx = 0;
        gbc.gridy++;

        listModel = new DefaultListModel<>();
        petList = new JList<>(listModel);
        
     

        gbc.gridx = 0;
        gbc.gridy++;
        JButton listType = new JButton("List");
        listType.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get pet list and update JList
                pettype = petTypeField.getText();
                pettype = pettype.toLowerCase();
                petcity = petCityField.getText();
                List<Pet> pets = AdServices.getPetListByCityAndPetType(petcity, pettype);
                updatePetList(pets);
            }
        });
        panel.add(listType, gbc);
        
     
        gbc.gridx = 0;
        gbc.gridy++;
        JLabel petsLabel = new JLabel("Pets:");
        panel.add(petsLabel, gbc);
       

        gbc.gridx = 0;
        gbc.gridy++;
        JScrollPane scrollPane = new JScrollPane(petList);
        panel.add(scrollPane, gbc);
        
        
        
        
        
        gbc.gridx = 0;
        gbc.gridy=6+ gbc.gridy;
        JLabel petIdLabel = new JLabel("Pet Id:");
        panel.add(petIdLabel, gbc);
        gbc.gridy++;
        petIdField = new JTextField(15);
        panel.add(petIdField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        /*JLabel patNameLabel = new JLabel("Pet Name:");
        panel.add(patNameLabel, gbc);
        gbc.gridx++;
        petNameField = new JTextField(15);
        panel.add(petNameField, gbc);*/

        gbc.gridx = 0;
        gbc.gridy++;
        JLabel status = new JLabel("");
        panel.add(status, gbc);

        
        
        gbc.gridx = 0;
        gbc.gridy++;
        JButton applyToAdopt = new JButton(" Apply ");
        applyToAdopt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Perform sign-up logic here
                // For simplicity, we are just displaying the entered information
            	int petid = Integer.parseInt(petIdField.getText());
            	Pet pett = petRepository.getPetByID(petid);
            	status.setText("Applied!");

            	AdServices.applyToPetAdoption(userr, pett);
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
    
    private void updatePetList(List<Pet> pets) {
        // Clear the existing list
        listModel.clear();

        // Add pet names to the list model
        for (Pet pet : pets) {
            listModel.addElement("ID:"+pet.getId()+ "   " + "Name:"+pet.getPetName()+ "   Type:" + pet.getPetType());
        }
    }
    
    
}

