package client;

import api.data.Instructor;
import api.data.Workout;

import javax.swing.*;
import java.util.ArrayList;

public class WorkoutsListModel extends AbstractListModel {
    private ArrayList<Workout> list = new ArrayList<>();

    @Override
    public int getSize() {
        return list.size();
    }

    @Override
    public Object getElementAt(int index) {
        return list.get(index);
    }

    public void addWorkout(Workout workout) {
        list.add(workout);
        fireIntervalAdded(workout, list.size() - 1, list.size() - 1);
    }

    public void deleteWorkout(int index) {
        Workout removeWorkout = list.remove(index);
        fireIntervalRemoved(removeWorkout, index, index);
    }
    public void clearWorkout(){
        list.clear();
    }
}
