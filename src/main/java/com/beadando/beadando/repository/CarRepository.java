package com.beadando.beadando.repository;

import com.beadando.beadando.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Long> {
}
