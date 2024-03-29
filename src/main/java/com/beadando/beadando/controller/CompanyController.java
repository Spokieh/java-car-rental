package com.beadando.beadando.controller;

import com.beadando.beadando.model.Car;
import com.beadando.beadando.model.Company;
import com.beadando.beadando.repository.CarRepository;
import com.beadando.beadando.repository.CompanyRepository;
import com.beadando.beadando.service.CarService;
import com.beadando.beadando.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
@Controller
public class CompanyController {

    private CompanyService companyService;


    private CarService carService;

    public CompanyController(CarService carService, CompanyService companyService) {
        super();
        this.carService = carService;
        this.companyService = companyService;
    }

    @GetMapping("/companies")
    public String showCompanies(Model model) {
        model.addAttribute("companies", companyService.getAllComapny());
        model.addAttribute("company", new Company());
        return "companies";
    }

    @GetMapping("/companies/create")
    @PreAuthorize("hasAuthority('MAINTAINER') or hasAuthority('ADMIN')")
    public String createCompany(Model model) {
        Company newCompany = new Company();
        model.addAttribute("company", newCompany);
        return "companies_create";
    }

    @PostMapping("/companies/create")
    @PreAuthorize("hasAuthority('MAINTAINER') or hasAuthority('ADMIN')")
    public String saveCompany(@ModelAttribute("company") Company company) {
        companyService.saveCompany(company);

        return "redirect:/companies";
    }

    @GetMapping("/companies/edit/{id}")
    @PreAuthorize("hasAuthority('MAINTAINER') or hasAuthority('ADMIN')")
    public String editCompany(@PathVariable Long id, Model model) {
        Company company = companyService.getCompanyById(id);

        if (company == null) {
            return "redirect:/companies";
        }

        model.addAttribute("company", company);
        return "companies_edit";
    }

    @PostMapping("/companies/{id}")
    @PreAuthorize("hasAuthority('MAINTAINER') or hasAuthority('ADMIN')")
    public String updateCompany(@PathVariable Long id,
                            @ModelAttribute("company") Company company,
                            Model model) {

        Company ec = companyService.getCompanyById(id);

        if (ec == null) {
            return "redirect:/companies";
        }

        ec.setId(id);
        ec.setName(company.getName());
        ec.setAddress(company.getAddress());
        ec.setContactInfo(company.getContactInfo());
        //carlist

        companyService.updateCompany(ec);
        return "redirect:/companies";
    }

    @GetMapping("/companies/{id}")
    @PreAuthorize("hasAuthority('MAINTAINER') or hasAuthority('ADMIN')")
    public String deleteCompany(@PathVariable Long id) {
        companyService.deleteCompanyById(id);

        return "redirect:/companies";

    }

}
