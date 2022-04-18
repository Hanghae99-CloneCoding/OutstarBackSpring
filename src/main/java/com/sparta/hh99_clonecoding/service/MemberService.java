package com.sparta.hh99_clonecoding.service;

import com.sparta.hh99_clonecoding.dto.MemberRequestDto;
import com.sparta.hh99_clonecoding.exception.Code;
import com.sparta.hh99_clonecoding.exception.PrivateException;
import com.sparta.hh99_clonecoding.jwt.JwtFilter;
import com.sparta.hh99_clonecoding.jwt.TokenProvider;
import com.sparta.hh99_clonecoding.model.Member;
import com.sparta.hh99_clonecoding.repository.MemberRepository;
import com.sparta.hh99_clonecoding.service.validator.Validator;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MemberService {
    private MemberRepository memberRepository;
    private Validator validator;
    private PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    public boolean signup(MemberRequestDto memberRequestDto) {
        validator.validateInput(memberRequestDto);

        if(memberRepository.existsByEmail(memberRequestDto.getEmail()))
            throw new PrivateException(Code.SIGNUP_EMAIL_DUPLICATE_ERROR);

        if (memberRepository.existsByUsername(memberRequestDto.getUsername()))
            throw new PrivateException(Code.SIGNUP_USERNAME_DUPLICATE_ERROR);

        Member member = new Member( memberRequestDto.getEmail(),
                                    memberRequestDto.getUsername(),
                                    passwordEncoder.encode(memberRequestDto.getPassword()));

        memberRepository.save(member);

        return true;
    }

    public HttpHeaders login(MemberRequestDto memberRequestDto) {

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(memberRequestDto.getEmail(), memberRequestDto.getPassword());

        //authenticate 메서드가 실행이 될 때 CustomUserDetailsService 에서 loadUserByUsername 메서드가 실행 됨
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        //위의 결과값을 가지고 SecurityContext 에 저장
        SecurityContextHolder.getContext().setAuthentication(authentication);

        //토큰 발행
        String jwt = tokenProvider.createToken(authentication);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);

        return httpHeaders;
    }
}
