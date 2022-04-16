package com.sparta.hh99_clonecoding.jwt;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

   @Override
   public void commence(HttpServletRequest request,
                        HttpServletResponse response,
                        AuthenticationException authException) throws IOException {
      // 유효한 자격증명을 제공하지 않고 접근하려 할때 401
//      response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
      System.out.println("잘못된 비밀번호를 입력하셨습니다.");
      response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
      PrintWriter writer = response.getWriter();
      writer.println("HTTP Status 401 / ErrCode : 111 , Msg : Plz check User's Password. Password is not Matching at DB Password");
   }
}
