package com.AiFunding.ToBi.controller;

import com.AiFunding.ToBi.dto.auth.SocialLoginType;
import com.AiFunding.ToBi.dto.auth.TokenDto;
import com.AiFunding.ToBi.entity.CustomerInformationEntity;
import com.AiFunding.ToBi.service.oauth.OAuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping(value = "/auth")
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

    @PostMapping(value = "/callback")
    public String callback(
            @RequestParam(name = "socialLoginType") String socialLoginType,
            @RequestParam(name = "code") String code) {
        log.info(">> 소셜 로그인 API 서버로부터 받은 code :: {}", code);
        String accessToken = oAuthService.requestAccessToken(SocialLoginType.valueOf(socialLoginType), code);
        String userId = oAuthService.requestUserInfo(SocialLoginType.valueOf(socialLoginType), code);
        boolean isExistsUser = oAuthService.isExistsUser(socialLoginType,userId);
        return "index";
    }

}
