package com.hcl.spring.demo.services;

import com.hcl.spring.demo.DAO.EmployeeDAO;
import com.hcl.spring.demo.models.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    @Qualifier("jpa")
    private EmployeeDAO employeeDAO;

    public List<Employee> getAllEmployees() {
        return employeeDAO.getAllEmployees();
    }

    public Employee saveEmployee(Employee employee) {
        return employeeDAO.saveEmployee(employee);
    }

    public Employee getEmployeeById(Long id) {
        return employeeDAO.getEmployeeById(id);
    }

    public Employee updateEmployee(Employee employee, Long id) {
        if (employeeDAO.getEmployeeById(id) != null)
            employeeDAO.saveEmployee(employee);
        return employee;
    }

    public Employee deleteEmployeeById(Long id) {
        return employeeDAO.deleteEmployeeById(id);
    }
}
