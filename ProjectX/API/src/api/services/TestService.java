package api.services;

import api.data.Person;

import java.util.List;

public interface TestService {

    int plus(int a, int b);

    int umn(int a, int b);

    int minus(int a, int b);

    void ping();

    List<Person> getAllPerson();
}
