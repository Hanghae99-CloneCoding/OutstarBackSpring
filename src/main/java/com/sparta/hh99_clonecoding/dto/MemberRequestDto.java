package com.sparta.hh99_clonecoding.dto;

import com.sparta.hh99_clonecoding.model.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberRequestDto {
    private String email;

    private String username; //nickname

    private String password;
}
