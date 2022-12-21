package server;

import api.data.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataManager {
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
            ResultSet resultSet = stmt.executeQuery("SELECT * FROM for_test");

            while (resultSet.next()) {
                String id = resultSet.getString("test_id");
                String name = resultSet.getString("test_name");
                Person person = new Person();
                person.setId(id);
                person.setName(name);
                persons.add(person);
            }
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return persons;
    }
}


