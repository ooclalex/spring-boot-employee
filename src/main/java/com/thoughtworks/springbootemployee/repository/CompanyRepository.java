package com.thoughtworks.springbootemployee.repository;

import com.thoughtworks.springbootemployee.exception.DuplicatedIdException;
import com.thoughtworks.springbootemployee.exception.NotFoundException;
import com.thoughtworks.springbootemployee.exception.OutOfRangeException;
import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class CompanyRepository {
    private final List<Company> companies = new ArrayList<>();

    public Company add(Company requestCompany) throws DuplicatedIdException {
        if (companies.stream().anyMatch(company -> company.getId() == requestCompany.getId())) {
            throw new DuplicatedIdException();
        }
        this.companies.add(requestCompany);
        return requestCompany;
    }

    public List<Company> getAll() {
        return this.companies;
    }

    public Company getByCompanyId(int companyId) {
        return this.getAll().stream()
                .filter(company -> companyId == company.getId())
                .findFirst()
                .orElseThrow(NotFoundException::new);
    }

    public List<Employee> getEmployeeList(int companyId) {
        return this.getByCompanyId(companyId).getEmployees();
    }

    public List<Company> getAllByPage(int page, int pageSize) throws OutOfRangeException {
        if(page < 1 || pageSize < 0){
            throw new OutOfRangeException();
        }
        return this.companies.stream().skip(pageSize * page - 1).limit(pageSize).collect(Collectors.toList());
    }

    public Company update(int companyId, Company updateCompany) {
        this.remove(companyId);
        this.companies.add(updateCompany);
        return updateCompany;
    }

    public void remove(int companyId) {
        Company company = this.getByCompanyId(companyId);
        this.companies.remove(company);
    }
}
