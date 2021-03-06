package com.sparta.hh99_clonecoding.repository;

import com.sparta.hh99_clonecoding.model.Post;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByOrderByCreatedAtDesc();
    Slice <Post> findAllBy(Pageable pageable);
}