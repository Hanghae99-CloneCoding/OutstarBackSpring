package com.sparta.hh99_clonecoding.service;

import com.sparta.hh99_clonecoding.dto.MemberRequestDto;
import com.sparta.hh99_clonecoding.exception.Code;
import com.sparta.hh99_clonecoding.exception.PrivateException;
import com.sparta.hh99_clonecoding.model.Member;
import com.sparta.hh99_clonecoding.service.validator.Validator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MemberService {
    private MemberRepository memberRepository;
    private Validator validator;

    public boolean signup(MemberRequestDto memberRequestDto) {
        validator.validateInput(memberRequestDto);

        if(memberRepository.existsByEmail(memberRequestDto.getEmail()))
            throw new PrivateException(Code.SIGNUP_EMAIL_DUPLICATE_ERROR);

        if (memberRepository.existsByUsername(memberRequestDto.getUsername()))
            throw new PrivateException(Code.SIGNUP_USERNAME_DUPLICATE_ERROR);

        memberRepository.save(new Member(memberRequestDto));

        return true;
    }
}
