package com.thoughtworks.springbootemployee.dto;

import java.util.List;

public class CompanyRequest {
    private String name;
    private Integer employeeNumber;
    private List<String> employees;

    public CompanyRequest() {

    }

    public CompanyRequest(String name, Integer employeeNumber, List<String> employees) {
        this.name = name;
        this.employeeNumber = employeeNumber;
        this.employees = employees;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(Integer employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public List<String> getEmployees() {
        return employees;
    }

    public void setEmployees(List<String> employees) {
        this.employees = employees;
    }
}
