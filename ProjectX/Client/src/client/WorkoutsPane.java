package client;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Calendar;
import java.util.Date;

public class WorkoutsPane extends JPanel {
    private Date date;
    private Calendar calendar;
    private int month;
    private int year;
    private CalendarPane calendarPane;
    private WorkoutsListPane workoutsListPane;

    public WorkoutsPane(){
        super(new BorderLayout());
        calendar=Calendar.getInstance();
        month=calendar.get(Calendar.MONTH);
        year=calendar.get(Calendar.YEAR);
        workoutsListPane=new WorkoutsListPane();
        calendarPane=new CalendarPane(month,year,workoutsListPane);
        workoutsListPane.setCalendarPane(calendarPane);
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, workoutsListPane, calendarPane);
        splitPane.setDividerLocation(290);
        splitPane.addPropertyChangeListener("dividerLocation", new PropertyChangeListener()
        {
            @Override
            public void propertyChange(PropertyChangeEvent e)
            {
                int location = ((Integer)e.getNewValue()).intValue();
                System.out.println(location);

                if (location != 290)
                {
                    JSplitPane splitPane = (JSplitPane)e.getSource();
                    splitPane.setDividerLocation(290);
                }
            }
        });

        add(splitPane);
    }


}
