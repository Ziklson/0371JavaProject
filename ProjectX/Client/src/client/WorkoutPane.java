package client;

import api.data.Car;
import api.data.Instructor;
import api.data.Person;
import api.data.Workout;

import javax.swing.*;
import java.awt.*;

public class WorkoutPane extends JPanel {
    private String nameClient;
    private String nameInstructor;
    private String brandCar;
    private String modelCar;
    private String timeStart;
    private String duration;
    private Person person;
    private Instructor instructor;
    private Car car;
    private Workout workout;

    public WorkoutPane(Person person,Instructor instructor, Car car, Workout workout){
        this.person=person;
        this.instructor=instructor;
        this.car=car;
        this.workout=workout;
        setWorkout();
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        JLabel labelClient=new JLabel();
        labelClient.setText("<html>Имя клиента: " + nameClient + "</html><br>");
        JLabel labelInstructor=new JLabel();
        labelInstructor.setText("<html>Имя инструктора: "+nameInstructor+"</html><br>");
        JLabel labelBrandCar=new JLabel();
        labelBrandCar.setText("<html>Марка автомобиля: "+brandCar+"</html><br>");
        JLabel labelModelCar=new JLabel();
        labelModelCar.setText("<html>Модель автомобиля: "+modelCar+"</html><br>");
        JLabel labelTimeStart=new JLabel();
        labelTimeStart.setText("<html>Время начала: "+timeStart+"</html><br>");
        JLabel labelDuration=new JLabel();
        labelDuration.setText("<html>Длительность(мин): "+duration+"</html><br>");
        setBorder(BorderFactory.createRaisedBevelBorder());
        setBackground(new Color(216,237,255));
        c.gridx=0;
        c.gridy=0;
       // c.anchor=GridBagConstraints.NORTHWEST;
     //   c.fill=GridBagConstraints.BOTH;
        add(labelClient,c);
        c.gridy=1;
        add(labelInstructor,c);
        c.gridy=2;
        add(labelBrandCar,c);
        c.gridy=3;
        add(labelModelCar,c);
        c.gridy=4;
        add(labelTimeStart,c);
        c.gridy=5;
        add(labelDuration,c);
    }
    public void setWorkout(){
        nameClient=person.getName();
        nameInstructor=instructor.getName();
        brandCar=car.getBrand();
        modelCar=car.getModel();
        timeStart=workout.getDate().split(" ")[1];
        duration=workout.getDuration();
    }

}
