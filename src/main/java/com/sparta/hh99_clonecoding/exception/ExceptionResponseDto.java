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
    private Object data;

    public ExceptionResponseDto(Code code) {
        this.code = code.getCode();
        this.msg = code.getMsg();
    }

    public ExceptionResponseDto(Code code, Object data){
        this.code = code.getCode();
        this.msg = code.getMsg();
        this.data = data;
    }
}