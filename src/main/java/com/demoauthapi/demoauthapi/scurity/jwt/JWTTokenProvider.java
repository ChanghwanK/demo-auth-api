package com.demoauthapi.demoauthapi.scurity.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.security.Key;
import java.util.Date;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

public class JWTTokenProvider {
    private JWTTokenProvider() {}
    private static final String SECRET = "12345678901234567890dqwewq1234567890dqwewqe12345678901234567890dqwewq1234567890dqwewqe";

    public static String generateToken(String nickName) {
//        var data = authentication.getName();
        Key key = new SecretKeySpec(DatatypeConverter.parseBase64Binary(SECRET), SignatureAlgorithm.HS256.getJcaName());
        return Jwts.builder()
            .setHeaderParam("typ", "JWT")
            .setIssuer("Changhwan")
            .setIssuedAt(new Date())
            .setSubject(nickName)
            .signWith(key)
            .compact();
    }

//    public static String getData(String token) {
////        Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
//        var data = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
//        return (String) data.getBody().get("roles");
//    }
}

