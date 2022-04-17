package com.sparta.hh99_clonecoding.model;

import com.sparta.hh99_clonecoding.dto.MemberRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
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

    public Member(String email, String username, String encodedPassword) {
        this.email = email;
        this.username = username;
        this.password = encodedPassword;
        this.kakaoId = null;
    }

    public Member(String email, String username, String encodedPassword, Long kakaoId) {
        this.email = email;
        this.username = username;
        this.password = encodedPassword;
        this.kakaoId = kakaoId;
    }
}
