package com.sparta.hh99_clonecoding.model;

import com.sparta.hh99_clonecoding.dto.postDto.PostRequestDto;
import com.sparta.hh99_clonecoding.exception.Code;
import com.sparta.hh99_clonecoding.exception.PrivateException;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
public class Post extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    @Column(nullable = false)
    private String content;

    @Transient
    private final List<Img> imgList = new ArrayList<>();

//    @OneToMany(mappedBy = "post", cascade = CascadeType.PERSIST, orphanRemoval = true)  // 게시글 업로드/삭제 때 이미지 데이터도 같이 업로드/삭제
//    @JsonManagedReference
//    private List<Img> imgList;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Member member;

    @OneToMany(mappedBy = "post")
    private List<Comment> comment;

    // 게시글 작성
    public Post(String content, Member member) {
        if (!StringUtils.hasText(content)) {
            throw new PrivateException(Code.WRONG_INPUT_CONTENT);
        }
        this.content = content;
        this.member = member;
    }

    // 게시글 수정
    public void updatePost(PostRequestDto res) {
        if (!StringUtils.hasText(res.getContent())) {
            throw new PrivateException(Code.WRONG_INPUT_CONTENT);
        }
        this.content = res.getContent();
    }
}