package com.thoughtworks.springbootemployee.repository;

import com.thoughtworks.springbootemployee.exception.DuplicatedIdException;
import com.thoughtworks.springbootemployee.model.Employee;

import java.util.ArrayList;
import java.util.List;

public class EmployeeRepository {
    private List<Employee> employees = new ArrayList<>();
    public Employee add(Employee requestEmployee) throws DuplicatedIdException {
        if(employees.stream().anyMatch(employee -> employee.getId() == requestEmployee.getId())){
            throw new DuplicatedIdException();
        };
        this.employees.add(requestEmployee);
        return requestEmployee;
    }

    public List<Employee> getAll() {
        return employees;
    }
}
