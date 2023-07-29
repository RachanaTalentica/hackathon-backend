package com.hackethon.employee.self.care.service;


import com.hackethon.employee.self.care.dao.Project;
import com.hackethon.employee.self.care.dao.ProjectRepository;
import com.hackethon.employee.self.care.dto.ProjectRequest;
import com.hackethon.employee.self.care.dto.ProjectResponse;
import com.hackethon.employee.self.care.exception.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class ProjectService {

    @Autowired ProjectRepository projectRepository;

    public ProjectResponse createProject(ProjectRequest projectRequest){

        Project project =new Project(projectRequest.getProjectName(), projectRequest.getProjectDescription());
        projectRepository.save(project);

        return new ProjectResponse(project.getId());
    }

    public List<ProjectRequest> getProjectList(){

        List<ProjectRequest> projectList = new ArrayList<>();

       for( Project project : projectRepository.findAll()) {
           ProjectRequest projectRequest = new ProjectRequest(project.getId(),
                   project.getProjectName(),
                   project.getProjectDescription());
           projectList.add(projectRequest);
       }

       return projectList;
    }

    public ProjectRequest getProject(Long projectId){

        Project project =  projectRepository.findById(projectId).orElse(null);

        if(project == null){
            throw new ResourceNotFoundException(String.format("Project not found with projectId = ",projectId));
        }

        return new ProjectRequest(project.getId(),
                project.getProjectName(),
                project.getProjectDescription());
    }
}
