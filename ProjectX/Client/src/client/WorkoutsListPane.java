package client;

import api.data.Car;
import api.data.Instructor;
import api.data.Person;
import api.data.Workout;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;



public class WorkoutsListPane extends JPanel {
    List<Workout> allWorkouts = null;
    List<WorkoutPane> allPaneWorkouts=new ArrayList<>();
    JPanel workoutsPanel=new JPanel(new GridBagLayout());
    public WorkoutsListPane(){
        super(new GridBagLayout());
        JButton addButton = new JButton("Добавить");
        JButton editButton = new JButton("Редактировать");
        JButton delButton = new JButton("Удалить");

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(delButton);
        GridBagConstraints c = new GridBagConstraints();
        c.gridx=0;
        c.gridy=0;
        c.fill=GridBagConstraints.HORIZONTAL;
        c.anchor=GridBagConstraints.NORTHWEST;
        add(buttonPanel,c);
        c.gridy=1;
        c.fill=GridBagConstraints.BOTH;
        c.weightx=1;
        c.weighty=1;
        add(workoutsPanel,c);

    }


    public void updWorkoutsPane(){
        workoutsPanel.removeAll();
        GridBagConstraints c=new GridBagConstraints();
        c.gridx=0;
        c.gridy=0;
        c.fill=GridBagConstraints.HORIZONTAL;
        c.anchor=GridBagConstraints.NORTHWEST;
        c.weightx=1;
        if(!allPaneWorkouts.isEmpty()){
            int size=allPaneWorkouts.size();
//            if (size == 1){
//                c.weighty=1;
//            }
            int count=1;
            for(WorkoutPane workoutPane : allPaneWorkouts){
                if(count == size){
                    c.weighty=1;
                }
                workoutsPanel.add(workoutPane,c);
                c.gridy++;
                count++;
                //c.weighty=1;
            }
            allPaneWorkouts.clear();
        }
        else{
            workoutsPanel.add(new JLabel("Записей нет!"));
        }
        workoutsPanel.setVisible(false);
        workoutsPanel.setVisible(true);
    }
    public void updWorkouts(List<Workout> workouts){
        allWorkouts=workouts;
        if (allWorkouts!=null) {
            if(!allWorkouts.isEmpty()){
                allPaneWorkouts.clear();
                for (Workout workout : allWorkouts) {
                    Person person=new Person();
                    try {
                        person=ServiceManager.getInstance().getTestService().getPersonById(workout.getClientId());
                    } catch (ConnectionException e) {
                        e.printStackTrace();
                    }
                    Instructor instructor=new Instructor();
                    try {
                        instructor=ServiceManager.getInstance().getTestService().getInstructorById(workout.getInstructorId());
                    } catch (ConnectionException e) {
                        e.printStackTrace();
                    }
                    Car car=new Car();
                    try {
                        car=ServiceManager.getInstance().getTestService().getCarById(workout.getCarId());
                    } catch (ConnectionException e) {
                        e.printStackTrace();
                    }
                    allPaneWorkouts.add(new WorkoutPane(person, instructor, car, workout));
                }
                updWorkoutsPane();
            }
            else{
                updWorkoutsPane();
            }

        }
    }
}
