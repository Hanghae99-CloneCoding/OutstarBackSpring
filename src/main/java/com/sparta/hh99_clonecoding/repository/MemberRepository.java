package com.sparta.hh99_clonecoding.repository;

import com.sparta.hh99_clonecoding.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member,Long> {
    Optional<Member> findByEmail(String email);
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);

}
