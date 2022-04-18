package com.sparta.hh99_clonecoding.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum Code {
    OK(HttpStatus.OK, "0", "정상"),

    PASSWORD_FORM_ERROR(HttpStatus.BAD_REQUEST, "10", "비밀번호 구성 오류"),
    LOGIN_FAIL(HttpStatus.BAD_REQUEST, "11", "로그인 실패"),

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

    NOT_FOUND_USER_NAME(HttpStatus.NOT_FOUND, "400", "해당 유정 정보를 찾을 수 없습니다");

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
