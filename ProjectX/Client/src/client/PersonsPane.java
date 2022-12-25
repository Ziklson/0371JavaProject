package client;

import javax.swing.*;
import java.awt.*;

public class PersonsPane extends JPanel {
    private PersonPane personPane = new PersonPane();
    private PersonsListPane personsListPane = new PersonsListPane(personPane);


    public PersonsPane() {
        super(new BorderLayout());

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, personsListPane, personPane);
        splitPane.setDividerLocation(200);
        add(splitPane);
    }

}
