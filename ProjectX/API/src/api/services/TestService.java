package api.services;

import api.data.Car;
import api.data.Instructor;
import api.data.Person;
import api.data.Workout;

import java.util.Date;
import java.util.List;

public interface TestService {

    int plus(int a, int b);

    int umn(int a, int b);

    int minus(int a, int b);

    void ping();

    // Клиенты

    Person getPersonById(String id);

    List<Person> getAllPerson();

    void addPerson(Person person);

    void delPerson(Person person);

    void updPerson(Person person,String newName, String newPhone);

    // Инструкторы

    Instructor getInstructorById(String id);

    List<Instructor> getAllInstructor();

    void addInstructor(Instructor instructor);

    void delInstructor(Instructor instructor);

    void updInstructor(Instructor instructor,String newName,String newCategory);

    // Автомобили

    Car getCarById(String id);

    List<Car> getAllCar();

    void addCar(Car car);

    void delCar(Car car);

    void updCar(Car car, String newBrand, String newModel, String NewCategory);

    // Расписание

    List<Workout> getWorkoutByDate(String date);

    List<String> getAllDates();




}
