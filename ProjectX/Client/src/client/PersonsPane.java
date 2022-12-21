package client;

import javax.swing.*;
import java.awt.*;

public class PersonsPane extends JPanel {
    //todo дописать панель Лошади
    public PersonsPane() {
        super(new BorderLayout());
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, new PersonsListPane(), new PersonPane());
        splitPane.setDividerLocation(200);
        add(splitPane);
    }
}
