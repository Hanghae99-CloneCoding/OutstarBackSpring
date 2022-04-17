package com.sparta.hh99_clonecoding.dto.postDto;

import com.sparta.hh99_clonecoding.model.Post;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Getter
public class PostDetailDto {
    private Long postId;
    private String desc;
    private List<String> imgUrl;
    private String modifiedAt;

    public PostDetailDto(Long postId, Post post, List<String> imgUrl) {
        this.postId = postId;
        this.desc = post.getDesc();
        this.imgUrl = imgUrl;
        this.modifiedAt = formatter(post.getModifiedAt());

    }

    public String formatter(LocalDateTime localDateTime) {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm").format(localDateTime);
    }
}