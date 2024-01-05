
package frontend;
import javax.swing.*;
import backend.services.AdServices;
import backend.models.PetType;
import backend.models.User;
import backend.util.db.repositories.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SeeYourApplications extends JFrame {

    private JTextField petNameField;
    private JTextField petTypeField;
    private JTextField lastNameField;
    private JTextField addressField;
    private JTextField passwordField;
    private String petname;
    private String pettype;
    private PetType petypee;
    private User userr;

    public SeeYourApplications(User user) {
        setTitle("See your applications");
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
        JLabel petTypeLabel = new JLabel("Your applications:");
        panel.add(petTypeLabel, gbc);
     
        
        // Buraya applicant id'nin  bu adam oldugu tüm petler basvurular dönecek 
        
        
        
        
        
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
