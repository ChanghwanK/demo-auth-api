package com.demoauthapi.demoauthapi.web.controller;

import com.demoauthapi.demoauthapi.service.CustomUserDetailService;
import com.demoauthapi.demoauthapi.web.controller.dto.login.request.SignUpDto;
import com.demoauthapi.demoauthapi.web.controller.dto.login.response.SignUpResult;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class SignUpController {

    private final CustomUserDetailService userDetailsService;

    @PostMapping("/api/sign-up")
    public ResponseEntity<SignUpResult> signUp(@RequestBody SignUpDto signUpDto) {
        return ResponseEntity.ok(userDetailsService.registerNewUser(signUpDto));
    }
}
