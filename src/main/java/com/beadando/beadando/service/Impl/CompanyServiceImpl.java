package com.beadando.beadando.service.Impl;

import com.beadando.beadando.model.Company;
import com.beadando.beadando.repository.CarRepository;
import com.beadando.beadando.repository.CompanyRepository;
import com.beadando.beadando.service.CompanyService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {
    private CompanyRepository companyRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository) {
        super();
        this.companyRepository = companyRepository;
    }
    @Override
    public List<Company> getAllComapny() {
        return companyRepository.findAll();
    }

    @Override
    public Company saveCompany(Company company) {
        return companyRepository.save(company);
    }

    @Override
    public Company getCompanyById(Long id) {
        return companyRepository.findById(id).get();
    }

    @Override
    public Company updateCompany(Company company) {
        return companyRepository.save(company);
    }

    @Override
    public void deleteCompanyById(Long id) {
        companyRepository.deleteById(id);
    }
}
