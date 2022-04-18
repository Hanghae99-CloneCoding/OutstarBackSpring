package com.sparta.hh99_clonecoding.service;

import com.sparta.hh99_clonecoding.dto.commentDto.CommentResponseDto;
import com.sparta.hh99_clonecoding.dto.postDto.*;
import com.sparta.hh99_clonecoding.exception.Code;
import com.sparta.hh99_clonecoding.exception.PrivateException;
import com.sparta.hh99_clonecoding.model.Comment;
import com.sparta.hh99_clonecoding.model.Img;
import com.sparta.hh99_clonecoding.model.Post;
import com.sparta.hh99_clonecoding.repository.CommentRepository;
import com.sparta.hh99_clonecoding.repository.ImgRepository;
import com.sparta.hh99_clonecoding.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final ImgRepository imgRepository;
    private final S3Service s3Service;


    // 게시글 전체 조회
   // 유저 정보 넣기, 좋아요, 댓글 개수 카운트
    @Transactional
    public Map<String, List<PostGetResponseDto>> getAllPost() {
        Map<String, List<PostGetResponseDto>> listMap = new HashMap<>();
        List<PostGetResponseDto> list = new ArrayList<>();
        for (Post post : postRepository.findAllByOrderByCreatedAtDesc()) {
            PostGetResponseDto main = getPostOne(post.getId());
            list.add(main);
        }
        listMap.put("mainData", list);
        return listMap;
    }

    // 무한 스크롤
    public Map<String, List<PostGetResponseDto>> getAllPostSlice(int page, int size, String sortBy, Boolean isAsc) {
        Sort.Direction direction = isAsc ? Sort.Direction.ASC : Sort.Direction.DESC;
        Sort sort = Sort.by(direction, sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);

        Slice<Post> slicePostList = postRepository.findAllBy(pageable);

        Map<String, List<PostGetResponseDto>> listMap = new HashMap<>();
        List<PostGetResponseDto> list = new ArrayList<>();
        for (Post post : slicePostList) {
            PostGetResponseDto main = getPostOne(post.getId());
            list.add(main);
        }
        listMap.put("mainData", list);
        return listMap;
    }

    // 게시글 상세 조회
    public PostGetResponseDto getPostOne(Long postid) {
        Post post = postRepository.getById(postid);

        List<String> imgUrl = imgRepository.findAllByPost(post)
                .stream()
                .map(Img::getImgUrl)
                .collect(Collectors.toList());

        List<Comment> findCommentByPost = commentRepository.findAllByPost(post);
        List<CommentResponseDto> commentResponseDtoList = new ArrayList<>();
        for (Comment comment : findCommentByPost) {
            commentResponseDtoList.add(new CommentResponseDto(comment));
        }
        return new PostGetResponseDto(postid, post, imgUrl, commentResponseDtoList);
    }

    // 게시글 작성
    // 유저 정보 넣기
    @Transactional
    public void uploadPost(PostRequestDto res, List<String> imgPaths) {
        postBlankCheck(imgPaths);
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
    }

    private void postBlankCheck(List<String> imgPaths) {
        if(imgPaths == null || imgPaths.isEmpty()){ //.isEmpty()도 되는지 확인해보기
            throw new PrivateException(Code.WRONG_INPUT_IMAGE);
        }
    }

    // 유저 정보 추가
    @Transactional
    public PostUpdateResponseDto updatePost(Long postId, PostRequestDto postRequestDto) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new PrivateException(Code.NOT_FOUND_POST));

        // 유저 조회
//        Member member = memberRepository.findByUserName(username).orElseThow(
//                () -> new PrivateException(Code.NOT_FOUND_USER_NAME)
//        );

        // 본인의 게시글만 수정 가능
//        if (!post.getMember().equals(member)) {
//           throw new PrivateException(Code.WRONG_ACCESS_POST_UPDATE);

//        if(!StringUtils.hasText(postRequestDto.getDesc())){
//            throw new ApiRequestException("내용은 반드시 있어야합니다.");
//        }

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
//           throw new PrivateException(Code.WRONG_ACCESS_POST_DELETE);

        // 게시글의 이미지 삭제
        s3Service.delete(post.getImgList());
        // 해당 게시글 삭제
        postRepository.delete(post);
    }
}