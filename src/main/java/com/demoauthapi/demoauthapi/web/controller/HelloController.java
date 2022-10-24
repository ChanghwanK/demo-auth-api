package com.demoauthapi.demoauthapi.web.controller;

import java.util.HashMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public HashMap<String, String> hello() {
        HashMap<String, String> map = new HashMap<>();
        map.put("message", "Hello World!");
        return map;
    }
}
