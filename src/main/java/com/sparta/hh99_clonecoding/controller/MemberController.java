package com.sparta.hh99_clonecoding.controller;

import com.sparta.hh99_clonecoding.dto.MemberRequestDto;
import com.sparta.hh99_clonecoding.exception.Code;
import com.sparta.hh99_clonecoding.exception.ExceptionResponseDto;
import com.sparta.hh99_clonecoding.service.MemberService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@AllArgsConstructor
public class MemberController {
    private MemberService memberService;

    @PostMapping("/user/signup")
    public ResponseEntity<ExceptionResponseDto> signup(@RequestBody MemberRequestDto memberRequestDto) {

        boolean rtval = memberService.signup(memberRequestDto);

        return rtval
                ? new ResponseEntity<>(new ExceptionResponseDto(Code.OK), HttpStatus.OK)
                : new ResponseEntity<>(new ExceptionResponseDto(Code.INTERNAL_SERVER_ERROR_PLZ_CHECK), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
