package com.beadando.beadando.service;

import com.beadando.beadando.model.Car;
import com.beadando.beadando.model.Rental;
import com.beadando.beadando.model.User;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface RentalService {
    Rental rentCar(User user, Car car, LocalDate rentalStartDate, int rentalDurationDays);

    List<Rental> getAllRentals();

    void deleteRentalById(Long id);
}
