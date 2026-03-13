package dev.java10x.MagicFridgeAI.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

// Service para o gemini
@Service
public class GeminiService {

    private final WebClient webClient;

    private String apikey = System.getenv("GEMINI_API_KEY");

    public GeminiService(WebClient webClient) {
        this.webClient = webClient;
    }

    public Mono<String> generateRecipe() {
        String prompt = "Agora você é um chef de cozinha e vai me sugerir receitas com base nos ingredientes que vou te passar, ok?";
        return webClient.get().uri("Teste").exchangeToMono();
    }

}
