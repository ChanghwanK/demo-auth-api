package com.demoauthapi.demoauthapi.web.controller;

import com.demoauthapi.demoauthapi.web.controller.dto.HelloDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/api/hello")
    public ResponseEntity<HelloDto> hello() {
        return ResponseEntity.ok(new HelloDto());
    }
}
