package com.sparta.hh99_clonecoding.dto.postDto;

import com.sparta.hh99_clonecoding.model.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@AllArgsConstructor
public class PostUpdateResponseDto {
    private Long postId;
    private String content;
    private String modifiedAt;

    public PostUpdateResponseDto(Long postId, Post post) {
        this.postId = postId;
        this.content = post.getContent();
        this.modifiedAt = formatter(post.getModifiedAt());
    }

    public String formatter(LocalDateTime localDateTime) {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm").format(localDateTime);
    }
}