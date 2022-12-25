package client;

import api.data.Instructor;
import api.data.Person;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class InstructorsListPane extends JPanel {
    private List<Instructor> allInstructors= null;
    private InstructorsListModel instructorsListModel = new InstructorsListModel();
    private JList<Instructor> list = new JList<>(instructorsListModel);



    public InstructorsListPane(InstructorPane instructorPane) {
        super(new BorderLayout());
        JButton addButton = new JButton("Добавить");
        JButton delButton = new JButton("Удалить");

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        list.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                setText(((Instructor) value).getName());
                return this;
            }
        });

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTextField name=  new JTextField();
                JTextField category=new JTextField();
                Object[] message={
                        "Введите ФИО:", name,
                        "Введите Категорию", category
                };

                int dialogResult=JOptionPane.showConfirmDialog(
                        InstructorsListPane.this,
                        message,
                        "Создание",
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.PLAIN_MESSAGE);
                if(dialogResult == 0) // OK option
                {
                    if (!name.getText().trim().isEmpty() & !category.getText().trim().isEmpty()) {
                        Instructor instructor = new Instructor();
                        instructor.setName(name.getText());
                        instructor.setCategory(category.getText());
                        instructorsListModel.addInstructor(instructor);
                        try {
                            ServiceManager.getInstance().getTestService().addInstructor(instructor);
                        } catch (ConnectionException er) {
                            er.printStackTrace();
                        }
                        updInstructorsListPane();
                        //todo add Person
                    }
                    else{

                    }
                }

            }
        });

        delButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = list.getSelectedIndex();
                Instructor selectedInstructor=list.getSelectedValue();
                instructorsListModel.deleteInstructor(selectedIndex);
                instructorPane.hideFields();
                try {
                    ServiceManager.getInstance().getTestService().delInstructor(selectedInstructor);
                } catch (ConnectionException er) {
                    er.printStackTrace();
                }

            }
        });

        list.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount()==2){
                    Instructor selectedInstructor = list.getSelectedValue();
                    instructorPane.setInstructor(selectedInstructor);
                    // todo   - переправить Объект в PersonsPane
                }
            }
        });


        buttonPanel.add(addButton);
        buttonPanel.add(delButton);

        add(buttonPanel, BorderLayout.NORTH);
        add(list, BorderLayout.CENTER);
        updInstructorsListPane();

        instructorPane.setInstructorsListPane(this);
    }



    void updInstructorsListPane() {
        if (instructorsListModel.getSize() != 0){
            instructorsListModel.clearInstructor();
        }
        try {
            allInstructors = ServiceManager.getInstance().getTestService().getAllInstructor();
        } catch (ConnectionException e) {
            e.printStackTrace();
        }
        for (Instructor instructor: allInstructors) {
            instructorsListModel.addInstructor(instructor);
        }
    }



}
