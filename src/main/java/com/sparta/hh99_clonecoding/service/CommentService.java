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

@Service
@RequiredArgsConstructor
public class CommentService {

    private PostRepository postRepository;
    private CommentRepository commentRepository;

    // 댓글 작성
    public CommentResponseDto postComment(Long postId, CommentRequestDto commentRequestDto) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new PrivateException(Code.NOT_FOUND_POST));

        Comment comment = new Comment(post, commentRequestDto);
        comment = commentRepository.save(comment);
        return new CommentResponseDto(comment);
    }
}
