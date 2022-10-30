package com.demoauthapi.demoauthapi.scurity.jwt;

import com.demoauthapi.demoauthapi.entity.UserDetailsImpl;
import com.demoauthapi.demoauthapi.scurity.model.TokenInfo;
import com.demoauthapi.demoauthapi.scurity.model.TokenVerifyResult;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import java.security.Key;
import java.util.Base64;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JWTTokenProvider {

    private JWTTokenProvider() {}
    private static final Logger logger = LoggerFactory.getLogger(JWTTokenProvider.class);
    private static final Key key = Keys.hmacShaKeyFor(getEncodedKey().getBytes());
    private static final Integer ACCESS_TOKEN_EXPIRATION_TIME = 3;
    private static final Integer REFRESH_TOKEN_EXPIRATION_TIME = 3600 * 60 * 24 * 7;

    public static String generateAccessToken(UserDetailsImpl userDetails) {
        return Jwts.builder()
            .setSubject(userDetails.getUsername())
            .setExpiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_EXPIRATION_TIME))
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
        } catch(ExpiredJwtException ex) {
            return new TokenVerifyResult(false, null, "Token is expired");
        } catch(
            SignatureException |
            IllegalArgumentException |
            MalformedJwtException |
            UnsupportedJwtException e
        ) {
            var stackTraceElements = e.getStackTrace();
            for (var data : stackTraceElements) {
                logger.error(data.toString());
            }
            return new TokenVerifyResult(false, null, e.getMessage());
        }
    }


    private static String getEncodedKey() {
        return Base64.getEncoder().encodeToString("312jkjkdasndjkashbdjkasnjdnasdj32k4j3k24k32jdndwqehwqbejkqwbewqjebdasd".getBytes());
    }

    public static TokenInfo generateTokenInfo(UserDetailsImpl user) {
        var token = generateAccessToken(user);
        var refreshToken = generateRefreshToken(user);
        return TokenInfo.of(token, refreshToken, ACCESS_TOKEN_EXPIRATION_TIME);
    }

    private static String generateRefreshToken(UserDetailsImpl userDetails) {
        return Jwts.builder()
            .setSubject(userDetails.getUsername())
            .setExpiration(new Date(System.currentTimeMillis() + REFRESH_TOKEN_EXPIRATION_TIME))
            .setIssuedAt(new Date(System.currentTimeMillis()))
            .signWith(key, SignatureAlgorithm.HS256)
            .compact();
    }
}
