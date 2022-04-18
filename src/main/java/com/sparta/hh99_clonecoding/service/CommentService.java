package com.sparta.hh99_clonecoding.service;

import com.sparta.hh99_clonecoding.dto.commentDto.CommentRequestDto;
import com.sparta.hh99_clonecoding.dto.commentDto.CommentResponseDto;
import com.sparta.hh99_clonecoding.exception.Code;
import com.sparta.hh99_clonecoding.exception.PrivateException;
import com.sparta.hh99_clonecoding.model.Comment;
import com.sparta.hh99_clonecoding.model.Member;
import com.sparta.hh99_clonecoding.model.Post;
import com.sparta.hh99_clonecoding.repository.CommentRepository;
import com.sparta.hh99_clonecoding.repository.MemberRepository;
import com.sparta.hh99_clonecoding.repository.PostRepository;
import com.sparta.hh99_clonecoding.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final MemberRepository memberRepository;

    // 댓글 작성
    @Transactional
    public CommentResponseDto postComment( Long postId, CommentRequestDto commentRequestDto) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new PrivateException(Code.NOT_FOUND_POST));

        System.out.println("로그인한 username : " + SecurityUtil.getCurrentUsername());

        String username = SecurityUtil.getCurrentUsername();

        Member member = memberRepository.findMemberByUsername(username).orElseThrow(
                () -> new PrivateException(Code.NOT_FOUND_MEMBER)
        );
        Comment comment = new Comment(post, commentRequestDto, member);
        comment = commentRepository.save(comment);
        return new CommentResponseDto(comment);
    }

    // 댓글 수정
    // 유저 정보 추가
    @Transactional
    public CommentResponseDto updateComment(Long commentId, CommentRequestDto commentRequestDto) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new PrivateException(Code.NOT_FOUND_COMMENT));

        System.out.println("로그인한 username : " + SecurityUtil.getCurrentUsername());

        String username = SecurityUtil.getCurrentUsername();

        Member member = memberRepository.findMemberByUsername(username).orElseThrow(
                () -> new PrivateException(Code.NOT_FOUND_MEMBER)
        );

        // 본인의 댓글만 수정 가능
        if (!comment.getMember().equals(member)) {
            throw new PrivateException(Code.WRONG_ACCESS_COMMENT_UPDATE);
        }
        comment.update(commentRequestDto);
        return new CommentResponseDto(comment);
    }

    // 댓글 삭제
    @Transactional
    public void deleteComment(Long commentId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new PrivateException(Code.NOT_FOUND_COMMENT));

        System.out.println("로그인한 username : " + SecurityUtil.getCurrentUsername());

        String username = SecurityUtil.getCurrentUsername();

        Member member = memberRepository.findMemberByUsername(username).orElseThrow(
                () -> new PrivateException(Code.NOT_FOUND_MEMBER)
        );

        // 본인의 댓글만 삭제 가능
        if (!comment.getMember().equals(member)) {
            throw new PrivateException(Code.WRONG_ACCESS_COMMENT_DELETE);
        }
        commentRepository.deleteById(commentId);
    }
}