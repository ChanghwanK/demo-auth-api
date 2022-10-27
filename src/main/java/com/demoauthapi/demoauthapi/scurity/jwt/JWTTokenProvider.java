package com.demoauthapi.demoauthapi.scurity.jwt;

import com.demoauthapi.demoauthapi.entity.UserDetailsImpl;
import com.demoauthapi.demoauthapi.scurity.model.TokenVerifyResult;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import java.security.Key;
import java.util.Arrays;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JWTTokenProvider {

    private JWTTokenProvider() {}
    private static final Logger logger = LoggerFactory.getLogger(JWTTokenProvider.class);
    private static final Key key = Keys.hmacShaKeyFor(getEncodedKey().getBytes());

    public static String generateToken(UserDetailsImpl userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return Jwts.builder()
            .setClaims(claims)
            .setSubject(userDetails.getUsername())
            .setIssuedAt(new Date(System.currentTimeMillis()))
            .signWith(key, SignatureAlgorithm.HS256)
            .compact();
    }

    public static TokenVerifyResult verifyToken(String token) {
        try {
            var data = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
            return new TokenVerifyResult(true, data.getSubject(), "Token is valid");
        } catch(
            SignatureException |
            IllegalArgumentException |
            MalformedJwtException |
            UnsupportedJwtException e
        ) {
            var datas = e.getStackTrace();
            for (var data : datas) {
                logger.error(data.toString());
            }
            return new TokenVerifyResult(false, null, e.getMessage());
        }
    }


    private static String getEncodedKey() {
        return Base64.getEncoder().encodeToString("312jkjkdasndjkashbdjkasnjdnasdj32k4j3k24k32jdndwqehwqbejkqwbewqjebdasd".getBytes());
    }
}
