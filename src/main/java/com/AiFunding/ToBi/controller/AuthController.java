package com.AiFunding.ToBi.controller;

import com.AiFunding.ToBi.dto.auth.TokenDto;
import com.AiFunding.ToBi.service.oauth.OAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class AuthController {

    private final OAuthService oAuthService;

    @RequestMapping(value = "/oauth2/code/kakao")
    public String kakaoLogin(@RequestParam("code") String code, HttpSession session){
        TokenDto token = oAuthService.getAccessToken(code); // accessToken 값 가져오기
        HashMap<String, String> userInfo = oAuthService.getUserInfo(token.getAccessToken());
        System.out.println("controller access_token : " + token.getAccessToken());
        System.out.println("login Controller : " + userInfo);

        userInfo.put("accessToken",token.getAccessToken()); // accessToken HashMap에 저장
        userInfo.put("refreshToken",token.getRefreshToken()); // refreshToken HashMap에 저장

        //    클라이언트의 이메일이 존재할 때 세션에 해당 이메일과 토큰 등록
        if (userInfo.get("email") != null) {
            session.setAttribute("userId", userInfo.get("email"));
            session.setAttribute("access_Token", token.getAccessToken());
        }

        try{
            oAuthService.saveUserInfo(userInfo,"kakao");
        }catch (Exception e){
            e.printStackTrace();
        }

        return "index";
    }
}
