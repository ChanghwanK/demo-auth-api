package com.demoauthapi.demoauthapi.scurity.model;

import lombok.Builder;
import lombok.Getter;

@Getter
public class TokenVerifyResult {

    private final boolean result;
    private final String username;
    private final String message;

    @Builder
    public TokenVerifyResult(boolean result, String username, String message) {
        this.result = result;
        this.username = username;
        this.message = message;
    }
}
