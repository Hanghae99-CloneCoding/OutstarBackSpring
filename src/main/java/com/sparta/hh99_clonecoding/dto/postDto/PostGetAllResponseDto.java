package com.sparta.hh99_clonecoding.dto.postDto;

import com.sparta.hh99_clonecoding.model.Post;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
public class PostGetAllResponseDto {
    private Long postId;
//    private String writer;
    private String desc;
    private String imageUrl;
    private String createdAt;
    private String modifiedAt;
//    private Long likesNum;
//    private Long commentsNum;
//    private boolean like;
//    private boolean checkUser;

    public PostGetAllResponseDto(Post post) {
        this.postId = post.getId();
//        this.writer = post.getUser().getUsername();
        this.desc = post.getDesc();
//        this.imageUrl = post.getImageUrl();
        this.createdAt = formatter(post.getCreatedAt());
        this.modifiedAt = formatter(post.getModifiedAt());
//        this.totalLike = totalLike;
//        this.heartLike = heartLike;
//        this.totalComment = totalComment;
//        this.checkMember = checkMember;
    }

    public String formatter(LocalDateTime localDateTime) {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm").format(localDateTime);
    }
}