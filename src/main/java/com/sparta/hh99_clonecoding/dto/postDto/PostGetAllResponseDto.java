package com.sparta.hh99_clonecoding.dto.postDto;

import com.sparta.hh99_clonecoding.model.Post;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Getter
public class PostGetAllResponseDto {
    // 유저 정보 추가
    // 좋아요 수 추가
    // 댓글 추가
    private Long postId;
    private String desc;
    private List<String> imgUrl;
    private String modifiedAt;

    public PostGetAllResponseDto(Long postId, Post post, List<String> imgUrl) {
        this.postId = postId;
        this.desc = post.getDesc();
        this.imgUrl = imgUrl;
        this.modifiedAt = formatter(post.getModifiedAt());
    }

    public String formatter(LocalDateTime localDateTime) {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm").format(localDateTime);
    }
}