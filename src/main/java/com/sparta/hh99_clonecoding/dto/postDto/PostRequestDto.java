package com.sparta.hh99_clonecoding.dto.postDto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonAutoDetect
public class PostRequestDto {

//    private String username;
    private String desc;
    private String imgUrl;
}