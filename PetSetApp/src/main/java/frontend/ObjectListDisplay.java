package frontend;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class Person {
    String name;
    int age;
    String gender;

    public Person(String name, int age, String gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    @Override
    public String toString() {
        return name;
    }

    public String getDetails() {
        return "Name: " + name + "\nAge: " + age + "\nGender: " + gender;
    }
}

public class ObjectListDisplay extends JFrame {

    private DefaultListModel<Person> personListModel;
    private JList<Person> personList;
    private JTextArea detailsTextArea;

    public ObjectListDisplay() {
        setTitle("Object List Display");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);

        // Initialize components
        personListModel = new DefaultListModel<>();
        personList = new JList<>(personListModel);
        detailsTextArea = new JTextArea();
        detailsTextArea.setEditable(false);

        // Add sample data
        personListModel.addElement(new Person("John Doe", 25, "Male"));
        personListModel.addElement(new Person("Jane Smith", 30, "Female"));

        // Add 20 more people
        for (int i = 1; i <= 20; i++) {
            personListModel.addElement(new Person("Person" + i, 20 + i, i % 2 == 0 ? "Male" : "Female"));
        }

        // Set layout
        setLayout(new BorderLayout());
        add(new JScrollPane(personList), BorderLayout.WEST);
        add(new JScrollPane(detailsTextArea), BorderLayout.CENTER);

        // List selection listener using lambda expression
        personList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                Person selectedPerson = personList.getSelectedValue();
                if (selectedPerson != null) {
                    detailsTextArea.setText(selectedPerson.getDetails());
                }
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ObjectListDisplay().setVisible(true));
    }
}
