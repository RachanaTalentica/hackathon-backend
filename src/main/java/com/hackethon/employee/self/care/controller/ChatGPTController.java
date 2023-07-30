package com.hackethon.employee.self.care.controller;

import com.hackethon.employee.self.care.adaptor.ChatGptApiClient;
import com.hackethon.employee.self.care.constant.ControllerConstant;
import com.hackethon.employee.self.care.dto.ChatGPTRequest;
import com.hackethon.employee.self.care.service.ChatGPTService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@Slf4j
@RequestMapping(ControllerConstant.API_BASE_PATH)
@CrossOrigin(origins = "*")
public class ChatGPTController {
    @Autowired
    ChatGPTService chatGPTService;
    @PostMapping("/chatgpt")
    public ResponseEntity<String> chatGpt(@RequestBody ChatGPTRequest chatGPTRequest) throws IOException {
        String response = ChatGptApiClient.sendApiRequest(chatGPTRequest);
        try {
            String content = ChatGptApiClient.extractContentFromResponse(response);
            return new ResponseEntity<>(content, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/userPrompt")
    public String getProjectDetail(@RequestBody String userPrompt) throws IOException {
        String response= chatGPTService.getTechStackBasedOnInputPrompt(userPrompt);
        return response;
    }
}
