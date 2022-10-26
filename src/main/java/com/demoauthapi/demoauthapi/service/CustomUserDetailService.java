package com.demoauthapi.demoauthapi.service;

import com.demoauthapi.demoauthapi.entity.Member;
import com.demoauthapi.demoauthapi.entity.UserDetailsImpl;
import com.demoauthapi.demoauthapi.repsoitory.AuthRepository;
import com.demoauthapi.demoauthapi.web.controller.dto.login.request.SignUpDto;
import com.demoauthapi.demoauthapi.web.controller.dto.login.response.SignUpResult;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CustomUserDetailService implements UserDetailsService {

    private final AuthRepository authRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var member = authRepository.findByNickName(username).orElseThrow(() -> new UsernameNotFoundException("존재하지 않는 유저입니다."));
        return new UserDetailsImpl(member);
    }

    public SignUpResult registerNewUser(SignUpDto signUpDto) {
        String encodedPassword = passwordEncoder.encode(signUpDto.getPassword());
        Member member = signUpDto.toEntity(encodedPassword);
        authRepository.save(member);
        return SignUpResult.of(member.getNickName(), member.getSignUpDate());
    }
}
