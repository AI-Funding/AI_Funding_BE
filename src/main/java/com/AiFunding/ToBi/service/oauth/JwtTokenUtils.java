package com.AiFunding.ToBi.service.oauth;

import com.AiFunding.ToBi.entity.CustomerInformationEntity;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class JwtTokenUtils {
    @Value("${spring.security.oauth2.client.token.secret-key}")
    String SECRET_KEY;

    @Value("${spring.security.oauth2.client.token.refresh-key}")
    String REFRESH_KEY;

    @Value("${spring.security.oauth2.client.token.data-key}")
    String DATA_KEY;

    private final long tokenValidMillisecond = 0;
    private final long refreshTokenValidMillisecond = 0;

    //jwt Access Token 생성
    public String createJwtToken(CustomerInformationEntity customerInformationEntity){
        Date now = new Date();

        return Jwts.builder()
                .setClaims(createClaims(customerInformationEntity))
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime()+tokenValidMillisecond))
                .signWith(SignatureAlgorithm.HS256, createSigningKey(SECRET_KEY))
                .compact();
    }

    // jwt refresh Token 생성
    public String createRefreshToken() {
        Date now = new Date();
        return Jwts.builder()
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + refreshTokenValidMillisecond))
                .signWith(SignatureAlgorithm.HS256, createSigningKey(REFRESH_KEY))
                .compact();
    }

    // JWT Token 유효성 검사
    public boolean isValidToken(String token) {
        System.out.println("isValidToken is : " +token);
        try {
            Claims accessClaims = getClaimsToken(token, SECRET_KEY);
            System.out.println("Access expireTime: " + accessClaims.getExpiration());
            System.out.println("Access userId: " + accessClaims.get("userId"));
            return true;
        } catch (ExpiredJwtException exception) { //토큰 만료
            System.out.println("Token Expired UserID : " + exception.getClaims().getSubject());
            return false;
        } catch (JwtException exception) { //토큰이 변조
            System.out.println("Token Tampered");
            return false;
        } catch (NullPointerException exception) {//토큰이 없는 경우
            System.out.println("Token is null");
            return false;
        }
    }

    //Refresh Token 유효성 검사
    public boolean isValidRefreshToken(String token) {
        try {
            Claims accessClaims = getClaimsToken(token, REFRESH_KEY);
            System.out.println("Access expireTime: " + accessClaims.getExpiration());
            return true;
        } catch (ExpiredJwtException exception) {
            System.out.println("Token Expired UserID : " + exception.getClaims().getSubject());
            return false;
        } catch (JwtException exception) {
            System.out.println("Token Tampered");
            return false;
        } catch (NullPointerException exception) {
            System.out.println("Token is null");
            return false;
        }
    }

    //Token 정보 읽기
    private Claims getClaimsToken(String token, String key) {
        return Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(key))
                .parseClaimsJws(token)
                .getBody();
    }

    private Key createSigningKey(String key) {
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(key);
        return new SecretKeySpec(apiKeySecretBytes, SignatureAlgorithm.HS256.getJcaName());
    }

    private Map<String, Object> createClaims(CustomerInformationEntity customerInformationEntity) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(DATA_KEY, customerInformationEntity.getUserId());//키와 함께 원하는 정보를 담는다.
        return claims;
    }
}
