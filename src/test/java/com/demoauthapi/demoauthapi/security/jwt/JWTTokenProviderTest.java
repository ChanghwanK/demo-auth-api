package com.demoauthapi.demoauthapi.security.jwt;

import com.demoauthapi.demoauthapi.scurity.jwt.JWTTokenProvider;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import org.junit.jupiter.api.Test;

public class JWTTokenProviderTest {

    @Test
    void generateToken() {
        Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
        String token = JWTTokenProvider.generateToken("test-1134");
        System.out.println(token);
    }
}
