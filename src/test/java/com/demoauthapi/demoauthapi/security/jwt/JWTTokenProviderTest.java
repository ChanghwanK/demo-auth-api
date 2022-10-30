package com.demoauthapi.demoauthapi.security.jwt;

import com.demoauthapi.demoauthapi.entity.Member;
import com.demoauthapi.demoauthapi.entity.UserDetailsImpl;
import com.demoauthapi.demoauthapi.scurity.jwt.JWTTokenProvider;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.security.core.Authentication;

public class JWTTokenProviderTest {
    @Mock
    public Authentication authentication;

    @Test
    void generateToken() {
        var member = Member.builder()
            .nickName("test")
            .password("test")
            .phoneNumber("010-1234-5678")
            .build();

        UserDetailsImpl userDetails = new UserDetailsImpl(member);

        String token = JWTTokenProvider.generateAccessToken(userDetails);
        System.out.println(token);
    }
}
