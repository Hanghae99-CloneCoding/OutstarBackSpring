package com.sparta.hh99_clonecoding.controller;

import com.sparta.hh99_clonecoding.dto.postDto.*;
import com.sparta.hh99_clonecoding.exception.Code;
import com.sparta.hh99_clonecoding.exception.ExceptionResponseDto;
import com.sparta.hh99_clonecoding.exception.PrivateException;
import com.sparta.hh99_clonecoding.model.Img;
import com.sparta.hh99_clonecoding.service.PostService;
import com.sparta.hh99_clonecoding.service.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Slice;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final S3Service s3Service;

    // 게시글 전체 조회 이미지 다중 버전
    // @AuthenticationPrincipal UserDetails userDetails 넣기
    @GetMapping("/posts")
    public Map<String, List<PostGetAllResponseDto>> getAllPost(){ return postService.getAllPost();}

    // 메인 페이지 무한 스크롤
    @GetMapping("/postsScroll")
    public Map<String, List<PostGetAllResponseDto>> getPostSlice(
            @RequestParam(required=false) Integer page,
            @RequestParam(required=false) Integer size,
            @RequestParam(required=false) String sortBy ,
            @RequestParam(required=false) Boolean isAsc
    ) {
        if (isNotNullParam(page, size, sortBy, isAsc)) {
            page -= 1;
            return postService.getAllPostSlice(page, size, sortBy, isAsc);
        } else {
            throw new PrivateException(Code.PAGING_ERROR);
        }
    }

    private boolean isNotNullParam(Integer page, Integer size, String sortBy, Boolean isAsc) {
        return (page != null) && (size != null) && (sortBy != null) && (isAsc != null);
    }

    // 게시글 상세 조회
    // 유저 정보 추가
    @GetMapping("/post/{postId}")
    public ExceptionResponseDto getPost(@PathVariable Long postId) {
        PostDetailDto postDetailDto = postService.getDetailPost(postId);
        return new ExceptionResponseDto(Code.OK, postDetailDto);
    }

    @PostMapping("/post")
    public ExceptionResponseDto uploadPost(@RequestPart("desc") PostRequestDto postRequestDto,
                                      @RequestPart("images") List<MultipartFile> multipartFiles) {
        if (multipartFiles.equals(null)) {
            throw new PrivateException(Code.WRONG_INPUT_DESC);
        }
        List<String> imgPaths = s3Service.upload(multipartFiles);
        System.out.println("IMG 경로들 : " + imgPaths);
        postService.uploadPost(postRequestDto, imgPaths);
        return new ExceptionResponseDto(Code.OK);

    }

    // 게시글 수정
    // @AuthenticationPrincipal UserDetails userDetails 넣기
    @PutMapping("/post/{postId}")
    public ExceptionResponseDto updatePost(@PathVariable Long postId,@RequestPart("desc") PostRequestDto postRequestDto) {
        PostUpdateResponseDto postUpdateResponseDto = postService.updatePost(postId, postRequestDto);
        return new ExceptionResponseDto(Code.OK, postUpdateResponseDto);
    }

    // 게시글 삭제
    @DeleteMapping("/post/{postId}")
    public ExceptionResponseDto deletePost(@PathVariable Long postId) {
        postService.deletePost(postId);
        return new ExceptionResponseDto(Code.OK);
    }
}