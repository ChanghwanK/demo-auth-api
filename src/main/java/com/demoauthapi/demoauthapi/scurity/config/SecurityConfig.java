package com.demoauthapi.demoauthapi.scurity.config;

import com.demoauthapi.demoauthapi.scurity.exeption.CustomAccessDeniedHandler;
import com.demoauthapi.demoauthapi.scurity.exeption.CustomEntryPoint;
import com.demoauthapi.demoauthapi.scurity.filter.JWTAuthenticationFilter;
import com.demoauthapi.demoauthapi.scurity.filter.JwtLoginFilter;
import com.demoauthapi.demoauthapi.service.CustomUserDetailService;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomUserDetailService customUserDetailService;

    public SecurityConfig(CustomUserDetailService customUserDetailService) {
        this.customUserDetailService = customUserDetailService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .authorizeRequests()
            .antMatchers("/login", "/api/sign-up").permitAll()
            .anyRequest().authenticated()
            .and()
            .addFilterAt(new JwtLoginFilter(authenticationManager(), customUserDetailService), UsernamePasswordAuthenticationFilter.class)
            .addFilterAt(new JWTAuthenticationFilter(authenticationManager(), customUserDetailService), UsernamePasswordAuthenticationFilter.class)
            .exceptionHandling().accessDeniedHandler(new CustomAccessDeniedHandler()).authenticationEntryPoint(new CustomEntryPoint());
    }

}
