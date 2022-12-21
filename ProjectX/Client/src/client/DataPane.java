package client;

import javax.swing.*;
import java.awt.*;

public class DataPane extends JPanel {
    public DataPane() {
        super();
        setLayout(new BorderLayout());
        JTabbedPane tabbedPane = new JTabbedPane(SwingConstants.LEFT);
        tabbedPane.addTab("Актёры", new PersonsPane());
        tabbedPane.addTab("Тренеры", new JButton("Тренеры")); //todo дописать панель Тренеры
        tabbedPane.addTab("Клиенты", new JButton("Клиенты")); //todo дописать панель Клиенты
        add(tabbedPane);
    }
}
