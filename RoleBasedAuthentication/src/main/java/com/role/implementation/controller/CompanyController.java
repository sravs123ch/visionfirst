package com.role.implementation.controller;

import com.role.implementation.DTO.CompanyDTO;
import com.role.implementation.model.Company;
import com.role.implementation.model.User;
import com.role.implementation.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/companies")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    // Create a new company using CompanyDTO
    @PostMapping
    public ResponseEntity<Company> createCompany(@RequestBody CompanyDTO companyDTO) {
        // Convert DTO to Company entity
        Company company = new Company();
        company.setCompanyName(companyDTO.getCompanyName());
        company.setCompanyAddress(companyDTO.getCompanyAddress());
        company.setStatus(companyDTO.getStatus());

        Company savedCompany = companyService.saveCompany(company, null);
        return ResponseEntity.created(URI.create("/api/companies/" + savedCompany.getId())).body(savedCompany); // Assuming Company has getId()
    }
    
    @PostMapping("/create")
    public ResponseEntity<Company> createCompany(@RequestBody Company company) {
        User currentUser = getCurrentUser(); // Implement this method to fetch the authenticated user
        return ResponseEntity.ok(companyService.saveCompany(company, currentUser));
    }


    private User getCurrentUser() {
		// TODO Auto-generated method stub
		return null;
	}


	// Get a company by ID
    @GetMapping("/{id}")
    public ResponseEntity<Company> getCompanyById(@PathVariable int id) {
        Optional<Company> company = companyService.getCompanyById(id);
        return company.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Get all companies
    @GetMapping
    public ResponseEntity<List<Company>> getAllCompanies() {
        List<Company> companies = companyService.getAllCompanies();
        return ResponseEntity.ok(companies);
    }

    // Update a company
    @PutMapping("/{id}")
    public ResponseEntity<Company> updateCompany(@PathVariable int id, @RequestBody CompanyDTO companyDetails) {
        Optional<Company> companyOptional = companyService.getCompanyById(id);

        if (companyOptional.isPresent()) {
            Company company = companyOptional.get();
            company.setCompanyName(companyDetails.getCompanyName());
            company.setCompanyAddress(companyDetails.getCompanyAddress());
            company.setStatus(companyDetails.getStatus());

            Company updatedCompany = companyService.saveCompany(company, null);
            return ResponseEntity.ok(updatedCompany);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete a company
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCompany(@PathVariable int id) {
        Optional<Company> companyOptional = companyService.getCompanyById(id);

        if (companyOptional.isPresent()) {
            companyService.deleteCompanyById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
