package client;

import api.data.Person;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.border.Border;

public class PersonPane extends JPanel {

    private PersonsListPane personsListPane;
    private Person person;
    private JTextField textName = new JTextField(20);
    private JTextField textPhone = new JTextField(20);
    private JButton edit=new JButton("Редактировать");
    private JLabel labelName=new JLabel("ФИО:");
    private JLabel labelPhone=new JLabel("Телефон:");
    private Color defaultColor=textName.getBackground();


    public PersonPane() {
        super(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        textName.setEnabled(false);
        textPhone.setEnabled(false);
        c.anchor=GridBagConstraints.FIRST_LINE_START;
        c.gridx = 0;
        c.gridy = 0;
        add(labelName,c);
        c.gridx = 1;
        c.gridy = 0;
        add(textName,c);

        c.gridx = 0;
        c.gridy = 1;
        add(labelPhone,c);
        c.gridx = 1;
        c.gridy = 1;
        c.weightx=1;

        add(textPhone,c);
        c.weighty=1;
        c.weightx=0;
        c.gridy=2;
        c.gridx=0;
        add(edit,c);
        hideFields();


        edit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(textName.isEnabled()){
                    if(!textName.getText().trim().isEmpty() & !textPhone.getText().trim().isEmpty())
                    {
                        String newName=textName.getText();
                        String newPhone=textPhone.getText();
                        try {
                            ServiceManager.getInstance().getTestService().updPerson(person,newName,newPhone);
                        } catch (ConnectionException er) {
                            er.printStackTrace();
                        }
                        if(personsListPane != null){
                            personsListPane.updPersonsListPane();
                        }
                        textName.setBackground(defaultColor);
                        textPhone.setBackground(defaultColor);
                        textName.setEnabled(false);
                        textPhone.setEnabled(false);
                    }
                    else{
                        if(textName.getText().trim().isEmpty()){
                            textName.setBackground(new Color(255,92,124,50));
                        }
                        if(textPhone.getText().trim().isEmpty()){
                            textPhone.setBackground(new Color(255,92,124,50));

                        }
                    }

                }
                else{
                    textName.setEnabled(true);
                    textPhone.setEnabled(true);
                }
            }
        });
    }
    public void setPerson(Person person) {

        if(!textName.isVisible()){
            textName.setVisible(true);
            textPhone.setVisible(true);
            edit.setVisible(true);
            labelName.setVisible(true);
            labelPhone.setVisible(true);
        }

        this.person = person;
        if (person != null) {
            textName.setText(person.getName());
            textName.setEnabled(false);
            textPhone.setText(person.getPhone());
            textPhone.setEnabled(false);
        } else {
            textName.setText("");
            textName.setEnabled(false);
        }
    }
    public void hideFields(){
        edit.setVisible(false);
        textName.setVisible(false);
        textPhone.setVisible(false);
        labelName.setVisible(false);
        labelPhone.setVisible(false);
    }

    public void setPersonsListPane(PersonsListPane personsListPane) {
        this.personsListPane = personsListPane;
    }
}
