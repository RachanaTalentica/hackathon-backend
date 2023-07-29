package com.hackethon.employee.self.care.dto;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ChatGPTRequest {
    String systemPrompt;
    String userPrompt;

    public ChatGPTRequest() {
    }

    public ChatGPTRequest(String systemPrompt, String userPrompt) {
        this.systemPrompt = systemPrompt;
        this.userPrompt = userPrompt;
    }
}

