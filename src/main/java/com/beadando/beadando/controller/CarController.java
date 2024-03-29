package com.beadando.beadando.controller;

import com.beadando.beadando.model.Car;
import com.beadando.beadando.model.Company;
import com.beadando.beadando.repository.CompanyRepository;
import com.beadando.beadando.service.CarService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
public class CarController {

    private CarService carService;

    private CompanyRepository companyRepository;


    public CarController(CarService carService, CompanyRepository companyRepository) {
        super();
        this.carService = carService;
        this.companyRepository = companyRepository;
    }


    @GetMapping("/")
    public String showCars() {
        return "index";
    }

    @GetMapping("/cars")
    public String showCars(Model model) {
        model.addAttribute("cars", carService.getAllCars());
        model.addAttribute("car", new Car());
        return "cars";
    }

    @GetMapping("/cars/create")
    @PreAuthorize("hasAuthority('MAINTAINER') or hasAuthority('ADMIN')")
    public String createCar(Model model) {
        Car newCar = new Car();
        List<Company> companies = companyRepository.findAll();
        model.addAttribute("companies", companies);
        model.addAttribute("car", newCar);
        return "cars_create";
    }

    @PostMapping("/cars/create")
    @PreAuthorize("hasAuthority('MAINTAINER') or hasAuthority('ADMIN')")
    public String saveCar(@ModelAttribute("car") Car car) {
        carService.saveCar(car);

        return "redirect:/cars";
    }

    @GetMapping("/cars/edit/{id}")
    @PreAuthorize("hasAuthority('MAINTAINER') or hasAuthority('ADMIN')")
    public String editCar(@PathVariable Long id, Model model) {
        Car car = carService.getCarById(id);
        List<Company> companies = companyRepository.findAll();

        if (car == null) {
            return "redirect:/cars";
        }

        model.addAttribute("car", car);
        model.addAttribute("companies", companies);
        return "cars_edit";
    }

    @PostMapping("/cars/{id}")
    @PreAuthorize("hasAuthority('MAINTAINER') or hasAuthority('ADMIN')")
    public String updateCar(@PathVariable Long id,
                                @ModelAttribute("car") Car car,
                                Model model) {

        Car ec = carService.getCarById(id);

        if (ec == null) {
            return "redirect:/cars";
        }

        ec.setId(id);
        ec.setBrand(car.getBrand());
        ec.setModel(car.getModel());
        ec.setYear(car.getYear());
        ec.setRentalPrice(car.getRentalPrice());
        ec.setAvailable(true);


        carService.updateCar(car);
        return "redirect:/cars";
    }

    @GetMapping("/cars/{id}")
    @PreAuthorize("hasAuthority('MAINTAINER') or hasAuthority('ADMIN')")
    public String deleteCar(@PathVariable Long id) {
        carService.deleteCarById(id);

        return "redirect:/cars";

    }
}
