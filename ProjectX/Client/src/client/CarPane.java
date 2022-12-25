package client;

import api.data.Car;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CarPane extends JPanel {
    private CarsListPane carsListPane;
    private Car car;
    private JTextField textBrand = new JTextField(20);
    private JTextField textModel=new JTextField(20);
    private JTextField textCategory = new JTextField(20);
    private JButton edit=new JButton("Редактировать");
    private JLabel labelBrand=new JLabel("Бренд:");
    private JLabel labelModel=new JLabel("Модель:");
    private JLabel labelCategory=new JLabel("Категория:");
    private Color defaultColor=textModel.getBackground();


    public CarPane() {
        super(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        textModel.setEnabled(false);
        textCategory.setEnabled(false);
        c.anchor=GridBagConstraints.FIRST_LINE_START;
        c.gridx = 0;
        c.gridy = 0;
        add(labelBrand,c);
        c.gridx = 1;
        c.gridy = 0;
        add(textBrand,c);

        c.gridx = 0;
        c.gridy = 1;
        add(labelModel,c);
        c.gridx = 1;
        c.gridy = 1;

        add(textModel,c);

        c.gridy=2;
        c.gridx=0;
        add(labelCategory,c);
        c.gridy=2;
        c.gridx=1;
        c.weightx=1;

        add(textCategory,c);
        c.weighty=1;
        c.weightx=0;
        c.gridy=3;
        c.gridx=0;
        add(edit,c);
        hideFields();


        edit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(textModel.isEnabled()){
                    if(!textBrand.getText().trim().isEmpty() & !textModel.getText().trim().isEmpty() & !textCategory.getText().trim().isEmpty())
                    {
                        String newBrand=textBrand.getText();
                        String newModel=textModel.getText();
                        String newCategory=textCategory.getText();
                        try {
                            ServiceManager.getInstance().getTestService().updCar(car,newBrand,newModel,newCategory);
                        } catch (ConnectionException er) {
                            er.printStackTrace();
                        }
                        if(carsListPane != null){
                            carsListPane.updCarsListPane();
                        }
                        textBrand.setBackground(defaultColor);
                        textModel.setBackground(defaultColor);
                        textCategory.setBackground(defaultColor);
                        textBrand.setEnabled(false);
                        textModel.setEnabled(false);
                        textCategory.setEnabled(false);
                    }
                    else{
                        if(textBrand.getText().trim().isEmpty()){
                            textBrand.setBackground(new Color(255,92,124,50));
                        }
                        if(textModel.getText().trim().isEmpty()){
                            textModel.setBackground(new Color(255,92,124,50));
                        }
                        if(textCategory.getText().trim().isEmpty()){
                            textCategory.setBackground(new Color(255,92,124,50));
                        }
                    }

                }
                else{
                    textBrand.setEnabled(true);
                    textModel.setEnabled(true);
                    textCategory.setEnabled(true);
                }
            }
        });
    }
    public void setCar(Car car) {

        if(!textModel.isVisible()){
            textBrand.setVisible(true);
            textModel.setVisible(true);
            textCategory.setVisible(true);
            edit.setVisible(true);
            labelBrand.setVisible(true);
            labelModel.setVisible(true);
            labelCategory.setVisible(true);
        }

        this.car = car;
        if (car != null) {
            textBrand.setText(car.getBrand());
            textBrand.setEnabled(false);
            textModel.setText(car.getModel());
            textModel.setEnabled(false);
            textCategory.setText(car.getCategory());
            textCategory.setEnabled(false);
        } else {
            textModel.setText("");
            textModel.setEnabled(false);
        }
    }
    public void hideFields(){
        edit.setVisible(false);
        textBrand.setVisible(false);
        textModel.setVisible(false);
        textCategory.setVisible(false);
        labelBrand.setVisible(false);
        labelModel.setVisible(false);
        labelCategory.setVisible(false);
    }

    public void setCarsListPane(CarsListPane carsListPane) {
        this.carsListPane = carsListPane;
    }
}
