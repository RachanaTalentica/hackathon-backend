package com.hackethon.employee.self.care.dto;

import lombok.*;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EmployeeRanking {

    private EmployeeRequest employeeRequest;
    private double similarity;
}
