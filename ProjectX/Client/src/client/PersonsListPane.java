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
    private List<Person> allPersons= null;
    private PersonsListModel personsListModel = new PersonsListModel();
    private JList<Person> list = new JList<>(personsListModel);



    public PersonsListPane(PersonPane personPane) {
        super(new BorderLayout());
        JButton addButton = new JButton("Добавить");
        JButton delButton = new JButton("Удалить");

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

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
                JTextField name=  new JTextField();
                JTextField phone=new JTextField();
                Object[] message={
                        "Введите ФИО:", name,
                        "Введите Телефон:", phone
                };

              int dialogResult=JOptionPane.showConfirmDialog(
                        PersonsListPane.this,
                        message,
                        "Создание",
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.PLAIN_MESSAGE);
              if(dialogResult == 0) // OK option
              {
                  if (!name.getText().trim().isEmpty() & !phone.getText().trim().isEmpty()) {
                      Person person = new Person();
                      person.setName(name.getText());
                      person.setPhone(phone.getText());
                      personsListModel.addPerson(person);
                      try {
                         ServiceManager.getInstance().getTestService().addPerson(person);
                      } catch (ConnectionException er) {
                          er.printStackTrace();
                      }
                      updPersonsListPane();
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
                Person selectedPerson=list.getSelectedValue();
                personsListModel.deletePerson(selectedIndex);
                personPane.hideFields();
                try {
                    ServiceManager.getInstance().getTestService().delPerson(selectedPerson);
                } catch (ConnectionException er) {
                    er.printStackTrace();
                }

            }
        });

        list.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount()==2){
                    Person selectedPerson = list.getSelectedValue();
                    personPane.setPerson(selectedPerson);
                    // todo   - переправить Объект в PersonsPane
                }
            }
        });


        buttonPanel.add(addButton);
        buttonPanel.add(delButton);

        add(buttonPanel, BorderLayout.NORTH);
        add(list, BorderLayout.CENTER);
        updPersonsListPane();

        personPane.setPersonsListPane(this);
    }



    void updPersonsListPane() {
        if (personsListModel.getSize() != 0){
            personsListModel.clearPerson();
        }
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
