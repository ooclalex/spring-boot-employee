package com.thoughtworks.springbootemployee.integration;

import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeIntegrationTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    private EmployeeRepository employeeRepository;

    @AfterEach
    void tearDown(){
        employeeRepository.deleteAll();
    }

    @Test
    public void should_return_all_employees_when_get_all_given_employees() throws Exception {
        //given
        Employee employee = new Employee("Victor", 18, 1000, "male");
        employeeRepository.save(employee);
        //when
        mockMvc.perform(MockMvcRequestBuilders.get("/employees"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").isString())
                .andExpect(jsonPath("$[0].name").value("Victor"))
                .andExpect(jsonPath("$[0].age").value(18))
                .andExpect(jsonPath("$[0].salary").value(1000))
                .andExpect(jsonPath("$[0].gender").value("male"));
        //then
    }

    @Test
    public void should_return_employee_when_add_employee_given_employee() throws Exception {
        //given
        String employeeAsJson = "{\n" +
                "    \"name\" : \"Victor\",\n" +
                "    \"age\"   : \"18\",\n" +
                "    \"salary\" : \"1000\",\n" +
                "    \"gender\" : \"male\"\n" +
                "}";
        //when
        //then
        mockMvc.perform(MockMvcRequestBuilders.post("/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .content(employeeAsJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("id").isString())
                .andExpect(jsonPath("name").value("Victor"))
                .andExpect(jsonPath("age").value(18))
                .andExpect(jsonPath("salary").value(1000))
                .andExpect(jsonPath("gender").value("male"));
        List<Employee> employeeList = employeeRepository.findAll();
        assertEquals(1, employeeList.size());
        assertEquals("Victor", employeeList.get(0).getName());
        assertEquals(18, employeeList.get(0).getAge());
        assertEquals(1000, employeeList.get(0).getSalary());
        assertEquals("male", employeeList.get(0).getGender());
    }

    @Test
    public void should_return_specific_employee_when_get_employee_given_employee_id() throws Exception {
        //given
        Employee employee = employeeRepository.save(new Employee("Victor", 18, 1000, "male"));
        //when
        //then
        mockMvc.perform(MockMvcRequestBuilders.get("/employees/"+employee.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").isString())
                .andExpect(jsonPath("name").value("Victor"))
                .andExpect(jsonPath("age").value(18))
                .andExpect(jsonPath("salary").value(1000))
                .andExpect(jsonPath("gender").value("male"));
    }

    @Test
    public void should_return_all_male_employees_when_get_all_employee_by_gender_given_employees_male() throws Exception {
        //given
        Employee employee = employeeRepository.save(new Employee("Victor", 18, 1000, "male"));
        employeeRepository.save(new Employee("Victor", 18, 1000, "female"));
        //when
        //then
        mockMvc.perform(MockMvcRequestBuilders.get("/employees").param("gender", "male"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*", hasSize(1)))
                .andExpect(jsonPath("$[0].id").isString())
                .andExpect(jsonPath("$[0].name").value("Victor"))
                .andExpect(jsonPath("$[0].age").value(18))
                .andExpect(jsonPath("$[0].salary").value(1000))
                .andExpect(jsonPath("$[0].gender").value("male"));
    }
}
