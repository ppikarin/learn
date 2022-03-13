package com.ppikarin.client.core;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClientService {

    private final RestTemplate restTemplate = new RestTemplate();

    public List<AttemptDTO> getAllStats() {

        return restTemplate
                .exchange(
                        "http://127.0.0.1:8080/attempts/all",
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<List<AttemptDTO>>() {
                        })
                .getBody();

    }

}
