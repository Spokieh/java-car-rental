package com.beadando.beadando.service;

import com.beadando.beadando.model.Company;

import java.util.List;

public interface CompanyService {
    List<Company> getAllComapny();
    Company saveCompany(Company company);
    Company getCompanyById(Long id);
    Company updateCompany(Company company);
    void deleteCompanyById(Long id);
}
