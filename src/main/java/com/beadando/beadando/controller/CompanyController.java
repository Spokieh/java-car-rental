package com.beadando.beadando.controller;

import com.beadando.beadando.model.Car;
import com.beadando.beadando.model.Company;
import com.beadando.beadando.repository.CarRepository;
import com.beadando.beadando.repository.CompanyRepository;
import com.beadando.beadando.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
@Controller
public class CompanyController {

    private CompanyRepository companyRepository;


    private CarService carService;

    public CompanyController(CarService carService, CompanyRepository companyRepository) {
        super();
        this.carService = carService;
        this.companyRepository = companyRepository;
    }

    @GetMapping("/companies")
    public String showCompanies(Model model) {
        model.addAttribute("companies", companyRepository.findAll());
        model.addAttribute("company", new Company());
        return "companies";
    }

    @GetMapping("/companies/create")
    public String createCompany(Model model) {
        Company newCompany = new Company();
        model.addAttribute("company", newCompany);
        return "companies_create";
    }

    @PostMapping("/companies/create")
    public String saveCompany(@ModelAttribute("company") Company company) {
        companyRepository.save(company);

        return "redirect:/companies";
    }
//
//    @GetMapping("/companies/edit/{id}")
//    public String editCompany(@PathVariable Long id, Model model) {
//        Company company = companyRepository.findById(id).get();
//
//        if (company == null) {
//            return "redirect:/companies";
//        }
//
//        model.addAttribute("companies", company);
//        return "companies_edit";
//    }
//
//    @PostMapping("/companies/{id}")
//    public String updateCompany(@PathVariable Long id,
//                            @ModelAttribute("company") Company company,
//                            Model model) {
//
//        Company ec = companyRepository.findById(id).get();
//
//        if (ec == null) {
//            return "redirect:/companies";
//        }
//
//        ec.setId(id);
//        ec.setName(company.getName());
//        ec.setAddress(company.getAddress());
//        ec.setContactInfo(company.getContactInfo());
//        //carlist
//
//        companyRepository.save(ec);
//        return "redirect:/companies";
//    }
//
//    @GetMapping("/cars/{id}")
//    public String deleteComapny(@PathVariable Long id) {
//        companyRepository.deleteById(id);
//
//        return "redirect:/companies";
//
//    }

}
