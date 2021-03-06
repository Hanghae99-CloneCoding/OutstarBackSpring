package com.sparta.hh99_clonecoding.repository;

import com.sparta.hh99_clonecoding.model.Comment;
import com.sparta.hh99_clonecoding.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByPost(Post postId);

}
