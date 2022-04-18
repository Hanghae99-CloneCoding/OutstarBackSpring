package com.sparta.hh99_clonecoding.controller;

import com.sparta.hh99_clonecoding.dto.commentDto.CommentRequestDto;
import com.sparta.hh99_clonecoding.dto.commentDto.CommentResponseDto;
import com.sparta.hh99_clonecoding.exception.Code;
import com.sparta.hh99_clonecoding.exception.ExceptionResponseDto;
import com.sparta.hh99_clonecoding.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    // Comment 작성
    // 유저 정보 추가
    @PostMapping("/post/{postId}/comment")
    public ExceptionResponseDto postComment(@PathVariable(name="postId") Long postId, @RequestBody CommentRequestDto commentRequestDto) {
        CommentResponseDto commentResponseDto = commentService.postComment(postId, commentRequestDto);
        return new ExceptionResponseDto(Code.OK, commentResponseDto);
    }

    // Comment 수정
    // 유저 정보 추가
    @PutMapping("/comment/{commentId}")
    public ExceptionResponseDto updateComment(@PathVariable Long commentId, @RequestBody CommentRequestDto commentRequestDto) {
        CommentResponseDto commentResponseDto = commentService.updateComment(commentId, commentRequestDto);
        return new ExceptionResponseDto(Code.OK, commentResponseDto);
    }

    // Comment 삭제
    // 유저 정보 추가
    @DeleteMapping("/comment/{commentId}")
    public ExceptionResponseDto deleteComment(@PathVariable Long commentId) {
        commentService.deleteComment(commentId);
        return new ExceptionResponseDto(Code.OK);
    }
}