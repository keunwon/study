package com.circuit.springcircuitbreaker.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    private final RestService restService;

    public Controller(RestService restService) {
        this.restService = restService;
    }

    @GetMapping(value = "/")
    public ResponseEntity<String> main() {
        return ResponseEntity.ok(restService.excute());
    }
}
