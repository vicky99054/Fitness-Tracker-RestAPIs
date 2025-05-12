package com.ActivityService.UserValidation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@Service
public class UserValidationService {
	
	@Autowired
	private WebClient webClient;
	
	
	public boolean validateUser(String userId) {
		
		try {
			
			return webClient.get()
					.uri("/api/users/{userId}/valid",userId)
					.retrieve()
					.bodyToMono(Boolean.class)
					.block();
			
		} catch (WebClientResponseException e) {
		
			if (e.getStatusCode()==HttpStatus.NOT_FOUND) {
				throw new RuntimeException("User Not Found "+ userId);
			}
			else if(e.getStatusCode()==HttpStatus.BAD_REQUEST) {
				throw new RuntimeException("Invalid Request "+userId);
			}
		}
		
		return false;
				
	}

}
