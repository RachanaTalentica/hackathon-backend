package com.hackethon.employee.self.care.dao;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private Gender gender;

    @Column(name = "years_of_experience")
    private int yearsOfExperience;

    @Column(name = "current_designation")
    private String currentDesignation;

    @ElementCollection
    @CollectionTable(name = "technology_list", joinColumns = @JoinColumn( name = "employee_id"))
    @Column(name = "tools_technology_database_framework")
    private List<String> toolsTechnologyDatabaseFramework = new ArrayList<String>();

    @Column(name = "interest_area")
    private String interestArea;


    public Employee() {
    }

    public Employee(String name, String email, Gender gender, int yearsOfExperience, String currentDesignation,String interestArea) {
        this.name = name;
        this.email = email;
        this.gender = gender;
        this.yearsOfExperience = yearsOfExperience;
        this.currentDesignation = currentDesignation;
        this.interestArea = interestArea;
    }
}
