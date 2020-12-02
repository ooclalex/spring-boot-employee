package com.thoughtworks.springbootemployee;

import com.thoughtworks.springbootemployee.exception.DuplicatedIdException;
import com.thoughtworks.springbootemployee.exception.NotFoundException;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import com.thoughtworks.springbootemployee.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
		final List<Employee> expected = Collections.singletonList(employee);
		employeeService.add(employee);
		//when
		final List<Employee> actual = employeeService.getAll();

		//then
		assertEquals(expected, actual);

	}
	@Test
	void should_return_exception_employees_when_get_employee_given_invalid_employee_id() {
		//given
		EmployeeRepository employeeRepository = new EmployeeRepository();
		EmployeeService employeeService = new EmployeeService(employeeRepository);

		//when
		final NotFoundException notFoundException = assertThrows(NotFoundException.class,
				() -> employeeService.get(1)
		);
		//then
		assertEquals("Not Found", notFoundException.getMessage());

	}

	@Test
	void should_return_specific_employees_when_get_employee_given_employees_employee_id() throws DuplicatedIdException {
		//given
		EmployeeRepository employeeRepository = new EmployeeRepository();
		EmployeeService employeeService = new EmployeeService(employeeRepository);
		Employee employee = new Employee(1, "test", 18, 1000, "male");
		employeeService.add(employee);

		//when
		final Employee actual = employeeService.get(1);

		//then
		assertEquals(employee, actual);

	}

	@Test
	void should_return_exception_when_update_employee_given_invalid_employee_id() {
		//given
		EmployeeRepository employeeRepository = new EmployeeRepository();
		EmployeeService employeeService = new EmployeeService(employeeRepository);

		//when
		final NotFoundException notFoundException = assertThrows(NotFoundException.class,
				() -> employeeService.update(1, new Employee())
		);
		//then
		assertEquals("Not Found", notFoundException.getMessage());

	}
}
