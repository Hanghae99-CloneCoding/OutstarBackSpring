package com.sparta.hh99_clonecoding.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum Code {
    OK(HttpStatus.OK, "0", "정상"),

    SIGNUP_EMAIL_FORM_ERROR(HttpStatus.BAD_REQUEST, "100", "email 형식을 맞춰주세요"),
    SIGNUP_EMAIL_DUPLICATE_ERROR(HttpStatus.BAD_REQUEST, "101", "email 이 중복됩니다."),
    SIGNUP_USERNAME_FORM_ERROR(HttpStatus.BAD_REQUEST, "102", "username 형식을 맞춰주세요"),
    SIGNUP_USERNAME_DUPLICATE_ERROR(HttpStatus.BAD_REQUEST, "103", "username 이 중복됩니다."),
    
    LOGIN_EMAIL_FAIL(HttpStatus.NOT_FOUND, "110", "해당 하는 email 이 없습니다"),
    LOGIN_PASSWORD_FAIL(HttpStatus.BAD_REQUEST, "111", "Password가 틀렸습니다."),
    LOGIN_WRONG_SIGNATURE_JWT_TOKEN(HttpStatus.BAD_REQUEST, "112", "잘못된 JWT 서명입니다."),
    LOGIN_EXPIRED_JWT_TOKEN(HttpStatus.BAD_REQUEST, "113", "만료된 JWT 토큰입니다."),
    LOGIN_NOT_SUPPORTED_JWT_TOKEN(HttpStatus.BAD_REQUEST, "114", "지원되지 않는 JWT 토큰입니다."),
    LOGIN_WRONG_FORM_JWT_TOKEN(HttpStatus.BAD_REQUEST, "115", "JWT 토큰이 잘못되었습니다."),

    POST_POST_ERROR(HttpStatus.BAD_REQUEST, "20", "게시글 생성 실패"),
    POST_PUT_ERROR(HttpStatus.BAD_REQUEST, "21", "게시글 수정 실패"),
    POST_DELETE_ERROR(HttpStatus.BAD_REQUEST, "22", "게시글 삭제 실패"),
    POST_GET_ERROR(HttpStatus.BAD_REQUEST, "23", "게시글 조회 실패"),

    COMMENT_POST_ERROR(HttpStatus.BAD_REQUEST, "30", "댓글 작성 실패"),
    COMMENT_PUT_ERROR(HttpStatus.BAD_REQUEST, "31", "댓글 수정 실패"),
    COMMENT_DELETE_ERROR(HttpStatus.BAD_REQUEST, "32", "댓글 삭제 실패"),


    INTERNAL_SERVER_ERROR_PLZ_CHECK(HttpStatus.INTERNAL_SERVER_ERROR, "999", "알수없는 서버 내부 에러 발생. 서버 담당자에게 알려주세요."),;

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
