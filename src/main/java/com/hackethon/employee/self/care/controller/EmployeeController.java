package com.hackethon.employee.self.care.controller;

import com.hackethon.employee.self.care.constant.ControllerConstant;
import com.hackethon.employee.self.care.dto.EmployeeRequest;
import com.hackethon.employee.self.care.dto.EmployeeResponse;
import com.hackethon.employee.self.care.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping(ControllerConstant.API_BASE_PATH)
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @PostMapping("/employee")
    public EmployeeResponse createEmployee(@RequestBody EmployeeRequest employeeRequest) {

        EmployeeResponse employeeResponse = employeeService.saveEmployee(employeeRequest);
        return employeeResponse;
    }

    @PutMapping("/employee/{employeeId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateEmployee(@RequestBody EmployeeRequest employeeRequest,
                               @PathVariable("employeeId") Long employeeId) {
        employeeService.updateEmployee(employeeRequest, employeeId);

    }

    @GetMapping("/employee/{employeeId}")
    public EmployeeRequest getEmployees(@PathVariable("employeeId") Long employeeId) {

        return employeeService.getEmployeeById(employeeId);
    }

    @DeleteMapping("/employee/{employeeId}")
    public void deleteEmployee(@PathVariable("employeeId") Long employeeId) {
        employeeService.deleteEmployee(employeeId);
    }
}
