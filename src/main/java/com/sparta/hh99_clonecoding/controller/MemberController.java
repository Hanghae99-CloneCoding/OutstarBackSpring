package com.sparta.hh99_clonecoding.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sparta.hh99_clonecoding.dto.MemberRequestDto;
import com.sparta.hh99_clonecoding.exception.Code;
import com.sparta.hh99_clonecoding.exception.ExceptionResponseDto;
import com.sparta.hh99_clonecoding.service.KakaoUserService;
import com.sparta.hh99_clonecoding.service.MemberService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
public class MemberController {
    private final MemberService memberService;
    private final KakaoUserService kakaoUserService;

    @PostMapping("/user/signup")
    public ResponseEntity<ExceptionResponseDto> signup(@RequestBody MemberRequestDto memberRequestDto) {

        boolean rtval = memberService.signup(memberRequestDto);

        return rtval
                ? new ResponseEntity<>(new ExceptionResponseDto(Code.OK), HttpStatus.OK)
                : new ResponseEntity<>(new ExceptionResponseDto(Code.INTERNAL_SERVER_ERROR_PLZ_CHECK), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping("/user/login")
    public ResponseEntity<ExceptionResponseDto> login(@RequestBody MemberRequestDto memberRequestDto) {

        HttpHeaders httpHeaderWithJWT = memberService.login(memberRequestDto);

        return new ResponseEntity<>(new ExceptionResponseDto(Code.OK), httpHeaderWithJWT, HttpStatus.OK);
    }

    @GetMapping("/user/kakao/callback")
    public ResponseEntity<ExceptionResponseDto> kakaoLogin(@RequestParam(value = "code") String authorityCode) throws JsonProcessingException {
        HttpHeaders httpHeaderWithJWT =  kakaoUserService.kakaoLogin(authorityCode);

        return new ResponseEntity<>(new ExceptionResponseDto(Code.OK), httpHeaderWithJWT, HttpStatus.OK);
    }
}