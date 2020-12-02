package com.thoughtworks.springbootemployee;

import com.thoughtworks.springbootemployee.exception.DuplicatedIdException;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import com.thoughtworks.springbootemployee.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SpringBootEmployeeApplicationTests {

	@Test
	void should_return_employees_when_add_employee_given_no_employees() throws DuplicatedIdException {
		//given
		EmployeeRepository employeeRepository = new EmployeeRepository();
		EmployeeService employeeService = new EmployeeService(employeeRepository);
		Employee employee = new Employee(1, "test", 18, 1000, "male");

		//when
		final Employee actual = employeeService.add(employee);

		//then
		assertEquals(employee, actual);
	}

	@Test
	void should_return_error_when_add_employee_given_employee_with_same_id() throws DuplicatedIdException {
		//given
		EmployeeRepository employeeRepository = new EmployeeRepository();
		EmployeeService employeeService = new EmployeeService(employeeRepository);
		Employee employee = new Employee(1, "test", 18, 1000, "male");
		employeeService.add(employee);
		//when
		final DuplicatedIdException duplicatedIdException = assertThrows(DuplicatedIdException.class,
				() -> employeeService.add(employee)
		);
		//then
		assertEquals("Duplicated ID", duplicatedIdException.getMessage());

	}

	@Test
	void should_return_all_employees_when_get_all_employee_given_employees() throws DuplicatedIdException {
		//given
		EmployeeRepository employeeRepository = new EmployeeRepository();
		EmployeeService employeeService = new EmployeeService(employeeRepository);
		Employee employee = new Employee(1, "test", 18, 1000, "male");
		final List<Employee> expected = Arrays.asList(employee);
		employeeService.add(employee);
		//when
		final List<Employee> actual = employeeService.getAll();

		//then
		assertEquals(expected, actual);

	}
}
