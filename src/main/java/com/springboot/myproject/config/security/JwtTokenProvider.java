package com.springboot.myproject.config.security;

import com.springboot.myproject.data.entity.UserDetails;
import com.springboot.myproject.service.UserDetailsService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

    private final Logger LOGGER = LoggerFactory.getLogger(JwtTokenProvider.class);
    private final UserDetailsService userDetailsService;

    @Value("${springboot.jwt.secret}") //application.properties 파일
    private String secretKey = "secretKey";
    private final long tokenValidMillisecond = 1000L*60*60;

    @PostConstruct //해당 객체가 빈으로 등록되면 수행됨.
    protected void init(){
        LOGGER.info("[init] JwtTokenProvider 내 secretKey 초기화 시작");
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes(StandardCharsets.UTF_8));
        LOGGER.info("[init] JwtTokenProvider 내 secretKey 초기화 완료");
    }

    public String createToken(String userUid, List<String> roles){
        LOGGER.info("[createToken] 토큰 생성 시작");
        //Claims 객체를 생성해 Jwt 토큰의 내용에 값 추가
        Claims claims = Jwts.claims().setSubject(userUid);
        claims.put("roles",roles);
        Date now = new Date();

        //토큰 생성
        String token = Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + tokenValidMillisecond))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();

        LOGGER.info("[createToken] 토큰 생성 완료");
        return token;
    }

    public String getUsername(String token){
        LOGGER.info("[getUsername] 토큰 기반 회원 구별 정보 추출");
        //decode라고 생각? 토큰 생성시에 넣었던 sub 값 추출
        String info = Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token).getBody().getSubject();
        LOGGER.info("[getUsername] 토큰 기반 회원 구별 정보 추출 완료, info : {}",info);
        return info;
    }

    //인증이 성공했을 때 SecurityContextHolder에 저장할 Authentication 생성
    public Authentication getAuthentication(String token){
        LOGGER.info("[getAuthentication] 토큰 인증 정보 조회 시작");
        UserDetails userDetails = userDetailsService.loadUserByUsername(this.getUsername(token));
        LOGGER.info("[getAuthentication] 토큰 인증 정보 조회 완료, UserDetails UserName : {}", userDetails.getUsername());
        return new UsernamePasswordAuthenticationToken(userDetails,"",userDetails.getAuthorities());
    }

    //헤더를 통해 클라이언트->서버로 Jwt 토큰 값을 전달해야 정상적인 추출이 가능
    public String resolveToken(HttpServletRequest request){
        LOGGER.info("[resolveToken] HTTP 헤더에서 Token 값 추출");
        return request.getHeader("X-AUTH-TOKEN");
    }

    public boolean validateToken(String token){
        LOGGER.info("[validateToken] 토큰 유효 체크 시작");
        try{
            Jws<Claims> claims = Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token);

            return !claims.getBody().getExpiration().before(new Date()); //유효기간 체크
        } catch (Exception e){
            LOGGER.info("[validateToken] 토큰 유효 체크 예외 발생");
            return false;
        }
    }
}
