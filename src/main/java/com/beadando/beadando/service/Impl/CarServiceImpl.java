package com.beadando.beadando.service.Impl;

import com.beadando.beadando.model.Car;
import com.beadando.beadando.repository.CarRepository;
import com.beadando.beadando.service.CarService;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class CarServiceImpl implements CarService {

    private CarRepository carRepository;

    public CarServiceImpl(CarRepository carRepository) {
        super();
        this.carRepository = carRepository;
    }

    @Override
    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    @Override
    public Car saveCar(Car car) {
        return carRepository.save(car);
    }

    @Override
    public Car getCarById(Long id) {
        return carRepository.findById(id).get();
    }

    @Override
    public Car updateCar(Car car) {
        return carRepository.save(car);
    }

    @Override
    public void deleteCarById(Long id) {
        carRepository.deleteById(id);
    }

    public void generateExcel(HttpServletResponse response) throws Exception {
        List<Car> cars = getAllCars();

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Cars");
        HSSFRow row = sheet.createRow(0);
        row.createCell(0).setCellValue("ID");
        row.createCell(1).setCellValue("Brand");
        row.createCell(2).setCellValue("Model");
        row.createCell(3).setCellValue("Year");
        row.createCell(4).setCellValue("Rental Price");
//        company

        int dataRowIndex = 1;

        for (Car car: cars) {
            HSSFRow dataRow = sheet.createRow(dataRowIndex);
            dataRow.createCell(0).setCellValue(car.getId());
            dataRow.createCell(1).setCellValue(car.getBrand());
            dataRow.createCell(2).setCellValue(car.getModel());
            dataRow.createCell(3).setCellValue(car.getYear());
            dataRow.createCell(4).setCellValue(car.getRentalPrice());
            dataRowIndex++;
        }

        ServletOutputStream ops = response.getOutputStream();

        workbook.write(ops);
        workbook.close();
        ops.close();

    }
}
