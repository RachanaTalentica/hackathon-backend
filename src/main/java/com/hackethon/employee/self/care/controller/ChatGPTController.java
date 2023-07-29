package com.hackethon.employee.self.care.controller;

import com.hackethon.employee.self.care.adaptor.ChatGptApiClient;
import com.hackethon.employee.self.care.constant.ControllerConstant;
import com.hackethon.employee.self.care.dto.ChatGPTRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@Slf4j
@RequestMapping(ControllerConstant.API_BASE_PATH)
@CrossOrigin(origins = "*")
public class ChatGPTController {
    @PostMapping("/chatgpt")
    public ResponseEntity<String> chatGpt(@RequestBody ChatGPTRequest chatGPTRequest) throws IOException {
        ChatGptApiClient chatGptApiClient = new ChatGptApiClient();
        String response = ChatGptApiClient.sendApiRequest(chatGPTRequest);
        try {
            String content = ChatGptApiClient.extractContentFromResponse(response);
            return new ResponseEntity<>(content, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
