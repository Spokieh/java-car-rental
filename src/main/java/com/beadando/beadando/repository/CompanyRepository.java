package com.beadando.beadando.repository;

import com.beadando.beadando.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {

}
