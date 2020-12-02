package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.exception.DuplicatedIdException;
import com.thoughtworks.springbootemployee.exception.OutOfRangeException;
import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;

import java.util.ArrayList;
import java.util.List;

public class CompanyService {
    private CompanyRepository companyRepository;
    public CompanyService(){
        this.companyRepository = new CompanyRepository();
    }
    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public Company add(Company requestCompany) throws DuplicatedIdException {
        return this.companyRepository.add(requestCompany);
    }

    public List<Company> getAll() {
        return companyRepository.getAll();
    }

    public Company get(int companyId) {
        return companyRepository.get(companyId);
    }

    public List<Employee> getEmployeeList(int companyId) {
        return companyRepository.getEmployeeList(companyId);
    }

    public List<Company> getAllByPage(int page, int pageSize) throws OutOfRangeException {
        return companyRepository.getAllByPage(page, pageSize);
    }

    public Company update(int companyId, Company updateCompany) {
        return companyRepository.update(companyId, updateCompany);
    }

    public void remove(int companyId) {
        companyRepository.remove(companyId);
    }
}
