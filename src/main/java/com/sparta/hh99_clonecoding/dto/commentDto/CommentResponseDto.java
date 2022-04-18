package com.sparta.hh99_clonecoding.dto.commentDto;

import com.sparta.hh99_clonecoding.model.Comment;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
public class CommentResponseDto {
    private Long postId;
    private String comment;
    private String modifiedAt;

    public CommentResponseDto(Comment comment) {
        this.postId = comment.getId();
        this.comment = comment.getComment();
        this.modifiedAt = formatter(comment.getModifiedAt());
    }

    public String formatter(LocalDateTime localDateTime) {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm").format(localDateTime);
    }
}