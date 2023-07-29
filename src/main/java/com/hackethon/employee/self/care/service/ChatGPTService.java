package com.hackethon.employee.self.care.service;

import com.hackethon.employee.self.care.adaptor.ChatGptApiClient;
import com.hackethon.employee.self.care.dao.Employee;
import com.hackethon.employee.self.care.dto.ChatGPTRequest;
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


}
