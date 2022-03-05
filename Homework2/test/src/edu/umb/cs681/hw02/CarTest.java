package edu.umb.cs681.hw02;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
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
        cars.add(car1);
        cars.add(car2);
        cars.add(car3);
        cars.add(car4);
        cars.add(car5);
    }

    @Test
    public void StreamMinTest() {
        float minPrice = cars.stream().map((Car car) -> car.getPrice()).min(Comparator.comparing(year -> year)).get();
        assertEquals(6000.0f, minPrice);
    }

    @Test
    public void StreamMaxTest() {
        float maxPrice = cars.stream().map((Car car) -> car.getPrice()).max(Comparator.comparing(year -> year)).get();
        assertEquals(10000.0f, maxPrice);
    }

    @Test
    public void averageTest() {
        float averagePrice = cars.stream().map(car -> car.getPrice()).reduce(new float[2],
                (result, price) -> {
                    result[1] = (result[0] * result[1] + price) / ++result[0];
                    return result;
                },
                (finalResult, intermediateResult) -> finalResult
                )[1];
        assertEquals(8300.1f, averagePrice);
    }
}
