package com.thoughtworks.springbootemployee;


import com.thoughtworks.springbootemployee.exception.DuplicatedIdException;
import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import com.thoughtworks.springbootemployee.service.CompanyService;
import com.thoughtworks.springbootemployee.service.EmployeeService;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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

    @Test
    void should_return_error_when_add_company_given_company_with_same_id() throws DuplicatedIdException {
        //given
        CompanyRepository companyRepository = new CompanyRepository();
        CompanyService companyService = new CompanyService(companyRepository);
        Company company = new Company(1, "My Company", 1000, new ArrayList<Employee>());
        companyService.add(company);
        //when
        final DuplicatedIdException duplicatedIdException = assertThrows(DuplicatedIdException.class,
                () -> companyService.add(company)
        );
        //then
        assertEquals("Duplicated ID", duplicatedIdException.getMessage());

    }
}
