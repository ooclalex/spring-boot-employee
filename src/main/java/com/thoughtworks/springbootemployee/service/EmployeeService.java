package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.exception.DuplicatedIdException;
import com.thoughtworks.springbootemployee.exception.OutOfRangeException;
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
        return employeeRepository.get(employeeId);
    }

    public Employee update(int employeeId, Employee updateEmployee) {
        return employeeRepository.update(employeeId, updateEmployee);
    }

    public void remove(int employeeId) {
        employeeRepository.remove(employeeId);
    }

    public List<Employee> getAllByGender(String gender) {
        return employeeRepository.getAllByGender(gender);
    }

    public List<Employee> getAllByPage(int page, int pageSize) throws OutOfRangeException {
        return employeeRepository.getAllByPage(page, pageSize);
    }
}
