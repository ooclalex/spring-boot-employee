package com.thoughtworks.springbootemployee.repository;

import com.thoughtworks.springbootemployee.exception.DuplicatedIdException;
import com.thoughtworks.springbootemployee.model.Employee;

import java.util.ArrayList;
import java.util.List;

public class EmployeeRepository {
    private List<Employee> employees = new ArrayList<>();
    public Employee add(Employee employee) throws DuplicatedIdException {
        if(employees.stream().anyMatch(item -> item.getId() == employee.getId())){
            throw new DuplicatedIdException();
        };
        this.employees.add(employee);
        return employee;
    }
}
