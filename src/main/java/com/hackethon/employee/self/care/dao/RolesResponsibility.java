package com.hackethon.employee.self.care.dao;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "roles_responsibilities")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RolesResponsibility {
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

    @ElementCollection
    @CollectionTable(name = "tools_list", joinColumns = @JoinColumn( name = "roles_responsibilities_id"))
    @Column(name = "tools")
    private List<String> tools;

    @Column(name = "start_year")
    private int startYear;

    @Column(name = "end_year")
    private int endYear;

    public RolesResponsibility(Employee employee,
                               Project project,
                               String rolesAndResponsibility,
                               String achievements,
                               List<String> tools,
                               int startYear,
                               int endYear){

        this.employee = employee;
        this.project = project;
        this.rolesAndResponsibility =  rolesAndResponsibility;
        this.achievements = achievements;
        this.tools = tools;
        this.startYear = startYear;
        this.endYear = endYear;
    }
}
