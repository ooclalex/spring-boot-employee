package com.thoughtworks.springbootemployee.model;

import java.util.List;

public class Company {
    private Integer id;
    private String name;
    private Integer employeeNumber;
    private List<Employee> employees;

    public Company(int id, String name, int employeeNumber, List<Employee> employees) {
        this.id = id;
        this.name = name;
        this.employeeNumber = employeeNumber;
        this.employees = employees;
    }

    public Company() {

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

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}
