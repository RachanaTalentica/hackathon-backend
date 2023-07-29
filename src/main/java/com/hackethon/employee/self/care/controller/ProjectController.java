package com.hackethon.employee.self.care.controller;


import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.hackethon.employee.self.care.constant.ControllerConstant;
import com.hackethon.employee.self.care.dao.Project;
import com.hackethon.employee.self.care.dto.ProjectRequest;
import com.hackethon.employee.self.care.dto.ProjectResponse;
import com.hackethon.employee.self.care.service.ChatGPTService;
import com.hackethon.employee.self.care.service.ProjectService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@Slf4j
@CrossOrigin(origins = "*")
@RequestMapping(ControllerConstant.API_BASE_PATH)
public class ProjectController {


    @Autowired
    ProjectService projectService;

    @Autowired
    ChatGPTService chatGPTService;

    @PostMapping("/project")
    public ProjectResponse createProject(@RequestBody ProjectRequest projectRequest) {

        return projectService.createProject(projectRequest);
    }

    @GetMapping("/project")
    public List<ProjectRequest> getProjectList() {

        return projectService.getProjectList();
    }

    @GetMapping("/project-suggestion/{projectId}")
    public String getProject(@PathVariable("projectId") Long projectId) throws IOException {
        ProjectRequest project=projectService.getProject(projectId);
        String response=chatGPTService.addProjectTechStackAndRelatedProjectInMarket(project);
        return response;
    }

}
