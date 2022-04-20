package com.circuit.springcircuitbreaker.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;

@Service
public class RestService {
    private final RestTemplate restTemplate;
    private final ObjectMapper mapper;

    public RestService(@Qualifier("scrapRestTemplate") RestTemplate restTemplate, ObjectMapper mapper) {
        this.restTemplate = restTemplate;
        this.mapper = mapper;
    }

    @CircuitBreaker(name = "scrap", fallbackMethod = "helloFallback")
    public String excute() {
        Map<String, String> params = new HashMap<>();
        params.put("url", "https://naver.com");

        String url = UriComponentsBuilder.fromPath("/api/v1/scrap")
                .queryParam("url", "https://naver.com")
                .toUriString();

        try {
            System.out.println(">>>>>>>>>>> API 호출");
            ResponseEntity<Object> response = restTemplate.getForEntity(url, Object.class);
            return mapper.writeValueAsString(response.getBody());
        } catch(JsonProcessingException e) {
            e.printStackTrace();
            return "error";
        }
    }

    private String helloFallback(Throwable t) {
        System.out.println(">>>>>>>>>>>> 실패");
        return "error";
    }
}
