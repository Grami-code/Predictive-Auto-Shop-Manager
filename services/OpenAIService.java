package com.serviceauto.services;

import okhttp3.*;
import org.json.JSONObject;

/**
 * The service for API connection
 *
 * OpenAIService class sends prompts to AI
 * and gets generated answers in return.
 * Can be used to generate description.
 */

public class OpenAIService {

    private static final String API_KEY = "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";
    private static final String URL = "https://api.openai.com/v1/chat/completions";

    public static String cereAI(String prompt) {
        OkHttpClient client = new OkHttpClient();

        JSONObject json = new JSONObject();
        json.put("model", "gpt-4o-mini");
        json.put("max_tokens", 300);

        json.put("messages", new org.json.JSONArray()
                .put(new JSONObject()
                        .put("role", "user")
                        .put("content", prompt)));

        RequestBody body = RequestBody.create(
                json.toString(),
                MediaType.parse("application/json")
        );

        Request request = new Request.Builder()
                .url(URL)
                .header("Authorization", "Bearer " + API_KEY)
                .post(body)
                .build();

        try (Response response = client.newCall(request).execute()) {

            if (!response.isSuccessful()) return "AI error";

            String rasp = response.body().string();
            JSONObject obj = new JSONObject(rasp);

            return obj
                    .getJSONArray("choices")
                    .getJSONObject(0)
                    .getJSONObject("message")
                    .getString("content");

        } catch (Exception e) {
            e.printStackTrace();
            return "Error at the AI connection";
        }
    }
}

// you can enter the car specifications and the services that are needed and it'll generate the pricing based on that


