package com.hackethon.employee.self.care.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RolesResponsibilityRequest {
    private Long id;
    @JsonIgnore
    private Long employeeId;
    private Long projectId;
    private String rolesAndResponsibility;
    private String achievements;
    private List<String> tools;
    private int startYear;
    private int endYear;
    private ProjectRequest project;

}
