package server;

import api.data.Car;
import api.data.Instructor;
import api.data.Person;
import api.data.Workout;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataManager {
    // клиенты
    public static Person getPersonById(String id){
        Person person = new Person();
        try {
            try {
                Class.forName("org.postgresql.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            Connection conn = DatabaseManager.getInstance().getConnection();
            Statement stmt = conn.createStatement();
            String sql="SELECT * FROM client WHERE client_id='"+id+"'";
            ResultSet resultSet=stmt.executeQuery(sql);
            if(resultSet.next()) {
                String name = resultSet.getString("client_name");
                String phone = resultSet.getString("client_phone");
                person.setId(id);
                person.setName(name);
                person.setPhone(phone);
            }
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return person;
    }
    public static List<Person> getAllPerson(){
        ArrayList<Person> persons=new ArrayList<>();
        try {
            try {
                Class.forName("org.postgresql.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            Connection conn = DatabaseManager.getInstance().getConnection();
            Statement stmt = conn.createStatement();
            ResultSet resultSet = stmt.executeQuery("SELECT * FROM client");

            while (resultSet.next()) {
                String id = resultSet.getString("client_id");
                String name = resultSet.getString("client_name");
                String phone = resultSet.getString("client_phone");
                Person person = new Person();
                person.setId(id);
                person.setName(name);
                person.setPhone(phone);
                persons.add(person);
            }
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return persons;
    }
    public static void addPerson(Person person){
        try {
            try {
                Class.forName("org.postgresql.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            Connection conn = DatabaseManager.getInstance().getConnection();
            Statement stmt = conn.createStatement();
           String sql="INSERT INTO client(client_name,client_phone) VALUES "+ "('"
                  +person.getName() +"','"+person.getPhone()+"');";
            int result=stmt.executeUpdate(sql);

        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public static void delPerson(Person person){
        try {
            try {
                Class.forName("org.postgresql.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            Connection conn = DatabaseManager.getInstance().getConnection();
            Statement stmt = conn.createStatement();
            String sql="DELETE FROM client WHERE client_id="+person.getId();
            int result=stmt.executeUpdate(sql);

        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }
    public static void updPerson(Person person,String newName, String newPhone){
        try {
            try {
                Class.forName("org.postgresql.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            Connection conn = DatabaseManager.getInstance().getConnection();
            Statement stmt = conn.createStatement();
            String sql="UPDATE client SET client_name='"+newName + "', client_phone='"+newPhone +"' WHERE client_id="+person.getId();
            int result=stmt.executeUpdate(sql);

        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Инструкторы
    public static Instructor getInstructorById(String id){
        Instructor instructor = new Instructor();
        try {
            try {
                Class.forName("org.postgresql.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            Connection conn = DatabaseManager.getInstance().getConnection();
            Statement stmt = conn.createStatement();
            String sql="SELECT * FROM instructor WHERE instructor_id='"+id+"'";
            ResultSet resultSet=stmt.executeQuery(sql);
            if(resultSet.next()) {
                String name = resultSet.getString("instructor_name");
                String category = resultSet.getString("instructor_category");
                instructor.setId(id);
                instructor.setName(name);
                instructor.setCategory(category);
            }
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return instructor;
    }
    public static List<Instructor> getAllInstructor(){
        ArrayList<Instructor> instructors=new ArrayList<>();
        try {
            try {
                Class.forName("org.postgresql.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            Connection conn = DatabaseManager.getInstance().getConnection();
            Statement stmt = conn.createStatement();
            ResultSet resultSet = stmt.executeQuery("SELECT * FROM instructor");

            while (resultSet.next()) {
                String id = resultSet.getString("instructor_id");
                String name = resultSet.getString("instructor_name");
                String category = resultSet.getString("instructor_category");
                Instructor instructor = new Instructor();
                instructor.setId(id);
                instructor.setName(name);
                instructor.setCategory(category);
                instructors.add(instructor);
            }
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return instructors;
    }
    public static void addInstructor(Instructor instructor){
        try {
            try {
                Class.forName("org.postgresql.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            Connection conn = DatabaseManager.getInstance().getConnection();
            Statement stmt = conn.createStatement();
            String sql="INSERT INTO instructor(instructor_name,instructor_category) VALUES "+ "('"
                    +instructor.getName() +"','"+instructor.getCategory()+"');";
            int result=stmt.executeUpdate(sql);

        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public static void delInstructor(Instructor instructor){
        try {
            try {
                Class.forName("org.postgresql.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            Connection conn = DatabaseManager.getInstance().getConnection();
            Statement stmt = conn.createStatement();
            String sql="DELETE FROM instructor WHERE instructor_id="+instructor.getId();
            int result=stmt.executeUpdate(sql);

        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }
    public static void updInstructor(Instructor instructor,String newName, String newCategory){
        try {
            try {
                Class.forName("org.postgresql.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            Connection conn = DatabaseManager.getInstance().getConnection();
            Statement stmt = conn.createStatement();
            String sql="UPDATE instructor SET instructor_name='"+newName + "', instructor_category='"+newCategory +"' WHERE instructor_id="+instructor.getId();
            int result=stmt.executeUpdate(sql);

        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Автомобили

    public static Car getCarById(String id){
        Car car = new Car();
        try {
            try {
                Class.forName("org.postgresql.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            Connection conn = DatabaseManager.getInstance().getConnection();
            Statement stmt = conn.createStatement();
            String sql="SELECT * FROM car WHERE car_id='"+id+"'";
            ResultSet resultSet=stmt.executeQuery(sql);
            if(resultSet.next()) {
                String brand = resultSet.getString("car_brand");
                String model = resultSet.getString("car_model");
                String category = resultSet.getString("car_category");
                car.setId(id);
                car.setBrand(brand);
                car.setModel(model);
                car.setCategory(category);
            }
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return car;
    }
    public static List<Car> getAllCar(){
        ArrayList<Car> cars=new ArrayList<>();
        try {
            try {
                Class.forName("org.postgresql.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            Connection conn = DatabaseManager.getInstance().getConnection();
            Statement stmt = conn.createStatement();
            ResultSet resultSet = stmt.executeQuery("SELECT * FROM car");

            while (resultSet.next()) {
                String id = resultSet.getString("car_id");
                String brand = resultSet.getString("car_brand");
                String model = resultSet.getString("car_model");
                String category = resultSet.getString("car_category");

                Car car = new Car();
                car.setId(id);
                car.setBrand(brand);
                car.setModel(model);
                car.setCategory(category);
                cars.add(car);
            }
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return cars;
    }
    public static void addCar(Car car){
        try {
            try {
                Class.forName("org.postgresql.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            Connection conn = DatabaseManager.getInstance().getConnection();
            Statement stmt = conn.createStatement();
            String sql="INSERT INTO car(car_brand,car_model,car_category) VALUES "+ "('"
                    +car.getBrand() +"','"+car.getModel()+"','"+car.getCategory()+"');";
            int result=stmt.executeUpdate(sql);

        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public static void delCar(Car car){
        try {
            try {
                Class.forName("org.postgresql.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            Connection conn = DatabaseManager.getInstance().getConnection();
            Statement stmt = conn.createStatement();
            String sql="DELETE FROM car WHERE car_id="+car.getId();
            int result=stmt.executeUpdate(sql);

        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }
    public static void updCar(Car car,String newBrand, String newModel,String newCategory){
        try {
            try {
                Class.forName("org.postgresql.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            Connection conn = DatabaseManager.getInstance().getConnection();
            Statement stmt = conn.createStatement();
            String sql="UPDATE car SET car_brand='"+newBrand + "', car_model='"+newModel +"',car_category='"+newCategory+"'" +
                    " WHERE car_id="+car.getId();
            int result=stmt.executeUpdate(sql);

        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Расписание


    public static List<Workout> getWorkoutByDate(String date){
        ArrayList<Workout> workouts = new ArrayList<>();
        try {
            try {
                Class.forName("org.postgresql.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            Connection conn = DatabaseManager.getInstance().getConnection();
            Statement stmt = conn.createStatement();

            String sql="SELECT * FROM workout WHERE date_workout LIKE '"+date+" %'";
            ResultSet resultSet=stmt.executeQuery(sql);
            while (resultSet.next()) {
                String id = resultSet.getString("workout_id");
                String clientId = resultSet.getString("client_id");
                String instructorId = resultSet.getString("instructor_id");
                String carId = resultSet.getString("car_id");
                String dateWorkout=resultSet.getString("date_workout");
                String durationWorkout=resultSet.getString("duration_workout");
                Workout workout = new Workout();
                workout.setId(id);
                workout.setClientId(clientId);
                workout.setInstructorId(instructorId);
                workout.setCarId(carId);
                workout.setDate(dateWorkout);
                workout.setDuration(durationWorkout);
                workouts.add(workout);
            }
        }
        catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        return workouts;
    }
    public static List<String> getAllDates(){
        List<String> allDates= new ArrayList<>();
        try {
            try {
                Class.forName("org.postgresql.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            Connection conn = DatabaseManager.getInstance().getConnection();
            Statement stmt = conn.createStatement();

            String sql="SELECT date_workout FROM workout";
            ResultSet resultSet=stmt.executeQuery(sql);
            while (resultSet.next()) {
                String date = resultSet.getString("date_workout").split(" ")[0];
                allDates.add(date);
            }
        }
        catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        return allDates;
    }
    public static void addWorkout(Workout workout){
        try {
            try {
                Class.forName("org.postgresql.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            Connection conn = DatabaseManager.getInstance().getConnection();
            Statement stmt = conn.createStatement();
            String sql="INSERT INTO workout(client_id,instructor_id,car_id,date_workout,duration_workout) VALUES "+ "('"
                    +workout.getClientId() +"','"+workout.getInstructorId()+"','"
                    +workout.getCarId()+"','"+workout.getDate()+"','"+workout.getDuration()+"');";
            int result=stmt.executeUpdate(sql);
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public static void delWorkout(Workout workout){
        try {
            try {
                Class.forName("org.postgresql.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            Connection conn = DatabaseManager.getInstance().getConnection();
            Statement stmt = conn.createStatement();
            String sql="DELETE FROM workout WHERE workout_id="+workout.getId();
            int result=stmt.executeUpdate(sql);
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }
}


