package com.thoughtworks.springbootemployee.model;

import java.util.List;

public class Company {
    private String id;
    private String name;
    private Integer employeeNumber;
    private List<Employee> employees;

    public Company(String name, int employeeNumber, List<Employee> employees) {
        this.name = name;
        this.employeeNumber = employeeNumber;
        this.employees = employees;
    }

    public Company() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}
