package com.thoughtworks.springbootemployee.integration;

import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeIntegrationTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    private EmployeeRepository employeeRepository;
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
    }
}
