package com.hackethon.employee.self.care.dto;

import com.hackethon.employee.self.care.dao.Gender;
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
    private Gender gender;
    private int yearsOfExperience;
    private String currentDesignation;
    private List<String> toolsTechnologyDatabaseFramework;
    private String interestArea;
    private List<RolesResponsibilityRequest> rolesResponsibility;
}
