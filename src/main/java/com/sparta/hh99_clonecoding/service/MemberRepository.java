package com.sparta.hh99_clonecoding.service;

import com.sparta.hh99_clonecoding.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member,Long> {
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);

}
