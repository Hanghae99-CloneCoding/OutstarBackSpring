package com.sparta.hh99_clonecoding.service;

import com.sparta.hh99_clonecoding.exception.Code;
import com.sparta.hh99_clonecoding.exception.PrivateException;
import com.sparta.hh99_clonecoding.model.Member;
import com.sparta.hh99_clonecoding.repository.MemberRepository;
import com.sparta.hh99_clonecoding.security.UserDetailsImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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

      return new UserDetailsImpl(findMember);
   }
}
