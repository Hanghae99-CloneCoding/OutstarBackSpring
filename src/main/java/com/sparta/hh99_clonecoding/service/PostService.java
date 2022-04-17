package com.sparta.hh99_clonecoding.service;

import com.sparta.hh99_clonecoding.dto.postDto.PostGetAllResponseDto;
import com.sparta.hh99_clonecoding.dto.postDto.PostRequestDto;
import com.sparta.hh99_clonecoding.dto.postDto.PostResponseDto;
import com.sparta.hh99_clonecoding.dto.postDto.PostUpdateResponseDto;
import com.sparta.hh99_clonecoding.exception.Code;
import com.sparta.hh99_clonecoding.exception.PrivateException;
import com.sparta.hh99_clonecoding.model.Img;
import com.sparta.hh99_clonecoding.model.Post;
import com.sparta.hh99_clonecoding.repository.ImgRepository;
import com.sparta.hh99_clonecoding.repository.PostRepository;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final ImgRepository imgRepository;
    private final S3Service s3Service;


    // 게시글 전체 조회
   // 유저 정보 넣기, 좋아요, 댓글 개수 카운트
    @Transactional
    public Map<String, List<PostGetAllResponseDto>> getAllPost() {
        Map<String, List<PostGetAllResponseDto>> listMap = new HashMap<>();
        List<PostGetAllResponseDto> list = new ArrayList<>();
        for (Post post : postRepository.findAllByOrderByCreatedAtDesc()) {
            PostGetAllResponseDto main = createMain(post.getId());
            list.add(main);
        }
        listMap.put("mainData", list);
        return listMap;
    }

    private PostGetAllResponseDto createMain(Long id) {

        Post post = postRepository.getById(id);

        imgRepository.findAllByPost(post).forEach(post::addImgList);
        List<String> imgUrl = imgRepository.findAllByPost(post)
                .stream()
                .map(Img::getImgUrl)
                .collect(Collectors.toList());
        return new PostGetAllResponseDto(id, post, imgUrl);
    }

    // 게시글 작성
    // 유저 정보 넣기
    @Transactional
    public void uploadPost(PostRequestDto res, List<String> imgPaths) {
        postBlankCheck(imgPaths);
        System.out.println("이미지 있니" + imgPaths);

        // 유저 조회
//        User user = userRepository.findByUserName(username).orElseThow(
//                () -> new PrivateException(Code.NOT_FOUND_USER_NAME)
//        );

        String desc = res.getDesc();

        Post post = new Post(desc);
        postRepository.save(post);

        List<String> imgList = new ArrayList<>();
        for (String imgUrl : imgPaths) {
            Img img = new Img(imgUrl, post);
            imgRepository.save(img);
            imgList.add(img.getImgUrl());
        }
        System.out.println("이미지 있니2" + imgList);
        new PostResponseDto(desc, imgList);
    }

    private void postBlankCheck(List<String> imgPaths) {
        if(imgPaths == null || imgPaths.isEmpty()){ //.isEmpty()도 되는지 확인해보기
            throw new PrivateException(Code.WRONG_INPUT_IMAGE);
        }
    }


    // 게시글 수정 - 글만
    // 유저 정보 추가
    @Transactional
    public PostUpdateResponseDto updatePost(Long postId, PostRequestDto postRequestDto) {
        // 해당 게시글 존재 여부 확인
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new PrivateException(Code.NOT_FOUND_POST));

        // 유저 조회
//        Member member = memberRepository.findByUserName(username).orElseThow(
//                () -> new PrivateException(Code.NOT_FOUND_USER_NAME)
//        );

        // 본인의 게시글만 수정 가능
//        if (!post.getMember().equals(member)) {
//           throw new PrivateException(Code.WRONG_USER_NAME);

        post.updatePost(postRequestDto);
        return new PostUpdateResponseDto(postId, post);
    }

    // 게시글 삭제
    // 유저 정보 추가 필요
    @Transactional
    public void deletePost(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new PrivateException(Code.NOT_FOUND_POST)
        );

        // 유저 조회
//        Member member = memberRepository.findByUserName(username).orElseThow(
//                () -> new PrivateException(Code.NOT_FOUND_USER_NAME)
//        );

        // 본인의 게시글만 삭제 가능
//        if (!post.getMember().equals(member)) {
//           throw new PrivateException(Code.WRONG_USER_NAME_DELETE);

        // 게시글의 이미지 삭제
        s3Service.delete(post.getImgList());
        // 해당 게시글 삭제
        postRepository.delete(post);
    }
}