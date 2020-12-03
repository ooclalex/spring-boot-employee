package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.exception.DuplicatedIdException;
import com.thoughtworks.springbootemployee.exception.OutOfRangeException;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.service.EmployeeService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    private EmployeeService employeeService = new EmployeeService();

    @GetMapping
    public List<Employee> getAll() {
        return employeeService.getAll();
    }

    @GetMapping(params = {"page", "pageSize"})
    public Page<Employee> getAllByPaging(
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
    public Employee getSpecificEmployee(@PathVariable String employeeId) {
        return employeeService.get(employeeId);
    }


    @PostMapping
    public Employee create(@RequestBody Employee employeeUpdate) throws DuplicatedIdException {
        return employeeService.add(employeeUpdate);
    }

    @PutMapping("/{employeeId}")
    public Employee update(@PathVariable String employeeId, @RequestBody Employee employeeUpdate) {
        return employeeService.update(employeeId, employeeUpdate);
    }

    @DeleteMapping("/{employeeId}")
    public void delete(@PathVariable String employeeId) {
        employeeService.remove(employeeId);
    }
}
