package com.AiFunding.ToBi.service.oauth;


import com.AiFunding.ToBi.dto.auth.SocialLoginType;
import com.AiFunding.ToBi.entity.CustomerInformationEntity;
import com.AiFunding.ToBi.exception.AlreadyExistUser;
import com.AiFunding.ToBi.mapper.CustomerInformationRepository;
import com.AiFunding.ToBi.service.oauth.social.GoogleOauth;
import com.AiFunding.ToBi.service.oauth.social.KakaoOauth;
import com.AiFunding.ToBi.service.oauth.social.SocialOauth;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OAuthService {

    private final CustomerInformationRepository customerInformationRepository;

    private final JwtTokenUtils jwtTokenUtils;
    private final HttpServletResponse response;
    private final List<SocialOauth> socialOauthList;

    public void request(SocialLoginType socialLoginType){
        SocialOauth socialOauth = this.findSocialOauthByType(socialLoginType);
        String redirectURL = socialOauth.getOauthRedirectURL();
        try {
            response.sendRedirect(redirectURL);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String requestAccessToken(SocialLoginType socialLoginType, String code) {
        SocialOauth socialOauth = this.findSocialOauthByType(socialLoginType);
        return socialOauth.requestAccessToken(code);
    }

    public String requestUserInfo(SocialLoginType socialLoginType,String accessToken) {
        SocialOauth socialOauth = this.findSocialOauthByType(socialLoginType);
        return socialOauth.getUserId(accessToken);
    }

    public boolean isExistsUser(String loginType, String userId){
        if(customerInformationRepository.findByUserIdAndLoginType(userId,loginType) == null){
            return false;
        }
        return true;


    public void saveUserInfo(HashMap<String, String> userInfo, String loginType) throws AlreadyExistUser {
        if(customerInformationRepository.findByUserIdAndLoginType(userInfo.get("id"),loginType) != null){
            throw new AlreadyExistUser();
        }
        customerInformationRepository.save(
                CustomerInformationEntity.builder()
                        .loginType(loginType)
                        .refreshToken(userInfo.get("refreshToken"))
                        .userId(userInfo.get("id")).build()
        );
    }

    private SocialOauth findSocialOauthByType(SocialLoginType socialLoginType) {
        return socialOauthList.stream()
                .filter(x -> x.type() == socialLoginType)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("알 수 없는 SocialLoginType 입니다."));
    }

}
