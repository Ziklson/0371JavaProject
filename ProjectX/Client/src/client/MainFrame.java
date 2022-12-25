package client;

import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import java.awt.*;

public class MainFrame extends JFrame {
    public MainFrame() throws HeadlessException {
        super("JavaApp");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        try {
            UIManager.setLookAndFeel(new NimbusLookAndFeel());
        } catch (UnsupportedLookAndFeelException e) {
            System.out.println("NimbusLookAndFeel not install");
        }

        setLayout(new BorderLayout());
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Расписание", new WorkoutsPane()); //todo дописать панель Расписание
        tabbedPane.addTab("База", new DataPane());

        add(tabbedPane);
    }
}
