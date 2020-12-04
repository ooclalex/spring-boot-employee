package com.thoughtworks.springbootemployee.dto;

import java.util.List;

public class CompanyResponse {
    private String id;
    private String name;
    private Integer employeeNumber;
    private List<String> employees;

    public CompanyResponse() {

    }

    public CompanyResponse(String id, String name, Integer employeeNumber, List<String> employees) {
        this.id = id;
        this.name = name;
        this.employeeNumber = employeeNumber;
        this.employees = employees;
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
