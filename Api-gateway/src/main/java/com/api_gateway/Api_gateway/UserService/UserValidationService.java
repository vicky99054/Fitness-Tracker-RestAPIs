package com.api_gateway.Api_gateway.UserService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class UserValidationService {

    @Autowired
    private WebClient webClient;

    public Boolean userValidate(String keyclockId){

        if(keyclockId != null){

            return webClient.get()
                    .uri("/api/users/{userId}/valid/keyclockid",keyclockId)
                    .retrieve()
                    .bodyToMono(Boolean.class)
                    .block();



        }

        return false;
    }
}
