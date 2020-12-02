package com.thoughtworks.springbootemployee;


import com.thoughtworks.springbootemployee.exception.DuplicatedIdException;
import com.thoughtworks.springbootemployee.exception.NotFoundException;
import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import com.thoughtworks.springbootemployee.service.CompanyService;
import com.thoughtworks.springbootemployee.service.EmployeeService;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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


    @Test
    void should_return_all_companies_when_get_all_companies_given_companies() throws DuplicatedIdException {
        //given
        CompanyRepository companyRepository = new CompanyRepository();
        CompanyService companyService = new CompanyService(companyRepository);
        Company company = new Company(1, "My Company", 1000, new ArrayList<Employee>());
        final List<Company> expected = Collections.singletonList(company);
        companyService.add(company);
        //when
        final List<Company> actual = companyService.getAll();

        //then
        assertEquals(expected, actual);

    }


    @Test
    void should_return_specific_company_when_get_company_given_compnaies_company_id() throws DuplicatedIdException {
        //given
        CompanyRepository companyRepository = new CompanyRepository();
        CompanyService companyService = new CompanyService(companyRepository);
        Company company = new Company(1, "My Company", 1000, new ArrayList<Employee>());
        companyService.add(company);

        //when
        final Company actual = companyService.get(1);

        //then
        assertEquals(company, actual);

    }

    @Test
    void should_return_exception_employees_when_get_employee_given_invalid_employee_id() {
        //given
        CompanyRepository companyRepository = new CompanyRepository();
        CompanyService companyService = new CompanyService(companyRepository);

        //when
        final NotFoundException notFoundException = assertThrows(NotFoundException.class,
                () -> companyService.get(1)
        );
        //then
        assertEquals("Not Found", notFoundException.getMessage());

    }

    @Test
    void should_return_specific_company_employee_list_when_get_company_employee_list_given_compnaies_company_id() throws DuplicatedIdException {
        //given
        CompanyRepository companyRepository = new CompanyRepository();
        CompanyService companyService = new CompanyService(companyRepository);
        List<Employee> expected = new ArrayList<Employee>();
        expected.add(new Employee());

        companyService.add(new Company(1, "My Company", 1000, expected));

        //when
        final List<Employee> actual = companyService.getEmployeeList(1);

        //then
        assertEquals(expected, actual);
    }

    @Test
    void should_return_error_when_get_company_employee_list_given_compnaies_invalid_company_id() throws DuplicatedIdException {
        //given
        CompanyRepository companyRepository = new CompanyRepository();
        CompanyService companyService = new CompanyService(companyRepository);

        //when
        final NotFoundException notFoundException = assertThrows(NotFoundException.class,
                () -> companyService.getEmployeeList(1)
        );
        //then
        assertEquals("Not Found", notFoundException.getMessage());
    }
}
