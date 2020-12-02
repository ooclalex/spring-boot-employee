package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.exception.DuplicatedIdException;
import com.thoughtworks.springbootemployee.exception.NotFoundException;
import com.thoughtworks.springbootemployee.exception.OutOfRangeException;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    private EmployeeService employeeService = new EmployeeService();

    @GetMapping
    public List<Employee> getAll() {
        return employeeService.getAll();
    }

    @GetMapping(params = {"page", "pageSize"})
    public List<Employee> getAllByPaging(
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer pageSize
    ) throws OutOfRangeException {
        return employeeService.getAllByPage(page, pageSize);
    }

    @GetMapping(params = {"gender"})
    public List<Employee> getAllByGender(@RequestParam(required = false) String gender) {
        return employeeService.getAllByGender(gender);
    }

    @GetMapping("/{employeeId}")
    public Employee getSpecificEmployee(@PathVariable Integer employeeId) {
        return employeeService.get(employeeId);
    }


    @PostMapping
    public Employee create(@RequestBody Employee employeeUpdate) throws DuplicatedIdException {
        return employeeService.add(employeeUpdate);
    }

    @PutMapping("/{employeeId}")
    public Employee update(@PathVariable Integer employeeId, @RequestBody Employee employeeUpdate) {
        return employeeService.update(employeeId, employeeUpdate);
    }

    @DeleteMapping("/{employeeId}")
    public void delete(@PathVariable Integer employeeId) {
        employeeService.remove(employeeId);
    }
}
