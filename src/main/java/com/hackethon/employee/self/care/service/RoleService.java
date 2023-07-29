package com.hackethon.employee.self.care.service;

import com.hackethon.employee.self.care.dao.*;
import com.hackethon.employee.self.care.dto.ProjectRequest;
import com.hackethon.employee.self.care.dto.RolesResponsibilityRequest;
import com.hackethon.employee.self.care.dto.RolesResponsibilityResponse;
import com.hackethon.employee.self.care.exception.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class RoleService {


    @Autowired
    RolesResponsibilityRepository rolesResponsibilityRepository;
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    ProjectRepository projectRepository;

    @Autowired ProjectService projectService;

    public List<RolesResponsibilityResponse> saveRoles(List<RolesResponsibilityRequest> roleRequestList,
                                                       Long employeeId) {

        Employee employee = employeeRepository.findById(employeeId).orElse(null);

        if (employee == null) {
            throw new ResourceNotFoundException(String.format("Employee not found with employeeId = ", employeeId));
        }

        List<RolesResponsibilityResponse> list = new ArrayList<>();

        for (RolesResponsibilityRequest roleRequest : roleRequestList) {

            Project project = projectRepository.findById(roleRequest.getProjectId()).orElse(null);

            if (project == null) {
                throw new ResourceNotFoundException(String.format("Project not found with ProjectId = ", project.getId()));
            }

            RolesResponsibility rolesResponsibility = new RolesResponsibility(
                    employee,
                    project,
                    roleRequest.getRolesAndResponsibility(),
                    roleRequest.getAchievements(), roleRequest.getTools(),
                    roleRequest.getStartYear(), roleRequest.getEndYear());

            rolesResponsibilityRepository.save(rolesResponsibility);

            list.add(new RolesResponsibilityResponse(rolesResponsibility.getId()));
        }

        return list;

    }

    public void updateRole(RolesResponsibilityRequest roleRequest, Long roleId) {


    }

    public List<RolesResponsibilityRequest> getRoles(Long employeeId){

        List<RolesResponsibilityRequest> rolesResponsibilityRequestList = new ArrayList<>();

        for (RolesResponsibility rolesResponsibility : rolesResponsibilityRepository.findAllByEmployeeId(employeeId)){

            ProjectRequest projectRequest = projectService.getProject(rolesResponsibility.getProject().getId());
            RolesResponsibilityRequest role = new RolesResponsibilityRequest(rolesResponsibility.getId(),
                    rolesResponsibility.getEmployee().getId(),
                    rolesResponsibility.getProject().getId(),
                    rolesResponsibility.getRolesAndResponsibility(),
                    rolesResponsibility.getAchievements(),
                    rolesResponsibility.getTools(),
                    rolesResponsibility.getStartYear(),
                    rolesResponsibility.getEndYear(),
                    projectRequest);

            rolesResponsibilityRequestList.add(role);

        }

        return rolesResponsibilityRequestList;

    }


}
