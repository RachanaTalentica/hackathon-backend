package com.hackethon.employee.self.care.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RolesResponsibilityRequest {
    private Long id;
    private Long employeeId;
    private Long projectId;
    private String rolesAndResponsibility;
    private String achievements;
    private List<String> tools;
    private int startYear;
    private int endYear;

}
