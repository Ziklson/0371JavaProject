package client;

import api.data.Car;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class CarsListPane extends JPanel {
    private List<Car> allCars= null;
    private CarsListModel carsListModel = new CarsListModel();
    private JList<Car> list = new JList<>(carsListModel);



    public CarsListPane(CarPane carPane) {
        super(new BorderLayout());
        JButton addButton = new JButton("Добавить");
        JButton delButton = new JButton("Удалить");

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        list.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                setText(((Car) value).getBrand() + " " +((Car) value).getModel() );
                return this;
            }
        });

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTextField brand=  new JTextField();
                JTextField model = new JTextField();
                JTextField category=new JTextField();
                Object[] message={
                        "Введите Бренд:", brand,
                        "Введите Модель:",model,
                        "Введите Категорию", category
                };

                int dialogResult=JOptionPane.showConfirmDialog(
                        CarsListPane.this,
                        message,
                        "Создание",
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.PLAIN_MESSAGE);
                if(dialogResult == 0) // OK option
                {
                    if (!brand.getText().trim().isEmpty() & !model.getText().trim().isEmpty() & !category.getText().trim().isEmpty()) {
                        Car car = new Car();
                        car.setBrand(brand.getText());
                        car.setModel(model.getText());
                        car.setCategory(category.getText());
                        carsListModel.addCar(car);
                        try {
                            ServiceManager.getInstance().getTestService().addCar(car);
                        } catch (ConnectionException er) {
                            er.printStackTrace();
                        }
                        updCarsListPane();
                        //todo add Person
                    }
                    else{

                    }
                }

            }
        });

        delButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = list.getSelectedIndex();
                Car selectedCar=list.getSelectedValue();
                carsListModel.deleteCar(selectedIndex);
                carPane.hideFields();
                try {
                    ServiceManager.getInstance().getTestService().delCar(selectedCar);
                } catch (ConnectionException er) {
                    er.printStackTrace();
                }

            }
        });

        list.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount()==2){
                    Car selectedCar = list.getSelectedValue();
                    carPane.setCar(selectedCar);
                    // todo   - переправить Объект в PersonsPane
                }
            }
        });


        buttonPanel.add(addButton);
        buttonPanel.add(delButton);

        add(buttonPanel, BorderLayout.NORTH);
        add(list, BorderLayout.CENTER);
        updCarsListPane();

        carPane.setCarsListPane(this);
    }



    void updCarsListPane() {
        if (carsListModel.getSize() != 0){
            carsListModel.clearCar();
        }
        try {
            allCars = ServiceManager.getInstance().getTestService().getAllCar();
        } catch (ConnectionException e) {
            e.printStackTrace();
        }
        for (Car car: allCars) {
            carsListModel.addCar(car);
        }
    }
}
