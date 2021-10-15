package com.hcl.spring.demo.DAO;

import com.hcl.spring.demo.models.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("jpa")
public class JpaProvider implements EmployeeDAO {

    @Autowired
    private JpaRepo jpaRepo;

    @Override
    public List<Employee> getAllEmployees() {
        return jpaRepo.findAll();
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        return jpaRepo.save(employee);
    }

    @Override
    public Employee getEmployeeById(Long id) {
        return jpaRepo.findById(id).orElse(null);
    }

    @Override
    public Employee updateEmployee(Employee employee, Long id) {
        if (jpaRepo.findById(id).orElse(null) != null)
            jpaRepo.save(employee);
        return employee;
    }

    @Override
    public Employee deleteEmployeeById(Long id) {
        Employee old = jpaRepo.findById(id).orElse(null);
        if (old!=null) {
            jpaRepo.delete(old);
        }
        return old;
    }
}
