package com.thoughtworks.springbootemployee;


import com.thoughtworks.springbootemployee.exception.DuplicatedIdException;
import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import com.thoughtworks.springbootemployee.service.CompanyService;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CompanyTests {
    @Test
    void should_return_company_when_add_company_given_no_employees() throws DuplicatedIdException {
        //given
        CompanyRepository companyRepository = new CompanyRepository();
        CompanyService companyService = new CompanyService(companyRepository);
        Company company = new Company(1, "My Company", 1000, new ArrayList<Employee>());

        //when
        final Company actual = companyService.add(company);

        //then
        assertEquals(company, actual);
    }
}
