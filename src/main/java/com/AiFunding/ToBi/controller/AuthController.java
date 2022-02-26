package com.AiFunding.ToBi.controller;

import com.AiFunding.ToBi.service.OAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class AuthController {

    @Autowired
    private OAuthService oAuthService;

    @RequestMapping(value = "/oauth2/code/kakao")
    public String kakaoLogin(@RequestParam("code") String code, HttpSession session){
        String accessToken = oAuthService.getAccessToken(code);
        HashMap<String, Object> userInfo = oAuthService.getUserInfo(accessToken);
        System.out.println("controller access_token : " + accessToken);
        System.out.println("login Controller : " + userInfo);

        //    클라이언트의 이메일이 존재할 때 세션에 해당 이메일과 토큰 등록
        if (userInfo.get("email") != null) {
            session.setAttribute("userId", userInfo.get("email"));
            session.setAttribute("access_Token", accessToken);
        }

        return "index";
    }
}
