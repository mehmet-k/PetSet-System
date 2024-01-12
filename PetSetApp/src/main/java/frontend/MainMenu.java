package frontend;

import javax.swing.*;

import backend.models.User;
import backend.services.HardDeleteInactiveRows;
import backend.util.db.repositories.userRepository;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JFrame {

    private JTextField textField;

    public MainMenu(User loggedUser) {
        setTitle("Main Menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 600);
        setLocationRelativeTo(null);
        User loggedInUser = loggedUser;
        createButtonPanel(loggedInUser);

        setVisible(true);
    }

    private void createButtonPanel(User loggedInUser) {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2, 10, 10));

        JButton button1 = new JButton("Adopt a Pet");
        JButton button2 = new JButton("Your  application(s)");
        JButton button3 = new JButton("Post an adoption ad");
        JButton button4 = new JButton("Your acitve ad(s)");
        JButton button5 = new JButton("Buy pet Item");
        JButton button6 = new JButton("Update profile");
        JButton button7 = new JButton("Hard Delete ");
        JButton button8 = new JButton("Your Adopted Pets ");



        JLabel welcomeLabel = new JLabel("Welcome " + loggedInUser.getFirstName() + " " + loggedInUser.getSurname());
        JLabel welcomeLabel1 = new JLabel();
        JLabel welcomeLabel2 = new JLabel();
        JLabel welcomeLabel3 = new JLabel();


        panel.add(welcomeLabel);
        panel.add(welcomeLabel1);
        if(userRepository.isAdmin(loggedInUser)) {
            panel.add(welcomeLabel2);
        }


        panel.add(button1);
        panel.add(button8);
        panel.add(button2);
        panel.add(button3);
        panel.add(button4);
        panel.add(button5);
        panel.add(button6);
        if(userRepository.isAdmin(loggedInUser)) {
            panel.add(welcomeLabel3);
            panel.add(button7);
        }




        add(panel);

        // Action performed for button1
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Replace with your logic for adopting a pet
                System.out.println("Adopt a Pet button clicked");
                
                dispose();
                new AdoptAPet(loggedInUser);
            }
        });

        // Action performed for button2
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Replace with your logic for seeing applications
                System.out.println("See applications button clicked");
                dispose();
                new SeeYourApplications(loggedInUser);
            }
        });

        // Action performed for button3
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Replace with your logic for posting an adoption ad
                System.out.println("Post an adoption ad button clicked");
                
                dispose();
                new PublishAd(loggedInUser);
            }
        });

        // Action performed for button4
        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Replace with your logic for viewing your adoption ads
                System.out.println("Your adoption ads button clicked");
                dispose();
                new ManageApplications(loggedInUser);
            }
        });

        // Action performed for button5
        button5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Replace with your logic for buying a pet item
            	  dispose();
                  new ItemBuy(loggedInUser);
                System.out.println("Buy pet Item button clicked");
            }
        });
        
        button6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Replace with your logic for buying a pet item
                System.out.println("Update clicked");
                dispose();
                new UpdateProfile(loggedInUser);
            }
        });
        
        button7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Replace with your logic for buying a pet item
            	HardDeleteInactiveRows.hardDeleteInactiveRowsFromDB();
            	
                System.out.println("Database cleaned");
                welcomeLabel1.setText("DB cleaned");
            }
        });
        
        button8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Replace with your logic for buying a pet item
            	   System.out.println("See adoptions button clicked");
                   dispose();
                   new SeeYourAdoptions(loggedInUser);
            }
        });
        
    }

}
   

