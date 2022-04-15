package com.sparta.hh99_clonecoding.model;

import org.springframework.util.StringUtils;
import com.sparta.hh99_clonecoding.dto.postDto.PostRequestDto;
import com.sparta.hh99_clonecoding.exception.Code;
import com.sparta.hh99_clonecoding.exception.PrivateException;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class Post extends Timestamped {

    @Id
    @GeneratedValue
    private Long Id;

    @Column(nullable = false)
    private String desc;
//
//    @Column(nullable = false)
//    private String imageUrl;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn
//    private User user;

    public Post(String desc) {
        if (!StringUtils.hasText(desc)) {
            throw new PrivateException(Code.WRONG_INPUT);
        }
        this.desc = desc;
    }

//    public void update(PostRequestDto requestDto) {
//        if (!StringUtils.hasText(requestDto.getDesc())) {
//            throw new PrivateException(Code.WRONG_INPUT);
//        }
//        this.desc = requestDto.getDesc();
//    }

    public void updatePost(String desc) {
        if (!StringUtils.hasText(desc)) {
            throw new PrivateException(Code.WRONG_INPUT);
        }
        this.desc = desc;
    }
}