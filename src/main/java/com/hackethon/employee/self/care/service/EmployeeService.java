package com.hackethon.employee.self.care.service;

import com.hackethon.employee.self.care.dao.Employee;
import com.hackethon.employee.self.care.dao.EmployeeRepository;
import com.hackethon.employee.self.care.dao.enums.CurrentDesignation;
import com.hackethon.employee.self.care.dao.enums.Gender;
import com.hackethon.employee.self.care.dto.EmployeeRequest;
import com.hackethon.employee.self.care.dto.EmployeeResponse;
import com.hackethon.employee.self.care.dto.RolesResponsibilityRequest;
import com.hackethon.employee.self.care.exception.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    RoleService roleService;

    @Autowired
    ChatGPTService chatGPTService;


    public EmployeeResponse saveEmployee(EmployeeRequest employeeRequest) throws IOException {
        Employee employee
                = new Employee(employeeRequest.getName(),
                employeeRequest.getEmail(),
                Gender.fromValue(employeeRequest.getGender()),
                employeeRequest.getYearsOfExperience(),
                CurrentDesignation.fromValue(employeeRequest.getCurrentDesignation()),
                employeeRequest.getInterestArea());
        employee.setToolsTechnologyDatabaseFramework(employeeRequest.getToolsTechnologyDatabaseFramework());
        employee = chatGPTService.addSuggestedTechStackAndTraining(employee);
        employeeRepository.save(employee);
        return new EmployeeResponse(employee.getId());
    }

    public void updateEmployee(EmployeeRequest employeeRequest, Long employeeId) throws IOException {

        Employee employee = employeeRepository.findById(employeeId).orElse(null);

        if (employee == null) {
            throw new ResourceNotFoundException(String.format("Employee not found with employeeId = ", employeeId));
        }

        employee.setGender(employeeRequest.getGender() != null ?
                Gender.fromValue(employeeRequest.getGender()) :
                employee.getGender());

        employee.setCurrentDesignation(Strings.isNotEmpty(employeeRequest.getCurrentDesignation()) ?
                CurrentDesignation.fromValue(employeeRequest.getCurrentDesignation()) :
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
        employee = chatGPTService.addSuggestedTechStackAndTraining(employee);
        employeeRepository.save(employee);
    }

    public EmployeeRequest getEmployeeById(Long employeeId) {

        Employee employee = employeeRepository.findById(employeeId).orElse(null);

        if (employee == null) {
            throw new ResourceNotFoundException(String.format("Employee not found with employeeId = ", employeeId));
        }

        List<RolesResponsibilityRequest> rolesResponsibilityList = roleService.getRoles(employeeId);

        EmployeeRequest employeeRequest = new EmployeeRequest(employee.getId(),
                employee.getName(),
                employee.getEmail(),
                employee.getGender().getValue(),
                employee.getYearsOfExperience(),
                employee.getCurrentDesignation().getValue(),
               employee.getToolsTechnologyDatabaseFramework(),
                employee.getInterestArea(), rolesResponsibilityList, employee.getSuggestedTechTraining());

        return employeeRequest;
    }

    public void deleteEmployee(Long employeeId) {

        employeeRepository.deleteById(employeeId);
    }

    public List<EmployeeRequest> getEmployees(){

        List<EmployeeRequest> employeeRequests = new ArrayList<>();

        for(Employee employee : employeeRepository.findAll()){

            employeeRequests.add(getEmployeeById(employee.getId()));
        }

        return employeeRequests;
    }
}
