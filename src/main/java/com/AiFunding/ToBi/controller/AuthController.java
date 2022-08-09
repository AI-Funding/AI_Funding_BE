package com.AiFunding.ToBi.controller;

import com.AiFunding.ToBi.dto.auth.*;
import com.AiFunding.ToBi.entity.CustomerInformationEntity;
import com.AiFunding.ToBi.service.oauth.OAuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

    @PostMapping(value = "/sign-in")
    public ResponseEntity<LoginDto> SignIn(
            @RequestParam(name = "socialLoginType") String socialLoginType,
            @RequestParam(name = "code") String code,
            HttpServletResponse response) {
        log.info(">> 소셜 로그인 API 서버로부터 받은 code :: {}", code);
        String accessToken = oAuthService.requestAccessToken(SocialLoginType.valueOf(socialLoginType), code);
        String userId = oAuthService.requestUserInfo(SocialLoginType.valueOf(socialLoginType), code);
        if(oAuthService.isExistsUser(socialLoginType,userId)){
            TokenDto tokens = oAuthService.signIn(userId, socialLoginType);
            Cookie cookie = new Cookie("refreshToken", tokens.getRefreshToken());
            cookie.setMaxAge(90 * 24 * 60 * 60);//90일 유지
            cookie.setSecure(true);
            cookie.setHttpOnly(true);
            cookie.setPath("/");

            response.addCookie(cookie);

            LoginDto loginDto = LoginDto.builder()
                    .accessToken(tokens.getAccessToken())
                    .UID(userId)
                    .isExistUser(true)
                    .build();
            return ResponseEntity.ok().body(loginDto);
        }
        Cookie cookie = new Cookie("refreshToken", null);
        cookie.setMaxAge(90 * 24 * 60 * 60);//90일 유지
        cookie.setSecure(true);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        response.addCookie(cookie);

        LoginDto loginDto = LoginDto.builder()
                .accessToken(null)
                .UID(userId)
                .isExistUser(false)
                .build();
        return ResponseEntity.ok().body(loginDto);
    }

    @PostMapping("/sign-up")
    public ResponseEntity<LoginDto> SignUp(@RequestBody LoginRequestDto loginRequestDto, HttpServletResponse response){
        TokenDto tokens = oAuthService.signUp(loginRequestDto);
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
    @GetMapping("/duplicate/email")
    public ResponseEntity<Map<String,Boolean>> checkEmailDuplicate(@RequestBody DuplicateRequestDto duplicateRequestDto){
        Map<String, Boolean> isExist = new HashMap<>();
        isExist.put("isExist",oAuthService.isEmailDuplicated(duplicateRequestDto.getValue()));
        return ResponseEntity.ok(isExist);
    }

    @GetMapping("/duplicate/nickName")
    public ResponseEntity<Map<String,Boolean>> checkNicknameDuplicate(@RequestBody DuplicateRequestDto duplicateRequestDto){
        Map<String, Boolean> isExist = new HashMap<>();
        isExist.put("isExist",oAuthService.isNicknameDuplicated(duplicateRequestDto.getValue()));
        return ResponseEntity.ok(isExist);
    }

}


