package com.sparta.hh99_clonecoding.model;

import com.sparta.hh99_clonecoding.dto.commentDto.CommentRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class Comment extends Timestamped{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Post post;

    @Column(nullable = false)
    private String comment;

    public Comment(Post post, CommentRequestDto commentRequestDto) {
        this.post = post;
        this.comment = commentRequestDto.getComment();
    }
}