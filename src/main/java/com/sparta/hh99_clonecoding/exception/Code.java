package com.sparta.hh99_clonecoding.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum Code {
    OK(HttpStatus.OK, "0", "정상"),

    PASSWORD_FORM_ERROR(HttpStatus.BAD_REQUEST, "10", "비밀번호 구성 오류"),
    LOGIN_FAIL(HttpStatus.BAD_REQUEST, "11", "로그인 실패"),

    POST_POST_ERROR(HttpStatus.BAD_REQUEST, "20", "게시글 생성 실패"),
    POST_PUT_ERROR(HttpStatus.BAD_REQUEST, "21", "게시글 수정 실패"),
    POST_DELETE_ERROR(HttpStatus.BAD_REQUEST, "22", "게시글 삭제 실패"),
    POST_GET_ERROR(HttpStatus.BAD_REQUEST, "23", "게시글 조회 실패"),

    COMMENT_POST_ERROR(HttpStatus.BAD_REQUEST, "30", "댓글 작성 실패"),
    COMMENT_PUT_ERROR(HttpStatus.BAD_REQUEST, "31", "댓글 수정 실패"),
    COMMENT_DELETE_ERROR(HttpStatus.BAD_REQUEST, "32", "댓글 삭제 실패");

    // NOT_FOUND_USER_ID(HttpStatus.NOT_FOUND, "12", "해당 USER ID는 존재하지 않습니다."),
    // NOT_FOUND_USER_ID(HttpStatus.NOT_FOUND, "13", "PASSWORD 가 틀렸습니다."),
    // NOT_FOUND_POST_ID(HttpStatus.NOT_FOUND, "24", "해당 POST ID는 존재하지 않습니다."),
    // NOT_FOUND_COMMENT_ID(HttpStatus.NOT_FOUND, "25", "해당 COMMENT ID는 존재하지 않습니다."),


    private final HttpStatus httpStatus;
    private final String code;
    private final String msg;

    Code(HttpStatus httpStatus, String code, String msg) {
        this.httpStatus = httpStatus;
        this.code = code;
        this.msg = msg;
    }
}
