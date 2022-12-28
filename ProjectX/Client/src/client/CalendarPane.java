package client;

import api.data.Person;
import api.data.Workout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;


public class CalendarPane extends JPanel {
    private List<Cell> cells=new ArrayList<>();
    private String[] months={"January","February","March","April","May","June","July","August","September",
    "October","November","December"};
    private int month;
    private int year;
    List<Workout> workoutByDate=null;
    private WorkoutsListPane workoutsListPane;
    List<String> allDates=new ArrayList<>();
    private Color defaultColor=new JButton().getBackground();
    private Cell lastCellPressed=null;
    private Color lastCellPressedColor=null;


    public CalendarPane(int month, int year, WorkoutsListPane workoutsListPane){
        super();
        this.workoutsListPane=workoutsListPane;
        this.month=month;
        this.year=year;

        GridBagLayout layout = new GridBagLayout();
        setLayout(layout);
        GridBagConstraints c = new GridBagConstraints();

        for(int i=0;i<52;i++){
            cells.add(new Cell());
        }
        for(int i=0;i<10;i++){
            cells.get(i).asTitle();
        }
        cells.get(0).setText("Back");
        cells.get(0).setBorder(BorderFactory.createRaisedBevelBorder());
        cells.get(0).setContentAreaFilled(true);

        cells.get(0).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int curMonth=getMonth();
                int curYear=getYear();
                if(curMonth == 0){
                    setMonth(11);
                    setYear(curYear-1);
                }
                else{
                    setMonth(curMonth-1);
                }
                cells.get(1).setText(getTitle());
                lastCellPressed=null;
                lastCellPressedColor=null;
                setDate();
            }
        });



        cells.get(1).setText(getTitle());

        cells.get(2).setText("Next");
        cells.get(2).setContentAreaFilled(true);
        cells.get(2).setBorder(BorderFactory.createRaisedBevelBorder());
        cells.get(2).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int curMonth=getMonth();
                int curYear=getYear();
                if(curMonth == 11){
                    setMonth(0);
                    setYear(curYear+1);
                }
                else{
                    setMonth(curMonth+1);
                }
                cells.get(1).setText(getTitle());
                lastCellPressed=null;
                lastCellPressedColor=null;
                setDate();
            }
        });


        cells.get(3).setText("Mon");
        cells.get(4).setText("Tue");
        cells.get(5).setText("Wed");
        cells.get(6).setText("Thu");
        cells.get(7).setText("Fri");
        cells.get(8).setText("Sat");
        cells.get(9).setText("Sun");
        c.insets=new Insets(1, 0,1 , 0);
        c.fill = GridBagConstraints.BOTH;
        c.weightx=2;
        c.weighty=2;
        c.gridx=0;
        c.gridy=0;
        c.gridwidth=2;
        add(cells.get(0),c);
        c.gridx=2;
        c.gridwidth=3;
        add(cells.get(1),c);
        c.gridx=5;
        c.gridwidth=2;
        add(cells.get(2),c);
        c.gridy=1;
        c.gridx=0;
        c.gridwidth=1;
        c.insets=new Insets(1, 1,1 , 1);;
        for(int i=3;i<52;i++){
            add(cells.get(i),c);

            if(c.gridx < 6){
                c.gridx++;
            }
            else{
                c.gridx=0;
                c.gridy++;
            }
        }
        setDate();
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public WorkoutsListPane getWorkoutsListPane() {
        return workoutsListPane;
    }

    public void setYear(int year) {
        this.year = year;
    }
    public String getTitle(){
        return this.months[month] + " " + this.year;
    }
    public void setDate(){
        try {
            allDates=ServiceManager.getInstance().getTestService().getAllDates();
        } catch (ConnectionException e) {
            e.printStackTrace();
        }
        Calendar calendar=Calendar.getInstance();
        calendar.set(Calendar.YEAR,year);
        calendar.set(Calendar.MONTH,month);
        calendar.set(Calendar.DATE,1);
        int startDay=calendar.get(Calendar.DAY_OF_WEEK)-2;
        if(startDay==-1){
            startDay=6;
        }
        if((startDay==6 & (month != 1)) | ((startDay == 5) &(month == 0 | month == 2 | month == 4 | month == 6 | month == 7 | month== 9 | month == 11 ))){
            GridBagConstraints c = new GridBagConstraints();
            c.gridx=0;
            c.gridy=7;
            c.gridwidth=1;
            c.insets=new Insets(1, 1,1 , 1);
            c.fill = GridBagConstraints.BOTH;
            c.weightx=2;
            c.weighty=2;
            for(int i=45;i<52;i++){
                add(cells.get(i),c);

                if(c.gridx < 6){
                    c.gridx++;
                }
                else{
                    c.gridx=0;
                    c.gridy++;
                }
            }
        }
        else {
            if (getComponents().length == 52) {
            for (int i = 51; i > 44; i--)
                remove(i);
            }
        }
        calendar.add(Calendar.DATE,-startDay);
        for(Component com:getComponents()){
            Cell cell = (Cell)com;
            if(!cell.isTitle()){
                cell.setText(calendar.get(Calendar.DATE)+"");
                cell.setDate(calendar.getTime());
                cell.currentMonth(calendar.get(Calendar.MONTH)==month);
                int curMonth = month + 1; // For table month starts with 1, not with 0
                String curDate = year + "-" + curMonth + "-" + cell.getText();
                if(!allDates.isEmpty()){
                    if(allDates.contains(curDate)){
                        cell.setBackground(new Color(156,255,173));
                        if(cell == lastCellPressed){
                            lastCellPressedColor=cell.getBackground();
                            cell.setBackground(new Color(96, 182, 168));

                        }
                    }
                    else{
                        cell.setBackground(defaultColor);
                        if(cell == lastCellPressed){
                            lastCellPressedColor=cell.getBackground();
                            cell.setBackground(new Color(96, 182, 168));
                        }
                    }
                }
                else{
                    cell.setBackground(defaultColor);
                    if(cell == lastCellPressed){
                        lastCellPressedColor=cell.getBackground();
                        cell.setBackground(new Color(96, 182, 168));
                    }
                }
                if(calendar.get(Calendar.MONTH)==month){
                    cell.setBorder(BorderFactory.createRaisedBevelBorder());
                    cell.setContentAreaFilled(true);
                    if(Arrays.stream(cell.getActionListeners()).count() != 1) {
                        cell.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                int curMonth = month + 1; // For table month starts with 1, not with 0
                                String curDate = year + "-" + curMonth + "-" + cell.getText();
                                workoutsListPane.setCurDate(curDate);
                                if(lastCellPressed != null){
                                    lastCellPressed.setBackground(lastCellPressedColor);
                                    lastCellPressedColor=cell.getBackground();
                                    lastCellPressed=cell;
                                }
                                if(lastCellPressed == null) {
                                    lastCellPressed=cell;
                                    lastCellPressedColor=cell.getBackground();
                                }
                                cell.setBackground(new Color(96, 182, 168));


                                System.out.println(curDate);
                            try {
                                workoutByDate=ServiceManager.getInstance().getTestService().getWorkoutByDate(curDate);
                            } catch (ConnectionException ex) {
                                ex.printStackTrace();
                            }
                            if(workoutByDate != null)
                                if(!workoutByDate.isEmpty()) {
                                    workoutsListPane.updWorkouts(workoutByDate);
                                }
                                else{
                                    workoutsListPane.updWorkouts(workoutByDate);
                                }
                            }
                        });
                    }
                }
                else{
                    cell.setContentAreaFilled(false);
                    cell.setBorder(null);
                    if(Arrays.stream(cell.getActionListeners()).count() != 0)
                        cell.removeActionListener(cell.getActionListeners()[0]);
                }
                calendar.add(Calendar.DATE,1);
            }
        }

    }

    public List<Workout> getWorkoutByDate() {
        return workoutByDate;
    }

    public void updWorkoutByDate(String curDate) {
        try {
            workoutByDate=ServiceManager.getInstance().getTestService().getWorkoutByDate(curDate);
        } catch (ConnectionException ex) {
            ex.printStackTrace();
        }
    }
}
