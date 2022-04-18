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

    NOT_FOUND_POST(HttpStatus.NOT_FOUND, "200", "해당 피드가 존재하지 않습니다"),
    WRONG_ACCESS_POST_UPDATE(HttpStatus.BAD_REQUEST, "201", "본인 피드만 수정할 수 있습니다"),
    WRONG_ACCESS_POST_DELETE(HttpStatus.BAD_REQUEST, "202", "본인 피드만 삭제할 수 있습니다"),
    WRONG_INPUT_DESC(HttpStatus.BAD_REQUEST, "203", "내용을 입력해주세요"),
    WRONG_INPUT_IMAGE(HttpStatus.BAD_REQUEST, "204", "이미지는 반드시 있어야 합니다"),


    IMAGE_UPLOAD_ERROR(HttpStatus.BAD_REQUEST, "210", "이미지 업로드에 실패했습니다"),
    WRONG_IMAGE_FORMAT(HttpStatus.BAD_REQUEST, "211", "지원하지 않는 파일 형식입니다"),

    PAGING_ERROR(HttpStatus.BAD_REQUEST, "220", "모든 요소가 필요합니다"),

    NOT_FOUND_COMMENT(HttpStatus.NOT_FOUND, "300", "해당 댓글이 존재하지 않습니다"),
    WRONG_ACCESS_COMMENT_UPDATE(HttpStatus.BAD_REQUEST, "301", "본인 댓글만 수정할 수 있습니다"),
    WRONG_ACCESS_COMMENT_DELETE(HttpStatus.BAD_REQUEST, "302", "본인 댓글만 삭제할 수 있습니다"),
    WRONG_INPUT_COMMENT(HttpStatus.BAD_REQUEST, "303", "댓글을 입력해주세요"),

    NOT_FOUND_MEMBER(HttpStatus.NOT_FOUND, "400", "해당 회원을 찾을 수 없습니다"),
    NOT_FOUND_JWT_TOKEN(HttpStatus.NOT_FOUND, "500", "JWT 이 존재하지 않습니다. 다시 확인해주세요."),

    NOT_FOUND_AUTHORIZATION_IN_SECURITY_CONTEXT(HttpStatus.INTERNAL_SERVER_ERROR, "998", "Security Context에 인증 정보가 없습니다."),
    INTERNAL_SERVER_ERROR_PLZ_CHECK(HttpStatus.INTERNAL_SERVER_ERROR, "999", "알수없는 서버 내부 에러 발생. 서버 담당자에게 알려주세요.");


    private final HttpStatus httpStatus;
    private final String code;
    private final String msg;

    Code(HttpStatus httpStatus, String code, String msg) {
        this.httpStatus = httpStatus;
        this.code = code;
        this.msg = msg;
    }
}