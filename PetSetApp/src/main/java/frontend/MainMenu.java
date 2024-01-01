package frontend;

import javax.swing.*;
import java.awt.*;

public class MainMenu extends JFrame {

    private JTextField textField;

    public MainMenu() {
        setTitle("Simple Button Page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null);

        createButtonPanel();

        setVisible(true);
    }

    private void createButtonPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2, 10, 10));

        JButton button1 = new JButton("Adopt a Pet");
        JButton button2 = new JButton("See applications");
        JButton button3 = new JButton("Post a adoption ad");
        JButton button4 = new JButton("Your adoption ads");
        JButton button5 = new JButton("Buy pet Item");

        textField = new JTextField("Welcome ");

        panel.add(button1);
        panel.add(button2);
        panel.add(button3);
        panel.add(button4);
        panel.add(button5);
        panel.add(textField);

        add(panel);
    }
}