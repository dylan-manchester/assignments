package com.hcl.spring.demo.models;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data // Provides Getters, Setters, ToString, EqualsAndHashCode, and RequiredArgsConstructor,
@Entity // Required for JPA
public class Employee {

    @Id // Required for JPA
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Automatically sets id value when saved to DB
    private long id;
    private String firstName;
    private String lastName;
    private String email;
}