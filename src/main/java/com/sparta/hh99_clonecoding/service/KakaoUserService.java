package com.sparta.hh99_clonecoding.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.hh99_clonecoding.dto.KakaoUserInfoDto;
import com.sparta.hh99_clonecoding.repository.MemberRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Service
public class KakaoUserService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private MemberRepository userRepository;

    @Value("${kakao.access-token.client-id}") private String CLIENT_ID;

    @Value("${kakao.access-token.client-secret}") private String CLIENT_SECRET ;

    @Value("${kakao.access-token.redirect-uri}") private String REDIRECT_URI;

    public HttpHeaders kakaoLogin(String authorityCode) throws JsonProcessingException {
        // 1. "인가 코드"로 "액세스 토큰" 요청
        String accessToken = getAccessToken(authorityCode);

        // 2. 토큰으로 카카오 API 호출
        KakaoUserInfoDto kakaoUserInfo = getKakaoUserInfo(accessToken);

        // 3. 필요시에 회원가입
//        Member kakaoUser = registerKakaoUserIfNeeded(kakaoUserInfo);

        // 4. 강제 로그인 처리
//        forceLogin(kakaoUser);

        return null;
    }

    private String getAccessToken(String code) throws JsonProcessingException {
        // HTTP Header 생성
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        // HTTP Body 생성
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "authorization_code");
        body.add("client_id", CLIENT_ID);
        body.add("client_secret", CLIENT_SECRET);
        body.add("redirect_uri", REDIRECT_URI);
        body.add("code", code);

        // HTTP 요청 보내기
        HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest =
                new HttpEntity<>(body, headers);
        RestTemplate rt = new RestTemplate();
        ResponseEntity<String> response = rt.exchange(
                "https://kauth.kakao.com/oauth/token",
                HttpMethod.POST,
                kakaoTokenRequest,
                String.class
        );

        // HTTP 응답 (JSON) -> 액세스 토큰 파싱
        String responseBody = response.getBody();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(responseBody);
        return jsonNode.get("access_token").asText();
    }

    private KakaoUserInfoDto getKakaoUserInfo(String accessToken) throws JsonProcessingException {
        // HTTP Header 생성
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accessToken);
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        // HTTP 요청 보내기
        HttpEntity<MultiValueMap<String, String>> kakaoUserInfoRequest = new HttpEntity<>(headers);
        RestTemplate rt = new RestTemplate();
        ResponseEntity<String> response = rt.exchange(
                "https://kapi.kakao.com/v2/user/me",
                HttpMethod.POST,
                kakaoUserInfoRequest,
                String.class
        );

        String responseBody = response.getBody();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(responseBody);
        Long id = jsonNode.get("id").asLong();
        String nickname = jsonNode.get("properties")
                .get("nickname").asText();
        String email = jsonNode.get("kakao_account")
                .get("email").asText();

        System.out.println("카카오 사용자 정보: " + id + ", " + nickname + ", " + email);
        return new KakaoUserInfoDto(id, nickname, email);
    }

//    private User registerKakaoUserIfNeeded(KakaoUserInfoDto kakaoUserInfo) {
//        // DB 에 중복된 Kakao Id 가 있는지 확인
//        Long kakaoId = kakaoUserInfo.getId();
//        User kakaoUser = userRepository.findByKakaoId(kakaoId)
//                .orElse(null);
//        if (kakaoUser == null) {
//            // 회원가입
//            // username: kakao nickname
//            String nickname = kakaoUserInfo.getNickname();
//
//            // password: random UUID
//            String password = UUID.randomUUID().toString();
//            String encodedPassword = passwordEncoder.encode(password);
//
//            // email: kakao email
//            String email = kakaoUserInfo.getEmail();
//            // role: 일반 사용자
//            UserRoleEnum role = UserRoleEnum.USER;
//
//            kakaoUser = new User(nickname, encodedPassword, email, role, kakaoId);
//            userRepository.save(kakaoUser);
//        }
//        return kakaoUser;
//    }
//
//    private void forceLogin(User kakaoUser) {
//        UserDetails userDetails = new UserDetailsImpl(kakaoUser);
//        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//    }
}