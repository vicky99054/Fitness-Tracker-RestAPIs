package com.api_gateway.Api_gateway;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

@RestController
//@RequestMapping("${spring.webflux.base-path}")
public class FallBackController {

    @GetMapping("/users")
    public Mono<ResponseEntity<String>> userFallback() {
        return Mono.just(ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                                       .body("User Service is unavailable. Please try later."));
    }

    @GetMapping("/activity")
    public Mono<ResponseEntity<String>> activityFallback() {
        return Mono.just(ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                                       .body("Activity Tracking Service is unavailable. Please try later."));
    }

    @GetMapping("/recommendation")
    public Mono<ResponseEntity<String>> aiFallback() {
        return Mono.just(ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                                       .body("AI Service is unavailable. Please try later."));
    }
}
