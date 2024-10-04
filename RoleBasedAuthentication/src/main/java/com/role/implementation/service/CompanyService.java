package com.role.implementation.service;

import com.role.implementation.model.Company;
import com.role.implementation.model.User;
import com.role.implementation.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    // Create or Update a company
//    public Company saveCompany(Company company) {
//        return companyRepository.save(company);
//    }
    public Company saveCompany(Company company, User user) {
        company.setCreatedBy(user); // Ensure the user is not null
        return companyRepository.save(company);
    }


    // Get a company by ID
    public Optional<Company> getCompanyById(int id) {
        return companyRepository.findById(id);
    }

    // Get all companies
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    // Delete a company by ID
    public void deleteCompanyById(int id) {
        companyRepository.deleteById(id);
    }


	public Company saveCompany(Company company, Object user) {
		// TODO Auto-generated method stub
		return null;
	}
}

