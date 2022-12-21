package client;

import api.data.Person;

import javax.swing.*;

import java.util.ArrayList;

public class PersonsListModel extends AbstractListModel {

    private ArrayList<Person> list = new ArrayList<>();

    @Override
    public int getSize() {
        return list.size();
    }

    @Override
    public Object getElementAt(int index) {
        return list.get(index);
    }

    public void addPerson(Person person) {
        list.add(person);
        fireIntervalAdded(person, list.size() - 1, list.size() - 1);
    }

    public void deletePerson(int index) {
        Person removePerson = list.remove(index);
        fireIntervalRemoved(removePerson, index, index);
    }
}
