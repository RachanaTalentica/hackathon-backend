package com.hackethon.employee.self.care.dto;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProjectRequest {
    private Long id;
    private String projectName;
    private String projectDescription;
}
