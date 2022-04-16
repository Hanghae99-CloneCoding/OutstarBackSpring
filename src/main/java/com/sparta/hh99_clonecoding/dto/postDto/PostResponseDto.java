package com.sparta.hh99_clonecoding.dto.postDto;

import lombok.Data;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Data
@Getter
public class PostResponseDto {
//    private String username;
    private String desc;
    private MultipartFile imageUrl;
//    private List<String> url = new ArrayList<>();

//    private LocalDateTime createdAt;
//    private LocalDateTime modifiedAt;

//    public PostResponseDto(Post post) {
//        this.desc = post.getDesc();
////        this.createdAt = post.getCreatedAt();
////        this.modifiedAt = post.getModifiedAt();
//    }
}