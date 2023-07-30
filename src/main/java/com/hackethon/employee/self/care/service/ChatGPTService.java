package com.hackethon.employee.self.care.service;

import com.hackethon.employee.self.care.adaptor.ChatGptApiClient;
import com.hackethon.employee.self.care.dao.Employee;
import com.hackethon.employee.self.care.dao.Project;
import com.hackethon.employee.self.care.dto.ChatGPTRequest;
import com.hackethon.employee.self.care.dto.ProjectRequest;
import com.hackethon.employee.self.care.exception.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@Slf4j
public class ChatGPTService {

    public Employee addSuggestedTechStackAndTraining(Employee employee) throws IOException {
        String userPrompt = "Employee details are: " + employee.toStringEmployeeYOEToolsInterestArea();
        String systemPrompt =
                "Suggest any 2 new techstack and 2 best rated training courses along with link (not only tech) " +
                        "(only names without description in json format) " +
                        "to do that employees should focus based on list of toolsTechnologyDatabaseFramework, " +
                        "interestArea, YearsOfExperience and currentDesignation for better career";
        ChatGPTRequest chatGPTRequest = new ChatGPTRequest(systemPrompt, userPrompt);
        String response = ChatGptApiClient.sendApiRequest(chatGPTRequest);
        try {
            String content = ChatGptApiClient.extractContentFromResponse(response);
            employee.setSuggestedTechTraining(content);
        } catch (Exception e) {
            new ResourceNotFoundException(e.getMessage());
        }
        return employee;
    }

    public String addProjectTechStackAndRelatedProjectInMarket(ProjectRequest project) throws IOException {
        String userPrompt = project.getProjectDescription();
        String systemPrompt =
                "Given the description of the project, give the best tech stack to be used for the project" +
                " and best architectural decisions in json format and " +
                "also give similar project around the market in json format." +
                " Also combine all the tech stack and return in a new field";
        ChatGPTRequest chatGPTRequest = new ChatGPTRequest(systemPrompt, userPrompt);
        String response = ChatGptApiClient.sendApiRequest(chatGPTRequest);
        try {
            String content = ChatGptApiClient.extractContentFromResponse(response);
            return content;
        } catch (Exception e) {
            new ResourceNotFoundException(e.getMessage());
        }
        return null;
    }


    public String getTechStackBasedOnInputPrompt(String userPrompt) throws IOException {
        String systemPrompt =
                "Based on the requirement, please provide tech stack (include similar techstack " +
                        "as well which can be a replacement of mentioned techstack) that can be " +
                        "useful on the basis of requirement in json format. " +
                        "Provide only the name of techstack. JSON should have only one key 'tech'";

        ChatGPTRequest chatGPTRequest = new ChatGPTRequest(systemPrompt, userPrompt);
        String response = ChatGptApiClient.sendApiRequest(chatGPTRequest);
        try {
            String content = ChatGptApiClient.extractContentFromResponse(response);
            return content;
        } catch (Exception e) {
            new ResourceNotFoundException(e.getMessage());
        }
        return null;
    }

}
