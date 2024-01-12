
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
import backend.util.db.repositories.*;
public class SeeYourAdoptions extends JFrame {

    private JList<String> petList;
    private DefaultListModel<String> listModel;
    private JTextField petNameField;
    private JTextField petTypeField;
    private JTextField lastNameField;
    private JTextField petCityField;
    private JTextField passwordField;
    private String petname;
    private String pettype;
    private String petcity;

    private User userr;

    public SeeYourAdoptions(User user) {
        setTitle("Your Adoptions");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 600);
        setLocationRelativeTo(null);
        userr = user;
        createSignUpPanel(userr);
        setVisible(true);
    }

    private void createSignUpPanel(User userr) {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5); // Add some padding

    
        gbc.gridx = 0;
        gbc.gridy++;
        JLabel petTypeLabel = new JLabel("Your adoptions:");
        panel.add(petTypeLabel, gbc);
     
        
        listModel = new DefaultListModel<>();
        petList = new JList<>(listModel);
        List<Pet> pets = userOwnershipRepository.getUserAdoptedPets(userr);

        for (Pet pet : pets) {
			System.out.println(pet.getPetName());
		}
        updatePetList(pets);
        
        
        gbc.gridx = 0;
        gbc.gridy++;
        JScrollPane scrollPane = new JScrollPane(petList);
        
        panel.add(scrollPane, gbc);

                
        
        
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
            listModel.addElement("ID:"+pet.getId()+ "   " + "Name:"+pet.getPetName()+  "   " + "Location: "
        +  userOwnershipRepository.getOwner(pet).getAddress()) ;
        }
    }
    
}
