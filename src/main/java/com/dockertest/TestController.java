package com.dockertest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.GetExchange;

@RestController
public class TestController {

    @GetExchange("/api")
    public ResponseEntity<?> test() {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("This is test controller");
    }

}
