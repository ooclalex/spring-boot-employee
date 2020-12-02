package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.exception.DuplicatedIdException;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;

import java.util.List;

public class EmployeeService {
    EmployeeRepository employeeRepository;
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee add(Employee requestEmployee) throws DuplicatedIdException {
        return employeeRepository.add(requestEmployee);
    }

    public List<Employee> getAll() {
        return employeeRepository.getAll();
    }

    public Employee get(int employeeId) {
        return null;
    }
}
