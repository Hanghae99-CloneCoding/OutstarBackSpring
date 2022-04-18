package com.sparta.hh99_clonecoding.service;

import com.sparta.hh99_clonecoding.dto.commentDto.CommentRequestDto;
import com.sparta.hh99_clonecoding.dto.commentDto.CommentResponseDto;
import com.sparta.hh99_clonecoding.exception.Code;
import com.sparta.hh99_clonecoding.exception.PrivateException;
import com.sparta.hh99_clonecoding.model.Comment;
import com.sparta.hh99_clonecoding.model.Post;
import com.sparta.hh99_clonecoding.repository.CommentRepository;
import com.sparta.hh99_clonecoding.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    // 댓글 작성
    // 유저 정보 추가
    public CommentResponseDto postComment( Long postId, CommentRequestDto commentRequestDto) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new PrivateException(Code.NOT_FOUND_POST));

        Comment comment = new Comment(post, commentRequestDto);
        comment = commentRepository.save(comment);
        return new CommentResponseDto(comment);
    }

    // 댓글 수정
    // 유저 정보 추가
    public CommentResponseDto updateComment(Long postId, Long commentId, CommentRequestDto commentRequestDto) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new PrivateException(Code.NOT_FOUND_POST));

        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new PrivateException(Code.NOT_FOUND_COMMENT));

//        if (!comment.getMember().equals(member)) {
//            throw new PrivateException(Code.WRONG_ACCESS_COMMENT_UPDATE);
//        }

        comment.update(post, commentRequestDto);
        return new CommentResponseDto(comment);
    }

    // 댓글 삭제
    public void deleteComment(Long commentId) {
//        Comment comment = commentRepository.findById(commentId).orElseThrow(
//                () -> new PrivateException(Code.NOT_FOUND_COMMENT));
//
//        if (!comment.getMember().equals(member)) {
//            throw new PrivateException(Code.WRONG_ACCESS_COMMENT_DELETE);
//        }
        commentRepository.deleteById(commentId);
    }
}