package dev.java10x.MagicFridgeAI.controller;

import dev.java10x.MagicFridgeAI.service.GeminiService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class RecipeController {

    private GeminiService geminiService;

    public RecipeController(GeminiService geminiService) {
        this.geminiService = geminiService;
    }

    /*Métod de requisição ao gemini espera o prompt de resposta da (string)*/
    @GetMapping
    public Mono<ResponseEntity<String>> generateRecipe() {

        return geminiService.generateRecipe();

    }


}
