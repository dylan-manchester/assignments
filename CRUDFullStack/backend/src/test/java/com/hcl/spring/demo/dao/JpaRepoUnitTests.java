package com.hcl.spring.demo.dao;

import com.hcl.spring.demo.models.Employee;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
class JpaRepoUnitTests {

    @Autowired
    JpaRepo jpaRepo;

    @Test
    void getAllReturnsSaved(){
        assert(jpaRepo.findAll().size()==0);
        Employee e = new Employee();
        e.setFirstName("First");
        e.setLastName("Last");
        e.setEmail("first.last@gmail.com");
        jpaRepo.save(e);
        List<Employee> result = jpaRepo.findAll();
        assert(result.size()==1);
        assert(result.contains(e));
    }

    @Test
    void saveSetsUniqueID(){
        Employee e = new Employee();
        e.setFirstName("First");
        e.setLastName("Last");
        e.setEmail("first.last@gmail.com");
        jpaRepo.save(e);
        Assertions.assertNotNull(e.getId());
        Employee e1 = new Employee();
        e.setFirstName("First");
        e.setLastName("Last");
        e.setEmail("first.1.last@gmail.com");
        jpaRepo.save(e1);
        Assertions.assertNotEquals(e.getId(),e1.getId());
    }

}
