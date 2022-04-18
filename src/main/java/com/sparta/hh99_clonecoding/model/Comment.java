package com.sparta.hh99_clonecoding.model;

import com.sparta.hh99_clonecoding.dto.commentDto.CommentRequestDto;
import com.sparta.hh99_clonecoding.exception.Code;
import com.sparta.hh99_clonecoding.exception.PrivateException;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

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

//    public void update(CommentRequestDto commentRequestDto) {
//        if (!StringUtils.hasText(commentRequestDto.getComment())) {
//            throw new PrivateException(Code.WRONG_INPUT_COMMENT);
//        }
//        this.comment = commentRequestDto.getComment();
//    }

    public void update(Post post, CommentRequestDto commentRequestDto) {
        if (!StringUtils.hasText(commentRequestDto.getComment())) {
            throw new PrivateException(Code.WRONG_INPUT_COMMENT);
        }
        this.post = post;
        this.comment = commentRequestDto.getComment();
    }
}