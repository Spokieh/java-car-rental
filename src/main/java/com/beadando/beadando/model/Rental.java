package com.beadando.beadando.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "rentals")
public class Rental {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "car_id", nullable = false)
    private Car car;

    @Column(name = "rental_start_date", nullable = false)
    private LocalDate rentalStartDate;

    @Column(name = "rental_end_date", nullable = false)
    private LocalDate rentalEndDate;

    @Column(name = "rental_duration_days", nullable = false)
    private int rentalDurationDays;

    public Rental(User user, Car car, LocalDate rentalStartDate, LocalDate rentalEndDate, int rentalDurationDays, double totalCost) {
        this.user = user;
        this.car = car;
        this.rentalStartDate = rentalStartDate;
        this.rentalEndDate = rentalEndDate;
        this.rentalDurationDays = rentalDurationDays;
        this.totalCost = totalCost;
    }

    public int getRentalDurationDays() {
        return rentalDurationDays;
    }

    public void setRentalDurationDays(int rentalDurationDays) {
        this.rentalDurationDays = rentalDurationDays;
    }

    @Column(name = "total_cost", nullable = false)
    private double totalCost;

    // Additional fields, such as payment status, rental status, etc., can be added as needed.

    public Rental() {}

    public Rental(User user, Car car, LocalDate rentalStartDate, LocalDate rentalEndDate, double totalCost) {
        this.user = user;
        this.car = car;
        this.rentalStartDate = rentalStartDate;
        this.rentalEndDate = rentalEndDate;
        this.totalCost = totalCost;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public LocalDate getRentalStartDate() {
        return rentalStartDate;
    }

    public void setRentalStartDate(LocalDate rentalStartDate) {
        this.rentalStartDate = rentalStartDate;
    }

    public LocalDate getRentalEndDate() {
        return rentalEndDate;
    }

    public void setRentalEndDate(LocalDate rentalEndDate) {
        this.rentalEndDate = rentalEndDate;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }
}
