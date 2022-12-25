package client;

import javax.swing.*;
import java.awt.*;

public class DataPane extends JPanel {
    public DataPane() {
        super();
        setLayout(new BorderLayout());
        JTabbedPane tabbedPane = new JTabbedPane(SwingConstants.LEFT);
        tabbedPane.addTab("Клиенты", new PersonsPane());
        tabbedPane.addTab("Инструкторы", new InstructorsPane()); //todo дописать панель Инструкторы
        tabbedPane.addTab("Автомобили", new CarsPane()); //todo дописать панель Автомобили
        add(tabbedPane);
    }
}
