package com.demoauthapi.demoauthapi.web.controller.dto.login.response;

import lombok.Getter;

@Getter
public class UnAuthorizeResponse {
    private final String message;

    public UnAuthorizeResponse(String message) {
        this.message = message;
    }
}
