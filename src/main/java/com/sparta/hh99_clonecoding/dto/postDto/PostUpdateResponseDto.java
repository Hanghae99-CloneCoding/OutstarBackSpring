package com.sparta.hh99_clonecoding.dto.postDto;

import lombok.Data;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
public class PostUpdateResponseDto {
    private Long postId;
//    private String username;
    private String desc;
//    private String imageUrl;
}