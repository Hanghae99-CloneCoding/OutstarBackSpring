package com.sparta.hh99_clonecoding.controller;

import com.sparta.hh99_clonecoding.dto.commentDto.CommentRequestDto;
import com.sparta.hh99_clonecoding.dto.commentDto.CommentResponseDto;
import com.sparta.hh99_clonecoding.exception.Code;
import com.sparta.hh99_clonecoding.exception.ExceptionResponseDto;
import com.sparta.hh99_clonecoding.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/post/{postId}")
@RequiredArgsConstructor
public class CommentController {

    private CommentService commentService;

    @PostMapping("/comment")
    public ExceptionResponseDto postComment(@PathVariable Long postId, @RequestBody CommentRequestDto commentRequestDto) {
        CommentResponseDto commentResponseDto = commentService.postComment(postId, commentRequestDto);
        return new ExceptionResponseDto(Code.OK, commentResponseDto);
    }
//    @PutMapping("/post/{postId}")
//    public ExceptionResponseDto updatePost(@PathVariable Long postId,@RequestPart("desc") PostRequestDto postRequestDto) {
//        PostUpdateResponseDto postUpdateResponseDto = postService.updatePost(postId, postRequestDto);
//        return new ExceptionResponseDto(Code.OK, postUpdateResponseDto);
//    }
//
//    @GetMapping("/post/{postId}")
//    public ExceptionResponseDto getPost(@PathVariable Long postId) {
//        PostDetailDto postDetailDto = postService.getDetailPost(postId);
//        return new ExceptionResponseDto(Code.OK, postDetailDto);
//    }

//    @PutMapping("/comment/{commentId}")
//
//    @DeleteMapping("/comment/commentId}")
}
