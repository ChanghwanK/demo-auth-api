package com.demoauthapi.demoauthapi.web.controller.dto.login.response;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
public class SignUpResult {

    private String nickName;
    private LocalDateTime signUpDate;

    private SignUpResult() {}

    @Builder
    private SignUpResult(String nickName, LocalDateTime signUpDate) {
        this.nickName = nickName;
        this.signUpDate = signUpDate;
    }

    public static SignUpResult of(String nickName, LocalDateTime signUpDate) {
        return SignUpResult.builder()
                .nickName(nickName)
                .signUpDate(signUpDate)
                .build();
    }
}
