

package frontend;
import javax.swing.*;
import backend.services.AdServices;
import backend.services.ItemPurchaseServices;
import backend.models.Items;
import backend.models.Pet;
import backend.models.PetType;
import backend.models.User;
import backend.util.db.repositories.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;


public class ItemBuy extends JFrame {

	  private JList<String> AllItemList;
	    private DefaultListModel<String> listModel;
	    private JTextField buyItemIDField;
	    private JTextField petCityField;

	    private JTextField petIdField;
	    private JTextField ItemTypeField;
	    private JTextField MinPriceField;
	    private JTextField MaxPriceField;


	    private String itemtype;
	    private int minPrice;
	    private int maxPrice;

	    private Pet selectedPet; // Variable to store the selected pet

	    private User userr;

	    public ItemBuy(User user) {
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
	        JLabel ItemTypeLabel = new JLabel("Item Type:");
	        panel.add(ItemTypeLabel, gbc);
	        gbc.gridy++;
	        ItemTypeField = new JTextField(15);
	        panel.add(ItemTypeField, gbc);
	        
	        
	        gbc.gridx = 0;
	        gbc.gridy++;
	        
	        JLabel MinPriceLabel = new JLabel("Min Price:");
	        panel.add(MinPriceLabel, gbc);
	        
	        gbc.gridx++;
	        JLabel MaxPriceLabel = new JLabel("Max Price:");
	        panel.add(MaxPriceLabel, gbc);

	        gbc.gridx = 0;

	        gbc.gridy++;
	        MinPriceField= new JTextField(15);
	        panel.add(MinPriceField, gbc);
	        
	        gbc.gridx++;
	        MaxPriceField = new JTextField(15);
	        panel.add(MaxPriceField, gbc);


	        gbc.gridx = 0;
	        gbc.gridy++;

	        listModel = new DefaultListModel<>();
	        AllItemList = new JList<>(listModel);
	        
	     

	        gbc.gridx = 0;
	        gbc.gridy++;
	        JButton listType = new JButton("List");
	        listType.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                // Get pet list and update JList
	                itemtype = ItemTypeField.getText();
	                itemtype = itemtype.toLowerCase();
	                
	                //itemsRepository.
	                
	                minPrice =  Integer.parseInt(MinPriceField.getText());
	                maxPrice =  Integer.parseInt(MaxPriceField.getText());
	                List<Items> items = ItemPurchaseServices.getItemsByPriceAndType(minPrice, maxPrice, null);
	                updateAllItemList(items);
	            }
	        });
	        panel.add(listType, gbc);
	        
	     
	        gbc.gridx = 0;
	        gbc.gridy++;
	        JLabel petsLabel = new JLabel("Items:");
	        panel.add(petsLabel, gbc);
	       

	        gbc.gridx = 0;
	        gbc.gridy++;
	        JScrollPane scrollPane = new JScrollPane(AllItemList);
	        panel.add(scrollPane, gbc);
	        
	        
	        
	        
	        
	        gbc.gridx = 0;
	        gbc.gridy=6+ gbc.gridy;
	        JLabel ItemIdLabel = new JLabel("Item Id:");
	        panel.add(ItemIdLabel, gbc);
	        gbc.gridy++;
	        buyItemIDField = new JTextField(15);
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
	        JButton applyToAdopt = new JButton(" Buy ");
	        applyToAdopt.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                // Perform sign-up logic here
	                // For simplicity, we are just displaying the entered information
	            	int petid = Integer.parseInt(petIdField.getText());
	            	Pet pett = petRepository.getPetByID(petid);
	            	status.setText("Bought!");

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
	    
	    private void updateAllItemList(List<Items> items) {
	        // Clear the existing list
	        listModel.clear();

	        // Add pet names to the list model
	        for (Items item : items) {
	            listModel.addElement("ID:"+item.getId()+ "   " + " Item Type:"+item.getItemType()+ " Item Name:"+item.getItemName()+ "   Price:" + item.getPrice());
	        }
	    }
	    
    
}
