package com.sparta.hh99_clonecoding.dto.postDto;

import com.sparta.hh99_clonecoding.dto.commentDto.CommentResponseDto;
import com.sparta.hh99_clonecoding.model.Post;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Getter
public class PostGetResponseDto {
    // 좋아요 수 추가
    private Long postId;
    private String username;
    private String content;
    private List<String> imgUrl;
    private String modifiedAt;
    private List<CommentResponseDto> commentList;

    public PostGetResponseDto(Long postId, Post post, List<String> imgUrl, List<CommentResponseDto> commentList) {
        this.postId = postId;
        this.username = post.getMember().getUsername();
        this.content = post.getContent();
        this.imgUrl = imgUrl;
        this.modifiedAt = formatter(post.getModifiedAt());
        this.commentList = commentList;
    }

    public String formatter(LocalDateTime localDateTime) {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm").format(localDateTime);
    }
}