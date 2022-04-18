package com.sparta.hh99_clonecoding.repository;

import com.sparta.hh99_clonecoding.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
