package client;

import api.data.Instructor;
import api.data.Person;

import javax.swing.*;
import java.util.ArrayList;

public class InstructorsListModel extends AbstractListModel {
    private ArrayList<Instructor> list = new ArrayList<>();


    @Override
    public int getSize() {
        return list.size();
    }

    @Override
    public Object getElementAt(int index) {
        return list.get(index);
    }

    public void addInstructor(Instructor instructor) {
        list.add(instructor);
        fireIntervalAdded(instructor, list.size() - 1, list.size() - 1);
    }

    public void deleteInstructor(int index) {
        Instructor removeInstructor = list.remove(index);
        fireIntervalRemoved(removeInstructor, index, index);
    }
    public void clearInstructor(){
        list.clear();
    }
}
