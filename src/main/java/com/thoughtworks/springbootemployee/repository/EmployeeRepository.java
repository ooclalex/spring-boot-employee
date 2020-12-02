package com.thoughtworks.springbootemployee.repository;

import com.thoughtworks.springbootemployee.EmployeeNotFoundException;
import com.thoughtworks.springbootemployee.exception.DuplicatedIdException;
import com.thoughtworks.springbootemployee.exception.NotFoundException;
import com.thoughtworks.springbootemployee.model.Employee;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class EmployeeRepository {
    private final List<Employee> employees;
    public EmployeeRepository(){
        this.employees = new ArrayList<>();
    }
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

    public Employee get(int employeeId) {
        return this.getAll().stream()
                .filter(employee -> employeeId == employee.getId())
                .findFirst()
                .orElseThrow(NotFoundException::new);
    }
}
