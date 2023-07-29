package com.hackethon.employee.self.care.controller;

import com.hackethon.employee.self.care.constant.ControllerConstant;
import com.hackethon.employee.self.care.dto.EmployeeRequest;
import com.hackethon.employee.self.care.dto.EmployeeResponse;
import com.hackethon.employee.self.care.dto.RolesResponsibilityRequest;
import com.hackethon.employee.self.care.dto.RolesResponsibilityResponse;
import com.hackethon.employee.self.care.service.EmployeeService;
import com.hackethon.employee.self.care.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping(ControllerConstant.API_BASE_PATH + "/employee/{employeeId}/roles")
public class RoleController {
    @Autowired
    RoleService roleService;

    @PostMapping
    public List<RolesResponsibilityResponse> createRoles(
            @RequestBody List<RolesResponsibilityRequest> roleRequestList,
              @PathVariable("employeeId") Long employeeId) {

        List<RolesResponsibilityResponse> roleResponse = roleService.saveRoles(roleRequestList, employeeId);
        return roleResponse;
    }

    @GetMapping
    public List<RolesResponsibilityRequest> getRolesList(@PathVariable("employeeId") Long employeeId){

        return roleService.getRoles(employeeId);

    }

}
