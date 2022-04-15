package com.sparta.hh99_clonecoding.service;

import com.sparta.hh99_clonecoding.dto.postDto.PostGetAllResponseDto;
import com.sparta.hh99_clonecoding.dto.postDto.PostRequestDto;
import com.sparta.hh99_clonecoding.dto.postDto.PostResponseDto;
import com.sparta.hh99_clonecoding.dto.postDto.PostUpdateResponseDto;
import com.sparta.hh99_clonecoding.exception.Code;
import com.sparta.hh99_clonecoding.exception.PrivateException;
import com.sparta.hh99_clonecoding.model.Post;
import com.sparta.hh99_clonecoding.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

//    // 게시글 전체 조회
//    // 유저 정보 넣기, 좋아요, 댓글 개수 카운트
    public List<PostGetAllResponseDto> getAllPost() {
        List<Post> postList = postRepository.findAllByOrderByCreatedAtDesc();
        List<PostGetAllResponseDto> responseDtos = new ArrayList<>();
        for (Post post : postList) {
            responseDtos.add(new PostGetAllResponseDto(post));
        }
        return responseDtos;
    }

    // 게시글 작성
    // 유저 정보, 이미지 추가
    public String uploadPost(PostResponseDto responseDto) {
        String desc = responseDto.getDesc();
//        if (desc == null) {
//            throw new PrivateException(Code.WRONG_INPUT);
//        }
        Post post = new Post(desc);
        postRepository.save(post);
        return desc;
    }

    // 게시글 수정
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

    public void deletePost(Long id) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new PrivateException(Code.NOT_FOUND_POST)
        );
        postRepository.delete(post);
    }

    // 게시글 작성 2차 방법
//    @Transactional
//    public PostResponseDto uploadPost(String desc) {
//        Post post = new Post(desc);
//        return new PostResponseDto(postRepository.save(post));
//    }

    // 게시글 수정
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

    public String formatter(LocalDateTime localDateTime) {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd").format(localDateTime);
    }
}