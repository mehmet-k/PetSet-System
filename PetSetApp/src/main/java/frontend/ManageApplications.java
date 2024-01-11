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

public class ManageApplications extends JFrame {

	 private JList<String> petList;
	    private DefaultListModel<String> listModel;
    private JTextField petNameField;
    private JTextField petIdField;
    private JTextField lastNameField;
    private JTextField applicantIdField;
    private JTextField passwordField;
    private String petname;
    private String pettype;
    private PetType petypee;
    private User userr;

    public ManageApplications(User user) {
        setTitle("Manage applications to your pet(s)");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(950, 600);
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
        JLabel petTypeLabel = new JLabel("Applications to your pet(s):");
        panel.add(petTypeLabel, gbc);
     
        
        // Buraya ownerid'nin bu adam oldugu tüm petler basvurular dönecek 
        listModel = new DefaultListModel<>();
        petList = new JList<>(listModel);
        listModel.clear();
        

      
/*

        for (Pet pet : userOwnershipRepository.getUserPets(userr)) {
            if (pet != null) {
                try {
                    List<User> applicants = AdServices.getAllApplicantsByPet(pet);
                    if (applicants != null) {
                        for (User uuser : applicants) {
                            if (uuser != null) {
                                listModel.addElement("Pet ID:" + pet.getId() + "   " + " Pet Name:" + pet.getPetName()
                                        + "Applicant ID:"  + "   " + " Applicant Name:"
                                        + uuser.getFirstName() + " " + uuser.getSurname() + "   "
                                        + " Applicant Address:" + uuser.getAddress());
                            } else {
                                // Handle the case where uuser is null
                                System.out.println("User is null for pet: " + pet.getId());
                            }
                        }
                    } else {
                        // Handle the case where applicants is null
                        System.out.println("Applicants are null for pet: " + pet.getId());
                    }
                } catch (Exception e) {
                    // Handle the exception
                    System.out.println("An error occurred for pet: " + pet.getId());
                    e.printStackTrace();
                }
        
            } else {
                // Handle the case where pet is null
                System.out.println("Pet is null");
            }
        }
        */
        
    	List<Pet> pets = userOwnershipRepository.getUserPets(userr);
    	updatePetList(pets);
        
        gbc.gridx = 0;
        gbc.gridy++;
        JScrollPane scrollPane = new JScrollPane(petList);
        
        panel.add(scrollPane, gbc);
        
        
        gbc.gridx = 0;
        gbc.gridy++;
        JLabel petIdLabel = new JLabel("Pet id:");
        panel.add(petIdLabel, gbc);
        gbc.gridx++;
        petIdField = new JTextField(15);
        panel.add(petIdField, gbc);
        

        gbc.gridx = 0;
        gbc.gridy++;
        JLabel applicantIdLabel = new JLabel("Applicant id:");
        panel.add(applicantIdLabel, gbc);
        gbc.gridx++;
        applicantIdField = new JTextField(15);
        panel.add(applicantIdField, gbc);
        
        
        


        gbc.gridx = 0;
        gbc.gridy++;

        gbc.gridx = 0;
        gbc.gridy++;
        JLabel status = new JLabel("");
        panel.add(status, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        JButton approve = new JButton("Approve");
        approve.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Perform sign-up logic here
                // For simplicity, we are just displaying the entered information
            	User applicantUser = userRepository.getUserByUserID(Integer.parseInt(applicantIdField.getText()));
            	Pet approvedPet = petRepository.getPetByID(Integer.parseInt(petIdField.getText()));
            	
            	AdServices.confirmPetAdoption(applicantUser, approvedPet );
                updatePetList(pets);
                
                status.setText("Adoption approved");
                
                
            	/*dispose();
                new ManageApplications(userr);*/
                // o türe sahip tüm listeyi döndürecek fonksiyon
                                      
                   
            }
        });

        // Add the publish button to the panel
        panel.add(approve, gbc);
      
        
        
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
            if (pet != null) {
                try {
                    List<User> applicants = AdServices.getAllApplicantsByPet(pet);
                    if (applicants != null) {
                        for (User uuser : applicants) {
                            if (uuser != null) {
                                listModel.addElement("Pet ID: " + pet.getId() + "   " + " Pet Name:" + pet.getPetName()
                                        + "Applicant ID: "+ uuser.getId()  + "   " + " Applicant Name:"
                                        + uuser.getFirstName() + " " + uuser.getSurname() + "   "
                                        + " Applicant Address:" + uuser.getAddress());
                            } else {
                                // Handle the case where uuser is null
                                System.out.println("User is null for pet: " + pet.getId());
                            }
                        }
                    } else {
                        // Handle the case where applicants is null
                        System.out.println("Applicants are null for pet: " + pet.getId());
                    }
                } catch (Exception e) {
                    // Handle the exception
                    System.out.println("An error occurred for pet: " + pet.getId());
                    e.printStackTrace();
                }
        
            } else {
                // Handle the case where pet is null
                System.out.println("Pet is null");
            }
        }
    
    
    
    
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
