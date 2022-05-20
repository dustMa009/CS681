package edu.umb.cs681.hw16;

import java.util.ArrayList;

public class CarTest {
    public static void main(String[] args) {
        ArrayList<Car> cars = new ArrayList<>();
        Car car1 = new Car("ModelA", "make5", 1200, 2012, 9000.5f);
        Car car2 = new Car("ModelB","make8", 2500, 2018, 8500.0f);
        Car car3 = new Car("ModelC","make9", 1000, 2010, 8000.0f);
        Car car4 = new Car("ModelD","make10", 2000, 2021, 10000.0f);
        Car car5 = new Car("ModelE","make3", 800, 2009, 6000.0f);
        Car car6 = new Car("ModelE","make8", 800, 2009, 6000.0f);
        cars.add(car1);
        cars.add(car2);
        cars.add(car3);
        cars.add(car4);
        cars.add(car5);
        cars.add(car6);

        int makeCount = cars.stream().parallel().map((Car car) -> car.getMake())
                .reduce(0, (result, carMaker) -> ++result, (finalResult, intermediateResult) ->
                {return finalResult + intermediateResult; });

        System.out.println("Total number of make: " + makeCount);
    }
}
