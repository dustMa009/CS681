package edu.umb.cs681.hw16;

import java.util.ArrayList;

public class Car {
    private String model, make;
    private int mileage, year, dominationCount;
    private float price;

    public Car(String model, String make, int mileage, int year, float price) {
        this.model = model;
        this.make = make;
        this.mileage = mileage;
        this.year = year;
        this.price = price;
        this.dominationCount = 0;
    }

    public String getModel() {
        return model;
    }

    public String getMake() {
        return make;
    }

    public int getMileage() {
        return mileage;
    }

    public int getYear() {
        return year;
    }

    public float getPrice() {
        return price;
    }

    public void setDominationCount(ArrayList<Car> cars) {
        for (Car c : cars) {
            if (c.getPrice() <= this.price && c.getYear() <= this.year && c.getMileage() <= this.mileage) {
                if (c.getPrice() < this.price || c.getYear() < this.year || c.getMileage() < this.mileage) {
                    this.dominationCount++;
                }
            }
        }
    }

    public int getDominationCount() { return this.dominationCount; }
}
