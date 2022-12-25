package client;

import javax.swing.*;
import java.awt.*;

public class CarsPane extends JPanel {
    private CarPane carPane = new CarPane();
    private CarsListPane carsListPane = new CarsListPane(carPane);

    public CarsPane() {
        super(new BorderLayout());

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, carsListPane, carPane);
        splitPane.setDividerLocation(200);
        add(splitPane);
    }
}
