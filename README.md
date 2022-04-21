# Instagram 클론 코딩(Back-End)
Spring Boot + React를 이용한 인스타크램 클론 코딩 프로젝트 
- [\[시연 영상 구경하기\]](https://www.youtube.com/watch?v=r8sIfu6DKtI&t=3s)
- [\[Front-End Repository\]](https://github.com/Hanghae99-CloneCoding/OutstarFrontReact)

## 👥 Member
<table>
    <tr>
        <td align="center">
        박형기<br>
         <a href="https://github.com/Denia-park"><img src="https://img.shields.io/badge/Github-181717?style=flat-square&logo=Github&logoColor=white"/></a>
        </td>
        <td align="center">
        유혜민<br>
        <a href="https://github.com/hyemco"><img src="https://img.shields.io/badge/Github-181717?style=flat-square&logo=Github&logoColor=white"/></a>
        </td>
    </tr>
    <tr>
        <td align="center"> 
          회원가입<br>
          로그인(JWT Token 인증 방식)<br>
          소셜 로그인(Kakao)
        </td>
        <td align="center">
          페이지네이션(무한 스크롤)<br>
          피드 CRUD<br>
          댓글 CRUD<br>
          다중 이미지 파일 서버(S3) 연결 및 CRUD 구현
        </td>
    </tr>
</table>
<br>

## 🗓 프로젝트 기간
- 2022년 4월 15일 ~ 2022년 4월 21일
<br>

## ⚙️ 기술 스택

|제목|내용|
|:---:|:---:|
|Language|<img src="https://img.shields.io/badge/Java-007396?style=flat-square&logo=java&logoColor=white"/>|
|Build|<img src="https://img.shields.io/badge/Gralde-02303A?style=flat-square&logo=Gradle&logoColor=white"/>|
|FrameWork|<img src="https://img.shields.io/badge/SpringBoot-6DB33F?style=flat-square&logo=springboot&logoColor=white"/>|
|DB|<img src="https://img.shields.io/badge/MySQL-4479A1?style=flat-square&logo=MySQL&logoColor=white"/>|
|Server|<img src="https://img.shields.io/badge/Amazon AWS-232F3E?style=flat-square&logo=Amazon AWS&logoColor=white"/> <img src="https://img.shields.io/badge/Amazon S3-569A31?style=flat-square&logo=Amazon S3&logoColor=white"/>|
<br>

## 💫 클론 기능
> 1. 회원가입 및 로그인
+ Spring Security, JWT 인증 방식을 사용한 로그인
+ Email, username 중복확인 및 필드별 유효성 검사
+ 카카오 소셜 로그인 구현(프론트와 연동 X)

> 2. 피드 CRUD
+ 로그인한 유저만 CRUD 가능
+ 피드 목록 조회(작성 시간 기준 내림차순)
+ 조회시 무한 스크롤(프론트와 연동 X)
+ 여러장의 사진과 내용 등록
+ 피드 수정 및 삭제(프론트와 연동 X)

> 3. 댓글 CRUD(프론트와 연동 X)
+ 로그인한 유저만 CRUD 가능
+ 피드별 댓글 조회
+ 댓글 작성 및 삭제
<br>

## 🗺 ERD 설계
<img src="https://user-images.githubusercontent.com/98294357/164461136-1d552294-11d3-4332-913c-55696c50f75f.png" width="400"/>

<br>
                                                                                                                           
## 📌 API 설계 및 회의 자료
[\[노션 링크\]](https://www.notion.so/10-Instagram-b230f427624443d78f77b547f3152a53)
<br>
                                                                                                                           
## 💡 Trouble Shooting
<details>                                                                                                                      
<summary>1. H2에서 생성이 되던 Table이 MySQL에 연결하니 생성이 되지 않는 문제</summary>
<br>
<div>
- 원인 : 테이블 컬럼명 중 MySQL 예약어 중 하나인 "desc"를 사용함<br>
- 해결 : "desc" 대신 "content"로 수정                                                                                                                         
</div>
</details>                                                                                                                  

<details>                                                                                                                      
<summary>2. JWT를 사용하여 SecurityContextHolder에 유저정보를 저장했는데 @AuthorizationPrincipal UserDetails userDetails 을 Controller 메서드의 매개변수로 사용할 경우 유저 정보를 제대로 가져오지 못하는 문제</summary>
<br>
<div>
- 원인 : SecurityContextHolder에 저장한 유저정보 와 @AuthorizationPrincipal UserDetails userDetails 는 다른 객체 이므로 @AuthorizationPrincipal UserDetail userDetail 를 통해서 유저정보를 가져올 수 없었음<br>
- 해결 : SecurityContextHolder.getContext().getAuthentication() 메서드를 사용해서 유저정보를 가져옴                                                                                                                         
</div>
</details>  

<details>                                                                                                                      
<summary>3. 서버에서 JWT를 Heade에 추가하여 클라이언트로 보내줬지만 FE에서 해당 Header를 읽지 못하는 문제 (null 값으로 반환됨) [개발자도구 - 네트워크 Tab 에서는 헤더를 확인할 수 있음]</summary>
<br>
<div>
- 원인 : CORS의 경우 기본적으로 화면에서 response header 값을 읽지 못함 (JavaScript 코드에서 읽을 수 없음)<br>
- 해결 : addCorsMappings에서 .exposedHeaders("Authorization") 을 추가해 화면에서 지정된 header 값("Authorization")을 읽을 수 있게 함                                                                                                                         
</div>
</details>                    

<details>                                                                                                                      
<summary>4. EC2에 jar 파일을 올리고 실행시 RSD랑 연결이 되지 않는 문제</summary>
<br>
<div>
- Err Msg :  com.mysql.cj.jdbc.exceptions.CommunicationsException: Communications link failure. The last packet sent successfully to the server was 0 milliseconds ago. The driver has not received any packets from the server
- 원인 : RDS 서버 통신 에러<br>
- 해결 : RDS의 DB 인스턴스를 삭제 후 재설치                                                                                                                         
</div>
</details>
                          
<details>                                                                                                                      
<summary>5. CORS 전역 처리를 했음에도 회원가입/로그인 이후에 CORS 에러가 계속 발생하는 문제</summary>
<br>
<div>
- 원인 : Spring Security에서 CORS 인가 설정을 하지 않아서 발생<br>
- 해결 : CORS 인가 처리를 해줌
<div class="colorscripter-code" style="color:#010101;font-family:Consolas, 'Liberation Mono', Menlo, Courier, monospace !important; position:relative !important;overflow:auto"><table class="colorscripter-code-table" style="margin:0;padding:0;border:none;background-color:#fafafa;border-radius:4px;" cellspacing="0" cellpadding="0"><tr><td style="padding:6px;border-right:2px solid #e5e5e5"><div style="margin:0;padding:0;word-break:normal;text-align:right;color:#666;font-family:Consolas, 'Liberation Mono', Menlo, Courier, monospace !important;line-height:130%"><div style="line-height:130%">1</div><div style="line-height:130%">2</div><div style="line-height:130%">3</div><div style="line-height:130%">4</div><div style="line-height:130%">5</div><div style="line-height:130%">6</div><div style="line-height:130%">7</div><div style="line-height:130%">8</div><div style="line-height:130%">9</div><div style="line-height:130%">10</div><div style="line-height:130%">11</div><div style="line-height:130%">12</div><div style="line-height:130%">13</div><div style="line-height:130%">14</div><div style="line-height:130%">15</div><div style="line-height:130%">16</div><div style="line-height:130%">17</div></div></td><td style="padding:6px 0;text-align:left"><div style="margin:0;padding:0;color:#010101;font-family:Consolas, 'Liberation Mono', Menlo, Courier, monospace !important;line-height:130%"><div style="padding:0 6px; white-space:pre; line-height:130%">@Override</div><div style="padding:0 6px; white-space:pre; line-height:130%"><span style="color:#a71d5d">protected</span>&nbsp;<span style="color:#a71d5d">void</span>&nbsp;configure(HttpSecurity&nbsp;httpSecurity)&nbsp;<span style="color:#a71d5d">throws</span>&nbsp;Exception&nbsp;{</div><div style="padding:0 6px; white-space:pre; line-height:130%">&nbsp;&nbsp;&nbsp;&nbsp;httpSecurity</div><div style="padding:0 6px; white-space:pre; line-height:130%">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;.cors()</div><div style="padding:0 6px; white-space:pre; line-height:130%">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;.configurationSource(corsConfigurationSource());</div><div style="padding:0 6px; white-space:pre; line-height:130%">}</div><div style="padding:0 6px; white-space:pre; line-height:130%">&nbsp;</div><div style="padding:0 6px; white-space:pre; line-height:130%">@Bean</div><div style="padding:0 6px; white-space:pre; line-height:130%"><span style="color:#a71d5d">public</span>&nbsp;CorsConfigurationSource&nbsp;corsConfigurationSource()&nbsp;{</div><div style="padding:0 6px; white-space:pre; line-height:130%">&nbsp;&nbsp;&nbsp;&nbsp;CorsConfiguration&nbsp;configuration&nbsp;<span style="color:#0086b3"></span><span style="color:#a71d5d">=</span>&nbsp;<span style="color:#a71d5d">new</span>&nbsp;CorsConfiguration();&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div><div style="padding:0 6px; white-space:pre; line-height:130%">&nbsp;&nbsp;&nbsp;&nbsp;configuration.setAllowCredentials(<span style="color:#0099cc">true</span>)&nbsp;;</div><div style="padding:0 6px; white-space:pre; line-height:130%">&nbsp;&nbsp;&nbsp;&nbsp;configuration.addAllowedOriginPattern(<span style="color:#63a35c">"*"</span>);</div><div style="padding:0 6px; white-space:pre; line-height:130%">&nbsp;&nbsp;&nbsp;&nbsp;configuration.addAllowedOrigin(<span style="color:#63a35c">"http://localhost:3000"</span>);</div><div style="padding:0 6px; white-space:pre; line-height:130%">&nbsp;&nbsp;&nbsp;&nbsp;configuration.addAllowedMethod(<span style="color:#63a35c">"*"</span>);</div><div style="padding:0 6px; white-space:pre; line-height:130%">&nbsp;&nbsp;&nbsp;&nbsp;configuration.addAllowedHeader(<span style="color:#63a35c">"*"</span>);</div><div style="padding:0 6px; white-space:pre; line-height:130%">&nbsp;&nbsp;&nbsp;&nbsp;configuration.addExposedHeader(<span style="color:#63a35c">"Authorization"</span>);</div><div style="padding:0 6px; white-space:pre; line-height:130%">}</div></div><div style="text-align:right;margin-top:-13px;margin-right:5px;font-size:9px;font-style:italic"></div></td></tr></table></div>
</div>
</details>

