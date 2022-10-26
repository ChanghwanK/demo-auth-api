package com.demoauthapi.demoauthapi.scurity.jwt;

import com.demoauthapi.demoauthapi.entity.UserDetailsImpl;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;

public class JWTTokenProvider {

    private JWTTokenProvider() {}
    private static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public static String generateToken(UserDetailsImpl userDetails) {
        String nickName = userDetails.getUsername();
        return Jwts.builder()
            .setHeaderParam("typ", "JWT")
            .setIssuer(nickName)
            .setIssuedAt(new Date())
            .setSubject(nickName)
            .signWith(key)
            .compact();
    }

    public static String getUserNameFromToken(String token) {
        var data = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
        return data.getSubject();
    }
}
