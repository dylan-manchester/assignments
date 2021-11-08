package com.hcl.spring.demo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hcl.spring.demo.exception.ResourceNotFoundException;
import com.hcl.spring.demo.models.Employee;
import com.hcl.spring.demo.services.EmployeeService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class EmployeeControllerUnitTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    EmployeeService employeeService;

    private Employee employee;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setup() {
        this.objectMapper = new ObjectMapper();
        this.employee = new Employee();
        this.employee.setId(1L);
        this.employee.setFirstName("Sam");
        this.employee.setLastName("Emp");
        this.employee.setEmail("sam.emp@gmail.com");
    }

    @AfterEach
    void teardown() {
        this.objectMapper = null;
        this.employee = null;
    }

    @Test
    void getBase() throws Exception {
        when(employeeService.getAllEmployees()).thenReturn(new ArrayList<Employee>() {});
        MvcResult result = this.mockMvc.perform(MockMvcRequestBuilders.get("/employee/").accept(MediaType.ALL))
                .andExpect(status().isOk())
                .andReturn();
        Assertions.assertEquals(result.getResponse().getContentAsString(),"[]");
    }

    @Test
    void postBase() throws Exception {
        when(employeeService.saveEmployee(employee)).thenReturn(employee);
        MvcResult result = this.mockMvc.perform(MockMvcRequestBuilders
                .post("/employee/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(employee))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        Assertions.assertEquals(objectMapper.readValue(result.getResponse().getContentAsByteArray(), Employee.class),employee);
    }

    @Test
    void putId() throws Exception {
        when(employeeService.updateEmployee(employee,employee.getId())).thenReturn(employee);
        MvcResult result = this.mockMvc.perform(MockMvcRequestBuilders
                .put("/employee/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(employee))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        Assertions.assertEquals(objectMapper.readValue(result.getResponse().getContentAsByteArray(), Employee.class),employee);
    }

    @Test
    void deleteId() throws Exception {
        when(employeeService.deleteEmployeeById(1L)).thenReturn(employee);
        MvcResult result = this.mockMvc.perform(MockMvcRequestBuilders
                .delete("/employee/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        Assertions.assertEquals(objectMapper.readValue(result.getResponse().getContentAsByteArray(), Employee.class),employee);
    }

    @Test
    void getId() throws Exception {
        when(employeeService.getEmployeeById(1L)).thenReturn(employee);
        MvcResult result = this.mockMvc.perform(MockMvcRequestBuilders
                .get("/employee/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        Assertions.assertEquals(objectMapper.readValue(result.getResponse().getContentAsByteArray(), Employee.class),employee);
    }

    @Test
    void getIdNotFound() throws Exception {
        Long id = 8L;
        when(employeeService.getEmployeeById(id)).thenThrow(new ResourceNotFoundException("Employee does not exist with id : " + id));
        MvcResult result = this.mockMvc.perform(MockMvcRequestBuilders
                .get("/employee/8")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andReturn();
    }
}
