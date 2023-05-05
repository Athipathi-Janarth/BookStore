package com.scanbuy.webapp.Controller;

import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {
    @GetMapping("/health")
    public ResponseEntity HealthCheck(){
        return (ResponseEntity) ResponseEntity.ok("Server is Running Successfully");
    }
}
