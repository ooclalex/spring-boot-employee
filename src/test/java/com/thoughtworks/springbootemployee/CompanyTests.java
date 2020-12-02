package com.thoughtworks.springbootemployee;


import com.thoughtworks.springbootemployee.exception.DuplicatedIdException;
import com.thoughtworks.springbootemployee.exception.NotFoundException;
import com.thoughtworks.springbootemployee.exception.OutOfRangeException;
import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import com.thoughtworks.springbootemployee.service.CompanyService;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
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
        Company company = new Company(1, "My Company", 1000, new ArrayList<>());

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
        Company company = new Company(1, "My Company", 1000, new ArrayList<>());
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
        Company company = new Company(1, "My Company", 1000, new ArrayList<>());
        final List<Company> expected = Collections.singletonList(company);
        companyService.add(company);
        //when
        final List<Company> actual = companyService.getAll();

        //then
        assertEquals(expected, actual);

    }


    @Test
    void should_return_specific_company_when_get_company_given_companies_company_id() throws DuplicatedIdException {
        //given
        CompanyRepository companyRepository = new CompanyRepository();
        CompanyService companyService = new CompanyService(companyRepository);
        Company company = new Company(1, "My Company", 1000, new ArrayList<>());
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
    void should_return_specific_company_employee_list_when_get_company_employee_list_given_companies_company_id() throws DuplicatedIdException {
        //given
        CompanyRepository companyRepository = new CompanyRepository();
        CompanyService companyService = new CompanyService(companyRepository);
        List<Employee> expected = new ArrayList<>();
        expected.add(new Employee());

        companyService.add(new Company(1, "My Company", 1000, expected));

        //when
        final List<Employee> actual = companyService.getEmployeeList(1);

        //then
        assertEquals(expected, actual);
    }

    @Test
    void should_return_error_when_get_company_employee_list_given_companies_invalid_company_id() {
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

    @Test
    void should_return_first_2_companies_when_get_companies_by_page_given_companies_page1_pageSize2() throws DuplicatedIdException, OutOfRangeException {
        //given
        CompanyRepository companyRepository = new CompanyRepository();
        CompanyService companyService = new CompanyService(companyRepository);

        Company company1 = new Company(1, "My Company1", 1000, new ArrayList<>());
        Company company2 = new Company(2, "My Company2", 1000, new ArrayList<>());

        companyService.add(company1);
        companyService.add(company2);
        companyService.add(new Company(3, "My Company3", 1000, new ArrayList<>()));

        final List<Company> expected = Arrays.asList(company1, company2);
        //when
        final List<Company> actual = companyService.getAllByPage(1, 2);

        //then
        assertEquals(expected, actual);
    }

    @Test
    void should_return_exception_when_get_companies_by_page_given_companies_invalid_page() {
        //given
        CompanyRepository companyRepository = new CompanyRepository();
        CompanyService companyService = new CompanyService(companyRepository);

        //when
        final OutOfRangeException outOfRangeException = assertThrows(OutOfRangeException.class,
                () -> companyService.getAllByPage(-1,2)
        );
        //then
        assertEquals("Out of range", outOfRangeException.getMessage());

    }

    @Test
    void should_return_exception_when_get_companies_by_page_given_companies_invalid_page_size(){
        //given
        CompanyRepository companyRepository = new CompanyRepository();
        CompanyService companyService = new CompanyService(companyRepository);

        //when
        final OutOfRangeException outOfRangeException = assertThrows(OutOfRangeException.class,
                () -> companyService.getAllByPage(2,-1)
        );
        //then
        assertEquals("Out of range", outOfRangeException.getMessage());

    }

    @Test
    void should_return_updated_employees_when_get_employee_given_employees_employee_id() throws DuplicatedIdException {
        //given
        CompanyRepository companyRepository = new CompanyRepository();
        CompanyService companyService = new CompanyService(companyRepository);
        companyService.add(new Company(1, "My Company1", 1000, new ArrayList<>()));

        Company company = new Company(1, "My New Company1", 10000, new ArrayList<>());

        //when
        final Company updateActual = companyService.update(1, company);
        final Company getUpdatedActual = companyService.get(1);


        //then
        assertEquals(company, updateActual);
        assertEquals(company, getUpdatedActual);

    }

    @Test
    void should_return_exception_when_update_company_given_invalid_company_id() {
        //given
        CompanyRepository companyRepository = new CompanyRepository();
        CompanyService companyService = new CompanyService(companyRepository);

        //when
        final NotFoundException notFoundException = assertThrows(NotFoundException.class,
                () -> companyService.update(1, new Company())
        );
        //then
        assertEquals("Not Found", notFoundException.getMessage());

    }

    @Test
    void should_return_exception_when_delete_employee_and_get_given_employee() throws DuplicatedIdException {
        //given
        CompanyRepository companyRepository = new CompanyRepository();
        CompanyService companyService = new CompanyService(companyRepository);

        companyService.add(new Company(1, "My New Company1", 10000, new ArrayList<>()));

        companyService.remove(1);

        //when
        final NotFoundException notFoundException = assertThrows(NotFoundException.class,
                () -> companyService.get(1)
        );
        //then
        assertEquals("Not Found", notFoundException.getMessage());

    }
}
