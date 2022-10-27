package com.demoauthapi.demoauthapi.web.controller.dto.login.response;

import lombok.Getter;

@Getter
public class ForbiddenException {
    private final String message;

    public ForbiddenException() {
        this.message = "Forbidden Request";
    }
}
