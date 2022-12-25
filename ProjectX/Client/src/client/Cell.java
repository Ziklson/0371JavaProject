package client;

import javax.swing.*;
import java.awt.*;
import java.util.Date;

public class Cell extends JButton {

    private Boolean title=false;
    private Date date;


    public Cell(){
        setContentAreaFilled(false);
        setBorder(null);
        setHorizontalAlignment(JLabel.CENTER);
    }

    public void asTitle(){
        title=true;
    }
    public Boolean isTitle(){
        return title;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void currentMonth(Boolean act){
        if(act){
            setForeground(new Color(68,68,68));
        }
        else{
            setForeground(new Color(169,169,169));
        }

    }
}
