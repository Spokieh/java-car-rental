package com.beadando.beadando.controller;

import com.beadando.beadando.Config.UserInfoDetails;
import com.beadando.beadando.Config.UserInfoDetailsService;
import com.beadando.beadando.model.Car;
import com.beadando.beadando.model.Company;
import com.beadando.beadando.model.User;
import com.beadando.beadando.service.CarService;
import com.beadando.beadando.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import com.beadando.beadando.model.Rental;
import com.beadando.beadando.service.RentalService;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Controller
//@RequestMapping("/rentals")
public class RentalController {

    @Autowired
    private RentalService rentalService;

    @Autowired
    private CarService carService;

    @Autowired
    private UserService userService;

    @GetMapping("/rentals")
    public String showRentals(Model model) {
        model.addAttribute("rentals", rentalService.getAllRentals());
        model.addAttribute("rental", new Rental());
        return "rentals";
    }

    @GetMapping("/rentals/create")
    public String createRental(Model model) {
        Rental newRental = new Rental();
        List<Car> cars = carService.getAllCars();
        model.addAttribute("cars", cars);
        model.addAttribute("rental", newRental);

        return "rentals_create";
    }

    @PostMapping("/rentals/create")
//    @PreAuthorize("hasAuthority('MAINTAINER') or hasAuthority('ADMIN')")
    public String saveRental(@ModelAttribute("rental") Rental rental) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String username = userDetails.getUsername();
        Optional<User> currentUser = userService.findUserByName(username);

        if (currentUser.isPresent()) {
            User user = currentUser.get();
            rentalService.rentCar(user, rental.getCar(), LocalDate.now(), rental.getRentalDurationDays());
        } else {
            System.out.println("User not found");
        }

        return "redirect:/rentals";
    }

    @GetMapping("/rentals/{id}")
    @PreAuthorize("hasAuthority('MAINTAINER') or hasAuthority('ADMIN')")
    public String deleteRental(@PathVariable Long id) {
        rentalService.deleteRentalById(id);

        return "redirect:/rentals";

    }
}
