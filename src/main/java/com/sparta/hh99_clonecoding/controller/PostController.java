package com.sparta.hh99_clonecoding.controller;

import com.sparta.hh99_clonecoding.dto.postDto.PostGetAllResponseDto;
import com.sparta.hh99_clonecoding.dto.postDto.PostRequestDto;
import com.sparta.hh99_clonecoding.dto.postDto.PostResponseDto;
import com.sparta.hh99_clonecoding.dto.postDto.PostUpdateResponseDto;
import com.sparta.hh99_clonecoding.exception.Code;
import com.sparta.hh99_clonecoding.exception.ExceptionResponseDto;
import com.sparta.hh99_clonecoding.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    // 게시글 전체 조회
    // @AuthenticationPrincipal UserDetails userDetails 넣기
    @GetMapping("/posts")
    public List<PostGetAllResponseDto> getAllPost() {
        return postService.getAllPost();
    }

    // 무한 스크롤 api 따로 추가해야하나..?


    // 게시글 작성
    // @AuthenticationPrincipal UserDetails userDetails 넣기
    // 이미지 추가 필요
    @PostMapping("/post")
    public ExceptionResponseDto upload(@RequestBody PostResponseDto responseDto) {
        postService.uploadPost(responseDto);

        return new ExceptionResponseDto(Code.OK);
    }

    // 게시글 작성 2
    // @AuthenticationPrincipal UserDetails userDetails 넣기
    // 이미지 추가 필요
//    @PostMapping("/post")
//    public PostResponseDto uploadPost(String desc) {
//        return postService.uploadPost(desc);
//    }

    // 게시글 수정
    //    // @AuthenticationPrincipal UserDetails userDetails 넣기
    @PutMapping("/post/{id}")
    public ExceptionResponseDto updatePost(@PathVariable Long id, @RequestBody PostRequestDto requestDto) {
        PostUpdateResponseDto postUpdateResponseDto = postService.updatePost(id, requestDto);
        return new ExceptionResponseDto(Code.OK,postUpdateResponseDto);
    }

//    // 게시글 수정 2
//    // @AuthenticationPrincipal UserDetails userDetails 넣기
//    @PutMapping("/post/{id}")
//    public PostResponseDto updatePost(@PathVariable Long id, @RequestBody PostRequestDto requestDto) {
//        return postService.uploadPost(id, requestDto);
//    }

    // 게시글 삭제
    @DeleteMapping("/post/{id}")
    public ExceptionResponseDto deletePost(@PathVariable Long id) {
        postService.deletePost(id);
        return new ExceptionResponseDto(Code.OK);
    }

}