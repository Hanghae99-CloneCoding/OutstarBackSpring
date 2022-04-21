package com.sparta.hh99_clonecoding.controller;

import com.sparta.hh99_clonecoding.exception.Code;
import com.sparta.hh99_clonecoding.exception.ExceptionResponseDto;
import com.sparta.hh99_clonecoding.exception.PrivateException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/api/test/getERR")
    public void getPosts(){
        throw new PrivateException(Code.NOT_FOUND_POST);
    }

    @GetMapping("/api/test/ok")
    public ResponseEntity<ExceptionResponseDto> getPosts_ok(){
        return new ResponseEntity<>(new ExceptionResponseDto(Code.OK), HttpStatus.OK);
    }

    @GetMapping("/api/logintest")
    public ResponseEntity<ExceptionResponseDto> getLoginTest(){
        return new ResponseEntity<>(new ExceptionResponseDto(Code.OK,"로그인 테스트 성공"), HttpStatus.OK);
    }
}