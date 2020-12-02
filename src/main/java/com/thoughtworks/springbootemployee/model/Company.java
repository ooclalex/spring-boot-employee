package com.thoughtworks.springbootemployee.model;

import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import com.thoughtworks.springbootemployee.service.EmployeeService;

import java.util.ArrayList;
import java.util.List;

public class Company {
    private int id;
    private String name;
    private int employeeNumber;
    private EmployeeService employeeService;

    public Company(int id, String name, int employeeNumber, List<Employee>  employees) {
        this.id = id;
        this.name = name;
        this.employeeNumber = employeeNumber;
        this.setEmployeeService(employees);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(int employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public EmployeeService getEmployeeService() {
        return employeeService;
    }

    public void setEmployeeService(List<Employee> employees) {
        this.employeeService = new EmployeeService(new EmployeeRepository(employees)) ;
    }

    public List<Employee> getEmployeeList() {
        return this.employeeService.getAll();
    }
}
