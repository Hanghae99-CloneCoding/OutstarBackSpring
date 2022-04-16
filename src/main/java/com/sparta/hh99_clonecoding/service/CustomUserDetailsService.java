package com.sparta.hh99_clonecoding.service;

import com.sparta.hh99_clonecoding.exception.Code;
import com.sparta.hh99_clonecoding.exception.PrivateException;
import com.sparta.hh99_clonecoding.model.Member;
import com.sparta.hh99_clonecoding.repository.MemberRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component("userDetailsService")
public class CustomUserDetailsService implements UserDetailsService {
   private final MemberRepository memberRepository;

   public CustomUserDetailsService(MemberRepository memberRepository) {
      this.memberRepository = memberRepository;
   }

   @Override
   @Transactional
   public UserDetails loadUserByUsername(final String email) {
       Member findMember = memberRepository.findByEmail(email)
              .orElseThrow(() -> new PrivateException(Code.LOGIN_EMAIL_FAIL));

      return createUser(findMember);
   }

   private org.springframework.security.core.userdetails.User createUser(Member Member) {
      String authority = "ROLE_USER";

      SimpleGrantedAuthority simpleAuthority = new SimpleGrantedAuthority(authority);
      Collection<GrantedAuthority> authorities = new ArrayList<>();
      authorities.add(simpleAuthority);

      return new org.springframework.security.core.userdetails.User(Member.getUsername(),
              Member.getPassword(),
              authorities);
   }
}
