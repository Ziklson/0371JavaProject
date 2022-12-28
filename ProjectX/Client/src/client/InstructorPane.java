package client;

import api.data.Instructor;
import api.data.Person;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InstructorPane extends JPanel {
    private InstructorsListPane instructorsListPane;
    private Instructor instructor;
    private JTextField textName = new JTextField(20);
    private JTextField textCategory = new JTextField(20);
    private JButton edit=new JButton("Редактировать");
    private JLabel labelName=new JLabel("ФИО:");
    private JLabel labelCategory=new JLabel("Категория:");
    private Color defaultColor=textName.getBackground();


    public InstructorPane() {
        super(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        textName.setEnabled(false);
        textCategory.setEnabled(false);
        c.anchor=GridBagConstraints.FIRST_LINE_START;
        c.gridx = 0;
        c.gridy = 0;
        add(labelName,c);
        c.gridx = 1;
        c.gridy = 0;
        add(textName,c);

        c.gridx = 0;
        c.gridy = 1;
        add(labelCategory,c);
        c.gridx = 1;
        c.gridy = 1;
        c.weightx=1;

        add(textCategory,c);
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
                    if(!textName.getText().trim().isEmpty() & !textCategory.getText().trim().isEmpty())
                    {
                        String newName=textName.getText();
                        String newCategory=textCategory.getText();
                        try {
                            ServiceManager.getInstance().getTestService().updInstructor(instructor,newName,newCategory);
                        } catch (ConnectionException er) {
                            er.printStackTrace();
                        }
                        if(instructorsListPane != null){
                            instructorsListPane.updInstructorsListPane();
                        }
                        textName.setBackground(defaultColor);
                        textCategory.setBackground(defaultColor);
                        textName.setEnabled(false);
                        textCategory.setEnabled(false);
                    }
                    else{
                        if(textName.getText().trim().isEmpty()){
                            textName.setBackground(new Color(255,92,124,50));
                        }
                        if(textCategory.getText().trim().isEmpty()){
                            textCategory.setBackground(new Color(255,92,124,50));
                        }
                    }

                }
                else{
                    textName.setEnabled(true);
                    textCategory.setEnabled(true);
                }
            }
        });
    }
    public void setInstructor(Instructor instructor) {

        if(!textName.isVisible()){
            textName.setVisible(true);
            textCategory.setVisible(true);
            edit.setVisible(true);
            labelName.setVisible(true);
            labelCategory.setVisible(true);
        }

        this.instructor = instructor;
        if (instructor != null) {
            textName.setText(instructor.getName());
            textName.setEnabled(false);
            textCategory.setText(instructor.getCategory());
            textCategory.setEnabled(false);
        } else {
            textName.setText("");
            textName.setEnabled(false);
        }
    }
    public void hideFields(){
        edit.setVisible(false);
        textName.setVisible(false);
        textCategory.setVisible(false);
        labelName.setVisible(false);
        labelCategory.setVisible(false);
    }

    public void setInstructorsListPane(InstructorsListPane instructorsListPane) {
        this.instructorsListPane = instructorsListPane;
    }
}
