package com.AiFunding.ToBi.controller;

import com.AiFunding.ToBi.dto.auth.*;
import com.AiFunding.ToBi.entity.CustomerInformationEntity;
import com.AiFunding.ToBi.service.oauth.OAuthService;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;


@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping(value = "/api/auth")
@Slf4j//logging Facade
public class AuthController {

    private final OAuthService oAuthService;

    @GetMapping(value = "/{socialLoginType}")
    public void socialLoginType(
            @PathVariable(name = "socialLoginType") SocialLoginType socialLoginType)
    {
        log.info(">> 사용자로부터 SNS 로그인 요청을 받음 :: {} Social Login", socialLoginType);
        oAuthService.request(socialLoginType);
    }

    @GetMapping(value = "/sign-in")
    public ResponseEntity<LoginDto> SignIn(
            @RequestParam(name = "socialLoginType") String socialLoginType,
            @RequestParam(name = "code") String code,
            HttpServletResponse response) {
        log.info(">> 소셜 로그인 API 서버로부터 받은 code :: {}", code);
        String accessToken = oAuthService.requestAccessToken(SocialLoginType.valueOf(socialLoginType), code);
        System.out.println("accessToken: " + accessToken);
        String userId = oAuthService.requestUserInfo(SocialLoginType.valueOf(socialLoginType), accessToken);
        System.out.println("userId: " + userId);
        String type ="";
        if(socialLoginType.equals("KAKAO")){
            type="00";
        }
        else if(socialLoginType.equals("GOOGLE")){
            type="01";
        }
        if(oAuthService.isExistsUser(type,userId)){
            System.out.println("로그인 요청 : " + userId);
            TokenDto tokens = oAuthService.signIn(userId, type);
            Cookie cookie = new Cookie("refreshToken", tokens.getRefreshToken());
            cookie.setMaxAge(90 * 24 * 60 * 60);//90일 유지
            cookie.setSecure(true);
            cookie.setHttpOnly(true);
            cookie.setPath("/");

            response.addCookie(cookie);
            System.out.println("쿠키 전송: " + tokens.getRefreshToken());

            LoginDto loginDto = LoginDto.builder()
                    .accessToken(tokens.getAccessToken())
                    .UID(userId)
                    .isExistUser(true)
                    .build();
            log.info(">> 로그인 성공 :: {}", socialLoginType);
            return ResponseEntity.ok().body(loginDto);
        }
        System.out.println("회원가입 요청 : " + userId);
        Cookie cookie = new Cookie("refreshToken", null);
        cookie.setMaxAge(90 * 24 * 60 * 60);//90일 유지
        cookie.setSecure(true);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        response.addCookie(cookie);
        System.out.println("쿠키 전송 : ");

        LoginDto loginDto = LoginDto.builder()
                .accessToken(null)
                .UID(userId)
                .isExistUser(false)
                .build();
        log.info(">> 회원가입 요청 :: {}", socialLoginType);
        return ResponseEntity.ok().body(loginDto);
    }

    @PostMapping(value = "/sign-up", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LoginDto> SignUp( HttpServletResponse response,
                                            @RequestBody LoginRequestDto loginRequestDto){
        log.info(">> 회원가입 요청 :: {}", loginRequestDto.getUID());

        System.out.println(loginRequestDto.getLoginType()+" "+loginRequestDto.getUID()+ " "+ loginRequestDto.getEmail());
        TokenDto tokens = oAuthService.signUp(loginRequestDto);
        System.out.println("accessToken: " + tokens.getAccessToken());
        System.out.println("refreshToken: " + tokens.getRefreshToken());
        Cookie cookie = new Cookie("refreshToken", tokens.getRefreshToken());
        cookie.setMaxAge(90 * 24 * 60 * 60);//90일 유지
        cookie.setSecure(true);
        cookie.setHttpOnly(true);
        cookie.setPath("/");

        response.addCookie(cookie);

        LoginDto loginDto = LoginDto.builder()
                .accessToken(tokens.getAccessToken())
                .UID(loginRequestDto.getUID())
                .isExistUser(true)
                .build();
        return ResponseEntity.ok().body(loginDto);
    }
    @PostMapping("/duplicate/email")
    public ResponseEntity<Map<String,Boolean>> checkEmailDuplicate(@RequestBody DuplicateRequestDto duplicateRequestDto){
        Map<String, Boolean> isExist = new HashMap<>();
        isExist.put("isExist",oAuthService.isEmailDuplicated(duplicateRequestDto.getValue()));
        return ResponseEntity.ok(isExist);
    }

    @PostMapping("/duplicate/nickName")
    public ResponseEntity<Map<String,Boolean>> checkNicknameDuplicate(@RequestBody DuplicateRequestDto duplicateRequestDto){
        Map<String, Boolean> isExist = new HashMap<>();
        isExist.put("isExist",oAuthService.isNicknameDuplicated(duplicateRequestDto.getValue()));
        return ResponseEntity.ok(isExist);
    }

}


