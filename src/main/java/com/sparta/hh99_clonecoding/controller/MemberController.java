package com.sparta.hh99_clonecoding.controller;

import com.sparta.hh99_clonecoding.dto.MemberRequestDto;
import com.sparta.hh99_clonecoding.dto.TokenDto;
import com.sparta.hh99_clonecoding.exception.Code;
import com.sparta.hh99_clonecoding.exception.ExceptionResponseDto;
import com.sparta.hh99_clonecoding.jwt.JwtFilter;
import com.sparta.hh99_clonecoding.jwt.TokenProvider;
import com.sparta.hh99_clonecoding.service.MemberService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@Controller
@AllArgsConstructor
public class MemberController {
    private final MemberService memberService;
    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    @PostMapping("/user/signup")
    public ResponseEntity<ExceptionResponseDto> signup(@RequestBody MemberRequestDto memberRequestDto) {

        boolean rtval = memberService.signup(memberRequestDto);

        return rtval
                ? new ResponseEntity<>(new ExceptionResponseDto(Code.OK), HttpStatus.OK)
                : new ResponseEntity<>(new ExceptionResponseDto(Code.INTERNAL_SERVER_ERROR_PLZ_CHECK), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping("/user/login")
    public ResponseEntity<ExceptionResponseDto> authorize(@RequestBody MemberRequestDto memberRequestDto) {

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

        return new ResponseEntity<>(new ExceptionResponseDto(Code.OK), httpHeaders, HttpStatus.OK);
    }

}
