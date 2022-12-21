package client;

import javax.swing.*;

public class Application {
    public static void main(String[] args) {

        try{
            ServiceManager.getInstance();
        }
        catch (ConnectionException e){
            JOptionPane.showMessageDialog(null, "Сервер не отвечает","Ошибка подключения",JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }

        MainFrame mainFrame = new MainFrame();
        mainFrame.setSize(1000, 600);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);

    }
}
