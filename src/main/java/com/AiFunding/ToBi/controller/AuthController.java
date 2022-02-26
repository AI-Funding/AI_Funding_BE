package com.AiFunding.ToBi.controller;

import com.AiFunding.ToBi.service.OAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class AuthController {

    @Autowired
    private OAuthService oAuthService;

    @RequestMapping(value = "/oauth2/code/kakao")
    public String kakaoLogin(@RequestParam("code") String code){
        String accessToken = oAuthService.getAccessToken(code);
        System.out.println("controller access_token : " + accessToken);

        return "index";
    }
}
