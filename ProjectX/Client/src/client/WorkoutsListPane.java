package client;

import api.data.Car;
import api.data.Instructor;
import api.data.Person;
import api.data.Workout;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class WorkoutsListPane extends JPanel {
    List<Workout> allWorkouts = null;
    List<WorkoutPane> allPaneWorkouts=new ArrayList<>();
    CalendarPane calendarPane;
    JPanel workoutsPanel=new JPanel(new GridBagLayout());
    JScrollPane scrollFrame = new JScrollPane(workoutsPanel);
    String curDate=null;


    public WorkoutsListPane(){
        super(new GridBagLayout());
        workoutsPanel.setAutoscrolls(true);

        JButton addButton = new JButton("Добавить");

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(addButton);
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
        add(scrollFrame,c);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(curDate != null){
                    try {
                        List<Person> allPersons = ServiceManager.getInstance().getTestService().getAllPerson();
                        List<Instructor> allInstructors = ServiceManager.getInstance().getTestService().getAllInstructor();
                        List<Car> allCars = ServiceManager.getInstance().getTestService().getAllCar();
                        Map<String,String> personsNames = new HashMap<String, String>();
                        Map<String,String> instructorsNames = new HashMap<String,String>();
                        Map<String,String> carsNames=new HashMap<String,String>();
                        for (Person person : allPersons) {
                            personsNames.put(person.getId(),person.getName());
                        }
                        for (Instructor instructor : allInstructors) {
                            instructorsNames.put(instructor.getId(),instructor.getName());
                        }
                        for (Car car : allCars) {
                            carsNames.put(car.getId(),car.getBrand() + " " + car.getModel());
                        }
                        JFormattedTextField timeWorkout = null;
                        JFormattedTextField durationWorkout=null;
                        try {
                            timeWorkout = new JFormattedTextField(new MaskFormatter("##:##"));
                            durationWorkout = new JFormattedTextField(new NumberFormatter());
                        } catch (ParseException ex) {
                            ex.printStackTrace();
                        }
                        JComboBox jComboBoxClients = new JComboBox<>(personsNames.values().toArray());
                        JComboBox jComboBoxInstructors = new JComboBox<>(instructorsNames.values().toArray());
                        JComboBox jComboBoxCars = new JComboBox<>(carsNames.values().toArray());
                        durationWorkout.setValue(30);
                        timeWorkout.setValue("00:00");

                        Object[] message = {
                                "Выберите клиента:", jComboBoxClients,
                                "Выберите инструктора:", jComboBoxInstructors,
                                "Выберите автомобиль:", jComboBoxCars,
                                "Укажите время старта:", timeWorkout,
                                "Укажите длительность(мин):",durationWorkout
                        };
                        int dialogResult = JOptionPane.showConfirmDialog(
                                null,
                                message,
                                "Создание",
                                JOptionPane.OK_CANCEL_OPTION,
                                JOptionPane.PLAIN_MESSAGE);
                        if(dialogResult == 0){
                            String clientId=getKey(personsNames, jComboBoxClients.getSelectedItem().toString());
                            String instructorId=getKey(instructorsNames, jComboBoxInstructors.getSelectedItem().toString());
                            String carId=getKey(carsNames, jComboBoxCars.getSelectedItem().toString());
                            String timeStart=timeWorkout.getText();
                            String duration=durationWorkout.getText();
                            Workout workout=new Workout();
                            workout.setClientId(clientId);
                            workout.setInstructorId(instructorId);
                            workout.setCarId(carId);
                            workout.setDuration(duration);
                            workout.setDate(getCurDate()+" "+ timeStart);
                            ServiceManager.getInstance().getTestService().addWorkout(workout);
                            calendarPane.setDate();
                            calendarPane.updWorkoutByDate(curDate);
                            calendarPane.getWorkoutsListPane().updWorkouts(calendarPane.getWorkoutByDate());
                        }
                    } catch (ConnectionException ex) {
                        ex.printStackTrace();
                    }
                }
                else{
                    JOptionPane.showMessageDialog(null,"Вы не выбрали дату!");
                }

            }
        });
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
            int count=1;
            for(WorkoutPane workoutPane : allPaneWorkouts){
                if(count == size){
                    c.weighty=1;
                }
                workoutPane.setCalendarPane(calendarPane);
                workoutsPanel.add(workoutPane,c);
                c.gridy++;
                count++;
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

    public void setCalendarPane(CalendarPane calendarPane) {
        this.calendarPane = calendarPane;
    }

    public void clearAllPaneWorkouts(){
        allPaneWorkouts.clear();
    }

    public void clearWorkoutsPanel(){
        workoutsPanel.removeAll();
    }


    //
    public static <K, V> K getKey(Map<K, V> map, V value)
    {
        for (K key: map.keySet())
        {
            if (value.equals(map.get(key))) {
                return key;
            }
        }
        return null;
    }

    public String getCurDate() {
        return curDate;
    }

    public void setCurDate(String curDate) {
        this.curDate = curDate;
    }
}
