package com.hackethon.employee.self.care.controller;


import com.hackethon.employee.self.care.constant.ControllerConstant;
import com.hackethon.employee.self.care.dto.ProjectRequest;
import com.hackethon.employee.self.care.dto.ProjectResponse;
import com.hackethon.employee.self.care.service.ProjectService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@CrossOrigin(origins = "*")
@RequestMapping(ControllerConstant.API_BASE_PATH)
public class ProjectController {


    @Autowired
    ProjectService projectService;

    @PostMapping("/project")
    public ProjectResponse createProject(@RequestBody ProjectRequest projectRequest) {

        return projectService.createProject(projectRequest);
    }

    @GetMapping("/project")
    public List<ProjectRequest> getProjectList() {

        return projectService.getProjectList();
    }

}
