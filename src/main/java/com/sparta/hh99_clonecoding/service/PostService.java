package com.sparta.hh99_clonecoding.service;

import com.sparta.hh99_clonecoding.dto.postDto.PostGetAllResponseDto;
import com.sparta.hh99_clonecoding.dto.postDto.PostRequestDto;
import com.sparta.hh99_clonecoding.dto.postDto.PostResponseDto;
import com.sparta.hh99_clonecoding.dto.postDto.PostUpdateResponseDto;
import com.sparta.hh99_clonecoding.exception.Code;
import com.sparta.hh99_clonecoding.exception.PrivateException;
import com.sparta.hh99_clonecoding.model.Post;
import com.sparta.hh99_clonecoding.repository.PostRepository;
import com.sparta.hh99_clonecoding.util.S3Uploader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final S3Uploader s3Uploader;

//    // 게시글 전체 조회
//    // 유저 정보 넣기, 좋아요, 댓글 개수 카운트
    @Transactional
    public List<PostGetAllResponseDto> getAllPost() {
        List<Post> postList = postRepository.findAllByOrderByCreatedAtDesc();
        List<PostGetAllResponseDto> responseDtos = new ArrayList<>();
        for (Post post : postList) {
            responseDtos.add(new PostGetAllResponseDto(post));
        }
        return responseDtos;
    }

    // 게시글 작성
    // 유저 정보 추가 해야함
    @Transactional
    public String uploadPost(PostResponseDto postResponseDto) throws IOException {
        // 유저 조회
////        User user = userRepository.findByUserName(username).orElseThow(
////                () -> new PrivateException(Code.NOT_FOUND_USER_NAME)
////        );

        String desc = postResponseDto.getDesc();

        // 이미지 업로드
        String imageUrl = s3Uploader.upload(postResponseDto.getImageUrl());

        // post 저장
        Post post = new Post(desc, imageUrl);
        postRepository.save(post);
        return imageUrl;
    }

    // 게시글 작성 2차 방법
//    @Transactional
//    public PostResponseDto uploadPost(String desc) {
//        Post post = new Post(desc);
//        return new PostResponseDto(postRepository.save(post));
//    }

    // 게시글 수정
    // 유저 정보 추가 필요
    @Transactional
    public PostUpdateResponseDto updatePost(Long id, PostRequestDto requestDto) {
        // 해당 게시글 존재 여부 확인
        Post post = postRepository.findById(id).orElseThrow(
                () -> new PrivateException(Code.NOT_FOUND_POST));

        // 유저 조회
////        User user = userRepository.findByUserName(username).orElseThow(
////                () -> new PrivateException(Code.NOT_FOUND_USER_NAME)
////        );
//
//        // 본인의 게시글만 수정 가능
////        if (!post.getUser().equals(user)) {
////            throw new PrivateException(Code.WRONG_USER_NAME);
////        }

        String desc = requestDto.getDesc();

        post.updatePost(desc);

        return new PostUpdateResponseDto(
                post.getId(),
//                post.getImage(),
//                post.getUser().getUsername(),
                post.getDesc()
//                post.getCreatedAt(),
//                post.getModifiedAt()
        );
    }

    // 게시글 수정 2차 방법
//    @Transactional
//    public PostResponseDto uploadPost(Long id, PostRequestDto requestDto) {
//        Post post = postRepository.findById(id).orElseThrow(
//                () -> new PrivateException(Code.NOT_FOUND_POST));
//
//        // 유저 조회
////        User user = userRepository.findByUserName(username).orElseThow(
////                () -> new PrivateException(Code.NOT_FOUND_USER_NAME)
////        );
//
//        // 본인의 게시글만 수정 가능
////        if (!post.getUser().equals(user)) {
////            throw new PrivateException(Code.WRONG_USER_NAME);
////        }
//        post.update(requestDto);
//        return new PostResponseDto(post);
//
//    }

    // 게시글 삭제
    // 유저 정보 추가 필요
    @Transactional
    public void deletePost(Long id) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new PrivateException(Code.NOT_FOUND_POST)
        );

        // 유저 조회
////        User user = userRepository.findByUserName(username).orElseThow(
////                () -> new PrivateException(Code.NOT_FOUND_USER_NAME)
////        );
//
//        // 본인의 게시글만 삭제 가능
////        if (!post.getUser().equals(user)) {
////            throw new PrivateException(Code.WRONG_USER_NAME_DELETE);
////        }

        // 해당 게시글 삭제
        postRepository.delete(post);

        // 게시글의 이미지 삭제
        s3Uploader.delete(post.getImageUrl());
    }

    public String formatter(LocalDateTime localDateTime) {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd").format(localDateTime);
    }
}