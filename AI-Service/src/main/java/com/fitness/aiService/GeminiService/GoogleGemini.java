package com.fitness.aiService.GeminiService;

import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class GoogleGemini {
	
	@Value("${google.api.url}")
	private String Google_GeminiApi;
	
	@Value("${google.api.key}")
	private String Google_GeminiApi_Key;
	
	private WebClient webClient;
	
	public GoogleGemini(WebClient.Builder wBuilder) {
		this.webClient=wBuilder.build();
	}
	
	
	public String GetAnswer(String Question) {
		
		Map<String, Object> requestBody=Map.of("contents" , new Object[] {
				
				Map.of("parts" , new Object[] {
					
						Map.of("text", Question)		
				})		
		});
		
		 // Create response
		
	    String response = webClient.post()  
	        .uri(Google_GeminiApi + Google_GeminiApi_Key)
	        .header("Content-Type", "application/json")
	        .bodyValue(requestBody)
	        .retrieve()
	        .bodyToMono(String.class)
	        .block(); 

	    return response;
	}
	

}
