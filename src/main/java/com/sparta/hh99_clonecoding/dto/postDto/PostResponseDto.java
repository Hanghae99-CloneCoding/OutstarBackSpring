package com.sparta.hh99_clonecoding.dto.postDto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Setter
@Getter
@NoArgsConstructor
public class PostResponseDto {
//    private String username;
    private String desc;
    private List<String> imgUrl;

    public PostResponseDto(String desc, List<String> imgUrl) {
        this.desc = desc;
        this.imgUrl = imgUrl;
    }
}