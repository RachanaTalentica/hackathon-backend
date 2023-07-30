package com.hackethon.employee.self.care.dto;

import com.hackethon.employee.self.care.dao.enums.Gender;
import lombok.*;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EmployeeRequest {
    private Long id;
    private String name;
    private String email;
    private String gender;
    private int yearsOfExperience;
    private String currentDesignation;
    private List<String> toolsTechnologyDatabaseFramework;
    private String interestArea;
    private List<RolesResponsibilityRequest> rolesResponsibility;
    private String suggestedTechTraining;
}
