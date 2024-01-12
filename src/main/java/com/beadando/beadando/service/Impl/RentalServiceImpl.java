package com.beadando.beadando.service.Impl;

import com.beadando.beadando.model.Car;
import com.beadando.beadando.model.Rental;
import com.beadando.beadando.model.User;
import com.beadando.beadando.repository.RentalRepository;
import com.beadando.beadando.service.RentalService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
public class RentalServiceImpl implements RentalService {

    private RentalRepository rentalRepository;

    public RentalServiceImpl(RentalRepository rentalRepository) {
        super();
        this.rentalRepository = rentalRepository;
    }

    @Override
    public Rental rentCar(User user, Car car, LocalDate rentalStartDate, int rentalDurationDays) {
        Rental rental = new Rental();
        rental.setUser(user);
        rental.setCar(car);
        rental.setRentalStartDate(rentalStartDate);

        // Calculate rental end date based on start date and duration
        LocalDate rentalEndDate = rentalStartDate.plusDays(rentalDurationDays);
        rental.setRentalEndDate(rentalEndDate);

        // Calculate total cost based on your business logic
        double totalCost = calculateTotalCost(rentalStartDate, rentalEndDate, car.getRentalPrice());
        rental.setTotalCost(totalCost);

        return rentalRepository.save(rental);
    }

    @Override
    public List<Rental> getAllRentals() {
        return rentalRepository.findAll();
    }

    @Override
    public void deleteRentalById(Long id) {
        rentalRepository.deleteById(id);
    }

    private double calculateTotalCost(LocalDate startDate, LocalDate endDate, double rentalPrice) {
        long daysBetween = ChronoUnit.DAYS.between(startDate, endDate);
        return rentalPrice * daysBetween;
    }
}
