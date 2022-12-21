package client;

import api.data.Person;
import java.util.List;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PersonsListPane extends JPanel {
    public PersonsListPane() {
        super(new BorderLayout());
        JButton addButton = new JButton("Добавить");
        JButton delButton = new JButton("Удалить");
        PersonsListModel personsListModel = new PersonsListModel();
        JList<Person> list = new JList<>(personsListModel);
        list.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                setText(((Person) value).getName());
                return this;
            }
        });

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = JOptionPane.showInputDialog(
                        PersonsListPane.this,
                        "Введите ФИО",
                        "Создание",
                        JOptionPane.PLAIN_MESSAGE);
                if (name != null) {
                    Person person = new Person();
                    person.setName(name);
                    personsListModel.addPerson(person);
                    //todo add Horse
                }
            }
        });

        delButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = list.getSelectedIndex();
                personsListModel.deletePerson(selectedIndex);
            }
        });

        list.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount()==2){
                    Object selectedValue = list.getSelectedValue();
                    // todo   - переправить Объект в HorsePanel

                }
            }
        });


        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(addButton);
        buttonPanel.add(delButton);

        add(buttonPanel, BorderLayout.NORTH);
        add(list, BorderLayout.CENTER);

        List<Person> allPersons= null;
        try {
            allPersons = ServiceManager.getInstance().getTestService().getAllPerson();
        } catch (ConnectionException e) {
            e.printStackTrace();
        }
        for (Person person: allPersons) {
            personsListModel.addPerson(person);

        }
    }
}
