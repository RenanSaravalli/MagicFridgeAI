package dev.java10x.MagicFridgeAI.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Value("${https://generativelanguage.googleapis.com/v1beta/models/gemini-3-flash-preview:generateContent}")
    private String geminiApiUrl;

    public WebClient webClient(WebClient.Builder builder) {
        return builder.baseUrl(geminiApiUrl).build();
    }


}
