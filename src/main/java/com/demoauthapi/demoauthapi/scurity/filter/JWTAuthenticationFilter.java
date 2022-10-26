package com.demoauthapi.demoauthapi.scurity.filter;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

public class JWTAuthenticationFilter extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        var header = httpServletRequest.getHeader("Authorization");
        System.out.println("header: " +  header);
        var data = SecurityContextHolder.getContext().getAuthentication();
        if (StringUtils.startsWith(header, "Bearer ")) {
            var token = StringUtils.substringAfter(header, "Bearer ");
            System.out.println("token: " + token);
        }
        chain.doFilter(request, response);
    }
}
