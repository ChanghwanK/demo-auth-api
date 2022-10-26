package com.demoauthapi.demoauthapi.security.jwt;

import com.demoauthapi.demoauthapi.scurity.jwt.JWTTokenProvider;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.security.core.Authentication;

public class JWTTokenProviderTest {
    @Mock
    public Authentication authentication;

    @Test
    void generateToken() {
        Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
        String token = JWTTokenProvider.generateToken(authentication);
        System.out.println(token);
    }
}
