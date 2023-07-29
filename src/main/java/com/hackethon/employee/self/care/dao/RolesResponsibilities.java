package com.hackethon.employee.self.care.dao;


import javax.persistence.*;

@Entity
@Table(name = "roles_responsibilities")
public class RolesResponsibilities {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    @Column(name = "roles_and_responsibility")
    private String rolesAndResponsibility;

    @Column(name = "achievements")
    private String achievements;

    @Column(name = "tools")
    private String tools;

    @Column(name = "start_year")
    private int startYear;

    @Column(name = "end_year")
    private int endYear;

    public RolesResponsibilities() {
    }
}
