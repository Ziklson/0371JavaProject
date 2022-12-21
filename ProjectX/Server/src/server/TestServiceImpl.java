package server;

import api.data.Person;
import api.services.TestService;
import com.caucho.hessian.server.HessianServlet;

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

    @Override
    public List<Person> getAllPerson(){return DataManager.getAllPerson();}

}

