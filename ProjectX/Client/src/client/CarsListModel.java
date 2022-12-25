package client;

import api.data.Car;

import javax.swing.*;
import java.util.ArrayList;

public class CarsListModel extends AbstractListModel {
    private ArrayList<Car> list = new ArrayList<>();


    @Override
    public int getSize() {
        return list.size();
    }

    @Override
    public Object getElementAt(int index) {
        return list.get(index);
    }

    public void addCar(Car car) {
        list.add(car);
        fireIntervalAdded(car, list.size() - 1, list.size() - 1);
    }

    public void deleteCar(int index) {
        Car removeCar = list.remove(index);
        fireIntervalRemoved(removeCar, index, index);
    }
    public void clearCar(){
        list.clear();
    }
}
