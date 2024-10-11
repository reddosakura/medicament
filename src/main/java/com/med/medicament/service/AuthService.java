package com.med.medicament.service;

import com.med.medicament.model.AuthDTO;
import com.med.medicament.model.TokenDTO;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class AuthService {

    public TokenDTO logIn(AuthDTO credentials) {

        WebClient.Builder webClientBuilder = WebClient.builder();
        Mono<TokenDTO> token = webClientBuilder.build()
                .post()
                .uri("http://localhost:7979/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(credentials))
                .retrieve()
                .bodyToMono(TokenDTO.class);
        return token.block();

    }

}
