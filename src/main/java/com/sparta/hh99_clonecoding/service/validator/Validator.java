package com.sparta.hh99_clonecoding.service.validator;

import com.sparta.hh99_clonecoding.dto.MemberRequestDto;
import com.sparta.hh99_clonecoding.exception.Code;
import com.sparta.hh99_clonecoding.exception.PrivateException;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class Validator {
    public void validateInput(MemberRequestDto memberRequestDto) {
        if (!isValidEmail(memberRequestDto.getEmail())) {
            throw new PrivateException(Code.SIGNUP_EMAIL_FORM_ERROR);
        }

        if (!isValidUsername(memberRequestDto.getUsername())) {
            throw new PrivateException(Code.SIGNUP_USERNAME_FORM_ERROR);
        }
    }

    private boolean isValidEmail(String email) {
        //1. email : email 형식
        String pattern = "\\w+@\\w+\\.\\w+(\\.\\w+)?";

        return Pattern.matches(pattern, email);
    }

    private boolean isValidUsername(String username) {
        //2. username : 영어(소대문자)+숫자/3~15
        String pattern = "^[A-Za-z0-9]{3,15}$";

        return Pattern.matches(pattern, username);
    }
}