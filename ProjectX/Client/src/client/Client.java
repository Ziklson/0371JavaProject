package client;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    public static void main(String[] args) {
        try(Socket socket = new Socket("localhost", 80)){
            OutputStream outputStream = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(outputStream,true);
            writer.println("abc\n");
            writer.println("def");

            InputStream inputStream = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            String s = reader.readLine();
            System.out.println(s);

        } catch (UnknownHostException e) {
            System.out.println("UnknownHostException");
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

