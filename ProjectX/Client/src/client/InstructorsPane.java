package client;

import javax.swing.*;
import java.awt.*;

public class InstructorsPane extends JPanel {
    private InstructorPane instructorPane = new InstructorPane();
    private InstructorsListPane instructorsListPane = new InstructorsListPane(instructorPane);

    public InstructorsPane() {
        super(new BorderLayout());

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, instructorsListPane, instructorPane);
        splitPane.setDividerLocation(200);
        add(splitPane);
    }
}
