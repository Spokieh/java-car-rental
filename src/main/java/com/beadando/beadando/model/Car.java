package com.beadando.beadando.model;

import jakarta.persistence.*;

@Entity
@Table(name = "cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "brand", nullable = false)
    private String brand;
    @Column(name = "model", nullable = false)
    private String model;
    @Column(name = "year", nullable = false)
    private int year;
    @Column(name = "rental_price", nullable = false)
    private double rentalPrice;
    @Column(name = "available", nullable = false)
    private boolean available;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    public Car() {
        // Default constructor
    }

    public Car(String brand, String model, int year, double rentalPrice, boolean available) {
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.rentalPrice = rentalPrice;
        this.available = true;
    }

    // Constructor with parameters
    public Car(String brand, String model, int year, double rentalPrice, boolean available, Company company) {
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.rentalPrice = rentalPrice;
        this.available = available;
        this.company = company;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getRentalPrice() {
        return rentalPrice;
    }

    public void setRentalPrice(double rentalPrice) {
        this.rentalPrice = rentalPrice;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
