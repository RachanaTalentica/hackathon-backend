package com.hackethon.employee.self.care.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

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

    @Column(name = "tools_technology_database_framework")
    private String toolsTechnologyDatabaseFramework;

    @Column(name = "interest_area")
    private String interestArea;


    public Employee() {
    }

    public Employee(String name, String email, Gender gender, int yearsOfExperience, String currentDesignation,
                    String toolsTechnologyDatabaseFramework, String interestArea) {
        this.name = name;
        this.email = email;
        this.gender = gender;
        this.yearsOfExperience = yearsOfExperience;
        this.currentDesignation = currentDesignation;
        this.toolsTechnologyDatabaseFramework = toolsTechnologyDatabaseFramework;
        this.interestArea = interestArea;
    }
}
