package com.sparta.hh99_clonecoding.repository;

import com.sparta.hh99_clonecoding.model.Img;
import com.sparta.hh99_clonecoding.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImgRepository extends JpaRepository<Img, Long> {
    List<Img> findAllByPost(Post postId);
}