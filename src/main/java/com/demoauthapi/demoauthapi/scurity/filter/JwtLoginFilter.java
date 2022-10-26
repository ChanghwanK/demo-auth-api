package com.demoauthapi.demoauthapi.scurity.filter;

import com.demoauthapi.demoauthapi.entity.UserDetailsImpl;
import com.demoauthapi.demoauthapi.scurity.jwt.JWTTokenProvider;
import com.demoauthapi.demoauthapi.web.controller.dto.login.request.LoginDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class JwtLoginFilter extends UsernamePasswordAuthenticationFilter {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public JwtLoginFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    public Authentication attemptAuthentication(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws AuthenticationException {
        try {
            LoginDto loginDto = objectMapper.readValue(request.getInputStream(), LoginDto.class);
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                loginDto.getNickName(),
                loginDto.getPwd(),
                null
            );
            return getAuthenticationManager().authenticate(token);
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * DaoAuthenticationProvider에서 인증이 성공하면 호출되는 메소드
     *
     * 1. JWT 토큰을 생성한다
     * 2. Response 세팅
     *  - 요구사항에 맞게
     *   - Header에 토큰
     *   - 유저정보 등등
     */
    @Override
    protected void successfulAuthentication(
        HttpServletRequest request,
        HttpServletResponse response,
        FilterChain chain,
        Authentication authResult
    ) throws IOException, ServletException {
        var user = (UserDetailsImpl) authResult.getPrincipal();
        response.setHeader("Authorization", "Bearer " + JWTTokenProvider.generateToken(user));
        response.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().write(objectMapper.writeValueAsString(user));
    }
}
