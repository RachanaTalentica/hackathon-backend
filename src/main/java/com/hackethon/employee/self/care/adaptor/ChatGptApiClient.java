package com.hackethon.employee.self.care.adaptor;

import com.google.gson.*;
import com.hackethon.employee.self.care.dto.ChatGPTRequest;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

import static com.hackethon.employee.self.care.constant.ControllerConstant.*;

public class ChatGptApiClient {

    public static String sendApiRequest(ChatGPTRequest chatGPTRequest) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(API_URL);

        httpPost.addHeader("Content-Type", "application/json");
        httpPost.addHeader("Authorization", "Bearer " + API_KEY);

        StringEntity requestBody=new StringEntity(
                createApiRequestBody(chatGPTRequest),
                ContentType.APPLICATION_JSON);
        httpPost.setEntity(requestBody);

        HttpResponse httpResponse = httpClient.execute(httpPost);
        HttpEntity responseEntity = httpResponse.getEntity();
        String response = EntityUtils.toString(responseEntity);

        httpClient.close();

        return response;
    }

    private static String createApiRequestBody(ChatGPTRequest chatGPTRequest) {
        JsonObject requestBody = new JsonObject();
        requestBody.addProperty("model", MODEL);
        requestBody.addProperty("temperature", TEMPERATURE);
        JsonArray messages = new JsonArray();
        JsonObject systemMessage = new JsonObject();
        systemMessage.addProperty("role", SYSTEM_ROLE);
        systemMessage.addProperty("content", chatGPTRequest.getSystemPrompt());
        messages.add(systemMessage);
        JsonObject userMessage = new JsonObject();
        userMessage.addProperty("role", USER_ROLE);
        userMessage.addProperty("content", chatGPTRequest.getUserPrompt());
        messages.add(userMessage);
        requestBody.add("messages", messages);
        Gson gson = new Gson();
        return gson.toJson(requestBody);
    }

    public static String extractContentFromResponse(String response) {
        JsonParser parser = new JsonParser();
        JsonObject responseObj = parser.parse(response).getAsJsonObject();

        // Extract the "content" from the "message" object inside the "choices" array
        String content = responseObj
                .getAsJsonArray("choices")
                .get(0) // Assuming there's only one choice in the array as per the response
                .getAsJsonObject()
                .getAsJsonObject("message")
                .get("content")
                .getAsString();

        return content;
    }
}
