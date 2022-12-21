package client;

import api.data.Person;

import javax.swing.*;
import java.awt.*;

public class PersonPane extends JPanel {

    private Person person;
    private JTextField textField = new JTextField(20);

    public PersonPane() {
        super(new FlowLayout(FlowLayout.LEFT));
        add(new JLabel("ФИО:"));
        add(textField);

        textField.setEnabled(false);
    }

    public void setHorse(Person person) {
        this.person = person;
        if (person != null) {
            textField.setText(person.getName());
            textField.setEnabled(true);
        } else {
            textField.setText("");
            textField.setEnabled(false);
        }
    }
}
