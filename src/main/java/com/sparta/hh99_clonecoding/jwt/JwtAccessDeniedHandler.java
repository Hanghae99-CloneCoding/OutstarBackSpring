package com.sparta.hh99_clonecoding.jwt;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAccessDeniedHandler implements AccessDeniedHandler {

   @Override
   public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException {
      //필요한 권한이 없이 접근하려 할때 403
      response.sendError(HttpServletResponse.SC_FORBIDDEN,"해당 메서드에 접근하기 위한 권한이 부족합니다. 본인의 권한을 다시 확인해주세요.");
   }
}
