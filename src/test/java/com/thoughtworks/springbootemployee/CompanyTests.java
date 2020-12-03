package com.thoughtworks.springbootemployee;


import com.thoughtworks.springbootemployee.exception.DuplicatedIdException;
import com.thoughtworks.springbootemployee.exception.NotFoundException;
import com.thoughtworks.springbootemployee.exception.OutOfRangeException;
import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import com.thoughtworks.springbootemployee.service.CompanyService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CompanyTests {

    @InjectMocks
    private CompanyService companyService;
    @Mock
    private CompanyRepository companyRepository;

    @Test
    void should_return_company_when_add_company_given_no_company() throws DuplicatedIdException {
        //given
        Company company = new Company(1, "My Company", 1000, new ArrayList<>());
        when(companyRepository.add(company)).thenReturn(company);

        //when
        final Company actual = companyService.add(company);

        //then
        assertEquals(company, actual);
    }


    @Test
    void should_return_all_companies_when_get_all_companies_given_companies() throws DuplicatedIdException {
        //given
        Company company = new Company(1, "My Company", 1000, new ArrayList<>());
        final List<Company> expected = Collections.singletonList(company);
        companyService.add(company);

        when(companyRepository.getAll()).thenReturn(expected);
        //when
        final List<Company> actual = companyService.getAll();

        //then
        assertEquals(expected, actual);

    }
    @Test
    void should_return_specific_company_employee_list_when_get_company_employee_list_given_companies_company_id() throws DuplicatedIdException {
        //given
        List<Employee> expected = new ArrayList<>();
        expected.add(new Employee());
        when(companyRepository.getEmployeeList(1)).thenReturn(expected);

        companyService.add(new Company(1, "My Company", 1000, expected));

        //when
        final List<Employee> actual = companyService.getEmployeeList(1);

        //then
        assertEquals(expected, actual);
    }

    @Test
    void should_return_first_2_companies_when_get_companies_by_page_given_companies_page1_pageSize2() throws DuplicatedIdException, OutOfRangeException {
        //given

        Company company1 = new Company(1, "My Company1", 1000, new ArrayList<>());
        Company company2 = new Company(2, "My Company2", 1000, new ArrayList<>());

        companyService.add(company1);
        companyService.add(company2);
        companyService.add(new Company(3, "My Company3", 1000, new ArrayList<>()));

        final List<Company> expected = Arrays.asList(company1, company2);

        when(companyRepository.getAllByPage(1, 2)).thenReturn(expected);
        //when
        final List<Company> actual = companyService.getAllByPage(1, 2);

        //then
        assertEquals(expected, actual);
    }

    @Test
    void should_return_updated_employees_when_get_employee_given_employees_employee_id() throws DuplicatedIdException {
        //given
        companyService.add(new Company(1, "My Company1", 1000, new ArrayList<>()));

        Company company = new Company(1, "My New Company1", 10000, new ArrayList<>());
        when(companyRepository.update(1, company)).thenReturn(company);

        //when
        final Company actual = companyService.update(1, company);

        //then
        assertEquals(company, actual);
    }
}
