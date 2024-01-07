package com.beadando.beadando.service;

import com.beadando.beadando.model.Car;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;

public interface CarService {
    List<Car> getAllCars();
    Car saveCar(Car car);
    Car getCarById(Long id);
    Car updateCar(Car car);
    void deleteCarById(Long id);

    void generateExcel(HttpServletResponse response) throws Exception;
}
