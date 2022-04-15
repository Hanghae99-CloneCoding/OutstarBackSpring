package com.sparta.hh99_clonecoding.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ExceptionResponseDto {
    private String code;
    private String msg;

    public ExceptionResponseDto(Code code) {
        this.code = code.getCode();
        this.msg = code.getMsg();
    }
}
