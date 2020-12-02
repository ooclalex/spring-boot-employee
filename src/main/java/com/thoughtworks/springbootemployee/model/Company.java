package com.thoughtworks.springbootemployee.model;

import com.thoughtworks.springbootemployee.service.EmployeeService;

import java.util.ArrayList;

public class Company {
    private int id;
    private String name;
    private int employeeNumber;
    private EmployeeService employees;

    public Company(int id, String name, int employeeNumber, ArrayList<Employee> employees) {
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

    public EmployeeService getEmployees() {
        return employees;
    }

    public void setEmployees(EmployeeService employees) {
        this.employees = employees;
    }
}
