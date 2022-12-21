package client;

import api.services.TestService;
import com.caucho.hessian.client.HessianProxyFactory;

import java.net.MalformedURLException;

public class TestClient {
    public static void main(String[] args) throws MalformedURLException {
        String url = "http://localhost:8080/TestService";

        HessianProxyFactory factory = new HessianProxyFactory();
        factory.setOverloadEnabled(true);
        TestService service = (TestService) factory.create(TestService.class, url);

        System.out.println(service.plus(9,5));
        System.out.println(service.umn(77,34));
        System.out.println(service.minus(44,34));
        System.out.println(service.plus(1,1));
        System.out.println(service.getAllPerson());
    }
}
