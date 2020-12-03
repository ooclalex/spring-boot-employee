package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.exception.DuplicatedIdException;
import com.thoughtworks.springbootemployee.exception.OutOfRangeException;
import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.service.CompanyService;
import com.thoughtworks.springbootemployee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {
    @Autowired
    private CompanyService companyService;

    @GetMapping
    public List<Company> getAll() {
        return companyService.getAll();
    }

    @GetMapping("/{companyId}")
    public Company getSpecificCompany(@PathVariable Integer companyId) {
        return companyService.get(companyId);
    }

    @GetMapping("/{companyId}/employees")
    public List<Employee> getSpecificCompanyEmployees(@PathVariable Integer companyId) {
        return companyService.getEmployeeList(companyId);
    }

    @GetMapping(params = {"page", "pageSize"})
    public List<Company> getAllByPaging(
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer pageSize
    ) throws OutOfRangeException {
        return companyService.getAllByPage(page, pageSize);
    }

    @PostMapping
    public Company create(@RequestBody Company companyUpdate) throws DuplicatedIdException {
        return companyService.add(companyUpdate);
    }

    @PutMapping("/{employeeId}")
    public Company update(@PathVariable Integer companyId, @RequestBody Company companyUpdate) {
        return companyService.update(companyId, companyUpdate);
    }

    @DeleteMapping("/{employeeId}")
    public void delete(@PathVariable Integer companyId) {
        companyService.remove(companyId);
    }
}
