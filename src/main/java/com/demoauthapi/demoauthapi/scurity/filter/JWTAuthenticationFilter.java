package com.demoauthapi.demoauthapi.scurity.filter;

import com.demoauthapi.demoauthapi.scurity.jwt.JWTTokenProvider;
import com.demoauthapi.demoauthapi.scurity.model.TokenVerifyResult;
import com.demoauthapi.demoauthapi.service.CustomUserDetailService;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

public class JWTAuthenticationFilter extends BasicAuthenticationFilter {

    protected final Logger logger = LoggerFactory.getLogger(JWTTokenProvider.class);
    private final CustomUserDetailService customUserDetailService;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager, CustomUserDetailService customUserDetailService) {
        super(authenticationManager);
        this.customUserDetailService = customUserDetailService;
    }

    @Override
    protected void doFilterInternal(
        HttpServletRequest request,
        HttpServletResponse response,
        FilterChain chain
    ) throws IOException, ServletException {
        String bearer = request.getHeader("Authorization");
        if (bearer == null || !bearer.startsWith("Bearer ")) {
            chain.doFilter(request, response);
            return;
        }
        String token = bearer.substring("Bearer ".length());
        TokenVerifyResult verifyResult = JWTTokenProvider.verifyToken(token);
        if (verifyResult.isResult()) {
            var user = customUserDetailService.loadUserByUsername(verifyResult.getUsername());
            var usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                user.getUsername(),
                user.getPassword(),
                null
            );
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            chain.doFilter(request, response);
        } else if(verifyResult.getMessage() == "Token is expired") {
            customUserDetailService.getRefreshToken(verifyResult.getUsername());
        } else {
            logger.error(verifyResult.getMessage());
        }
        chain.doFilter(request, response);
    }
}
