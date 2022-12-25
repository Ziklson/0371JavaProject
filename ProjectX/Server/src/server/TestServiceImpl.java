package server;

import api.data.Car;
import api.data.Instructor;
import api.data.Person;
import api.data.Workout;
import api.services.TestService;
import com.caucho.hessian.server.HessianServlet;

import javax.xml.crypto.Data;
import java.util.Date;

import java.util.List;

public class TestServiceImpl extends HessianServlet implements TestService {
    @Override
    public int plus(int a, int b) {
        return a + b;
    }

    @Override
    public int umn(int a, int b) {
        return a*b;
    }

    @Override
    public int minus(int a, int b) {
        return a - b;
    }

    @Override
    public void ping(){

    }

    // Клиенты

    @Override
    public Person getPersonById(String id){
        return DataManager.getPersonById(id);
    }

    @Override
    public List<Person> getAllPerson(){return DataManager.getAllPerson();}

    @Override
    public void addPerson(Person person) {
        DataManager.addPerson(person);
    }
    @Override
    public void delPerson(Person person){
        DataManager.delPerson(person);
    }

    @Override
    public void updPerson(Person person,String newName,String newPhone){
        DataManager.updPerson(person,newName,newPhone);
    }



    // Инструкторы

    @Override
    public Instructor getInstructorById(String id){
        return DataManager.getInstructorById(id);
    }

    @Override
    public List<Instructor> getAllInstructor(){return DataManager.getAllInstructor();}

    @Override
    public void addInstructor(Instructor instructor) {
        DataManager.addInstructor(instructor);
    }
    @Override
    public void delInstructor(Instructor instructor){
        DataManager.delInstructor(instructor);
    }

    @Override
    public void updInstructor(Instructor instructor,String newName,String newCategory){
        DataManager.updInstructor(instructor,newName,newCategory);
    }


    // Автомобили

    @Override
    public Car getCarById(String id){
        return DataManager.getCarById(id);
    }

    @Override
    public List<Car> getAllCar(){return DataManager.getAllCar();}

    @Override
    public void addCar(Car car){
        DataManager.addCar(car);
    }

    @Override
    public void delCar(Car car){
        DataManager.delCar(car);
    }

    @Override
    public void updCar(Car car, String newBrand,String newModel,String newCategory){
        DataManager.updCar(car,newBrand,newModel,newCategory);
    }

    // Расписание

    @Override
    public List<Workout> getWorkoutByDate(String date){
        return DataManager.getWorkoutByDate(date);
    }

    @Override
    public List<String> getAllDates(){
        return DataManager.getAllDates();
    }
}

