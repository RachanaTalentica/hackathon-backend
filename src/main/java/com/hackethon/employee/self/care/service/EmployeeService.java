package com.hackethon.employee.self.care.service;

import com.hackethon.employee.self.care.dao.Employee;
import com.hackethon.employee.self.care.dao.EmployeeRepository;
import com.hackethon.employee.self.care.dto.EmployeeRequest;
import com.hackethon.employee.self.care.dto.EmployeeResponse;
import com.hackethon.employee.self.care.exception.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    public EmployeeResponse saveEmployee(EmployeeRequest employeeRequest) {
        Employee employee
                = new Employee(employeeRequest.getName(),
                employeeRequest.getEmail(),
                employeeRequest.getGender(),
                employeeRequest.getYearsOfExperience(),
                employeeRequest.getCurrentDesignation(),
                employeeRequest.getInterestArea());
        employee.setToolsTechnologyDatabaseFramework(employeeRequest.getToolsTechnologyDatabaseFramework());
        employeeRepository.save(employee);

        return new EmployeeResponse(employee.getId());
    }

    public void updateEmployee(EmployeeRequest employeeRequest, Long employeeId) {

        Employee employee = employeeRepository.findById(employeeId).orElse(null);

        if (employee == null) {
            throw new ResourceNotFoundException(String.format("Employee not found with employeeId = ", employeeId));
        }

        employee.setGender(employeeRequest.getGender() != null ?
                employeeRequest.getGender() :
                employee.getGender());

        employee.setCurrentDesignation(Strings.isNotEmpty(employeeRequest.getCurrentDesignation()) ?
                employeeRequest.getCurrentDesignation() :
                employee.getCurrentDesignation());

        employee.setYearsOfExperience(employeeRequest.getYearsOfExperience() != 0 ?
                employeeRequest.getYearsOfExperience() :
                employee.getYearsOfExperience());

        employee.setToolsTechnologyDatabaseFramework(employeeRequest.getToolsTechnologyDatabaseFramework() != null ?
                employeeRequest.getToolsTechnologyDatabaseFramework() :
                employee.getToolsTechnologyDatabaseFramework());

        employee.setInterestArea(employeeRequest.getInterestArea() != null ?
                employeeRequest.getInterestArea() :
                employee.getInterestArea());

        employeeRepository.save(employee);
    }

    public EmployeeRequest getEmployeeById(Long employeeId){

        Employee employee = employeeRepository.findById(employeeId).orElse(null);

        if (employee == null) {
            throw new ResourceNotFoundException(String.format("Employee not found with employeeId = ", employeeId));
        }

        EmployeeRequest employeeRequest = new EmployeeRequest(employee.getId(),
                employee.getName(),
                employee.getEmail(),
                employee.getGender(),
                employee.getYearsOfExperience(),
                employee.getCurrentDesignation(),
                employee.getToolsTechnologyDatabaseFramework(),
                employee.getInterestArea());

        return employeeRequest;
    }

    public void deleteEmployee(Long employeeId){

        employeeRepository.deleteById(employeeId);
    }
}
