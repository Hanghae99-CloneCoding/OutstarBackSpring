package com.sparta.hh99_clonecoding.controller;

import com.sparta.hh99_clonecoding.dto.postDto.PostGetResponseDto;
import com.sparta.hh99_clonecoding.dto.postDto.PostRequestDto;
import com.sparta.hh99_clonecoding.dto.postDto.PostUpdateResponseDto;
import com.sparta.hh99_clonecoding.exception.Code;
import com.sparta.hh99_clonecoding.exception.ExceptionResponseDto;
import com.sparta.hh99_clonecoding.exception.PrivateException;
import com.sparta.hh99_clonecoding.service.PostService;
import com.sparta.hh99_clonecoding.service.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final S3Service s3Service;

    // 게시글 전체 조회
    @GetMapping("/posts")
    public Map<String, List<PostGetResponseDto>> getAllPost(){ return postService.getAllPost();}

    // 메인 페이지 무한 스크롤
    @GetMapping("/postsScroll")
    public Map<String, List<PostGetResponseDto>> getPostSlice(
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
    @GetMapping("/post/{postId}")
    public ExceptionResponseDto getPost(@PathVariable Long postId) {
        PostGetResponseDto postGetResponseDto = postService.getPostOne(postId);
        return new ExceptionResponseDto(Code.OK, postGetResponseDto);
    }

    // 게시글 작성
    @PostMapping("/post")
    public ExceptionResponseDto uploadPost(@RequestPart("content") PostRequestDto postRequestDto,
                                           @RequestPart("imgUrl") List<MultipartFile> multipartFiles) {
        if (multipartFiles == null) {
            throw new PrivateException(Code.WRONG_INPUT_CONTENT);
        }
        List<String> imgPaths = s3Service.upload(multipartFiles);
        System.out.println("IMG 경로들 : " + imgPaths);
        postService.uploadPost(postRequestDto, imgPaths);
        return new ExceptionResponseDto(Code.OK);

    }

    // 게시글 수정
    @PutMapping("/post/{postId}")
    public ExceptionResponseDto updatePost(@PathVariable Long postId,@RequestPart("content") PostRequestDto postRequestDto) {
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