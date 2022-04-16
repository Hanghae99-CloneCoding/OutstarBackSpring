package com.sparta.hh99_clonecoding.model;

import com.sparta.hh99_clonecoding.dto.MemberRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
public class Member {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String username; //nickname

    @Column(nullable = false)
    private String password;

    @Column(unique = true)
    private Long kakaoId;

    public Member(String email, String username, String password) {
        this.email = email;
        this.username = username;
        this.password = password;
    }
}
