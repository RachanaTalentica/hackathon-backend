package com.hackethon.employee.self.care.adaptor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ChatGPTConfig {
    public static String API_KEY;

    public static String API_URL;

    @Value("${chatgpt.api.key}")
    public void setApiKeyValue(String apiKey) {
        ChatGPTConfig.API_KEY = apiKey;
    }

    @Value("${chatgpt.api.url}")
    public void setApiUrlValue(String apiUrl) {
        ChatGPTConfig.API_URL = apiUrl;
    }
}
