package com.demoauthapi.demoauthapi.web.controller.dto.login.request;

import com.demoauthapi.demoauthapi.entity.Member;
import lombok.Getter;

@Getter
public class SignUpDto {

    private String nickName;
    private String password;
    private String phoneNumber;

    private SignUpDto() {}
    public Member toEntity(String encryptedPassword) {
        return Member.builder()
                .nickName(nickName)
                .password(encryptedPassword)
                .phoneNumber(phoneNumber)
                .build();
    }
}
