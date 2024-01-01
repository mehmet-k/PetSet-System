package frontend;

import javax.swing.*;

import backend.models.User;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JFrame {

    private JTextField textField;

    public MainMenu(User loggedUser) {
        setTitle("Main Menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 500);
        setLocationRelativeTo(null);
        User loggedInUser = loggedUser;
        createButtonPanel(loggedInUser);

        setVisible(true);
    }

    private void createButtonPanel(User loggedInUser) {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2, 10, 10));

        JButton button1 = new JButton("Adopt a Pet");
        JButton button2 = new JButton("See applications");
        JButton button3 = new JButton("Post an adoption ad");
        JButton button4 = new JButton("Your adoption ads");
        JButton button5 = new JButton("Buy pet Item");
        JLabel welcomeLabel = new JLabel("Welcome " + loggedInUser.getFirstName() + " " + loggedInUser.getSurname());

        panel.add(welcomeLabel);

        panel.add(button1);
        panel.add(button2);
        panel.add(button3);
        panel.add(button4);
        panel.add(button5);

        add(panel);

        // Action performed for button1
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Replace with your logic for adopting a pet
                System.out.println("Adopt a Pet button clicked");
            }
        });

        // Action performed for button2
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Replace with your logic for seeing applications
                System.out.println("See applications button clicked");
            }
        });

        // Action performed for button3
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Replace with your logic for posting an adoption ad
                System.out.println("Post an adoption ad button clicked");
            }
        });

        // Action performed for button4
        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Replace with your logic for viewing your adoption ads
                System.out.println("Your adoption ads button clicked");
            }
        });

        // Action performed for button5
        button5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Replace with your logic for buying a pet item
                System.out.println("Buy pet Item button clicked");
            }
        });
    }

}
   

