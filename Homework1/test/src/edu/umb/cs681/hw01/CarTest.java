package edu.umb.cs681.hw01;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CarTest {
    private static Car car1, car2, car3, car4, car5;
    private static ArrayList<Car> cars;

    @BeforeAll
    public static void setUp() {
        car1 = new Car("ModelA", "make5", 1200, 2012, 9000.5f);
        car2 = new Car("ModelB","make8", 2500, 2018, 8500.0f);
        car3 = new Car("ModelC","make9", 1000, 2010, 8000.0f);
        car4 = new Car("ModelD","make10", 2000, 2021, 10000.0f);
        car5 = new Car("ModelE","make3", 800, 2009, 6000.0f);
        cars = new ArrayList<Car>();
    }

    @Test
    public void sortWithMileage() {
        Car[] expected = {car5, car3, car1, car4, car2};
        List<Car> sortedCars = Stream.of(car1, car2, car3, car4, car5).sorted((Car c1, Car c2) -> c1.getMileage() - c2.getMileage())
                .collect(Collectors.toList());
        assertEquals(5, sortedCars.size());
        int i = 0;
        for (Car c : sortedCars) {
            assertSame(expected[i], c);
            i++;
        }
    }

    @Test
    public void sortWithPrice() {
        Car[] expected = {car5, car3, car2, car1, car4};
        List<Car> sortedCars = Stream.of(car1, car2, car3, car4, car5).sorted((Car c1, Car c2) -> (int)(c1.getPrice() - c2.getPrice()))
                .collect(Collectors.toList());
        assertEquals(5, sortedCars.size());
        int i = 0;
        for (Car c : sortedCars) {
            assertSame(expected[i], c);
            i++;
        }
    }

    @Test
    public void sortWithYear() {
        Car[] expected = {car5, car3, car1, car2, car4};
        List<Car> sortedCars = Stream.of(car1, car2, car3, car4, car5).sorted((Car c1, Car c2) -> c1.getYear() - c2.getYear())
                .collect(Collectors.toList());
        assertEquals(5, sortedCars.size());
        int i = 0;
        for (Car c : sortedCars) {
            assertSame(expected[i], c);
            i++;
        }
    }

    @Test
    public void sortWithDominationCount() {
        Car[] expected = {car5, car3, car1, car2, car4};
        cars.add(car1);
        cars.add(car2);
        cars.add(car3);
        cars.add(car4);
        cars.add(car5);
        for (Car c : cars) {
            c.setDominationCount(cars);
        }
        List<Car> sortedCars = cars.stream().sorted((Car c1, Car c2) -> c1.getDominationCount() - c2.getDominationCount())
                .collect(Collectors.toList());
        assertEquals(5, sortedCars.size());
        int i = 0;
        for (Car c : sortedCars) {
            assertSame(expected[i], c);
            i++;
        }
    }
}
