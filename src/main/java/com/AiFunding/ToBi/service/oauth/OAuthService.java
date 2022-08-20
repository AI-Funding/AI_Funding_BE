package com.AiFunding.ToBi.service.oauth;

import com.AiFunding.ToBi.dto.auth.LoginRequestDto;
import com.AiFunding.ToBi.dto.auth.SocialLoginType;
import com.AiFunding.ToBi.dto.auth.TokenDto;
import com.AiFunding.ToBi.entity.CustomerInformationEntity;
import com.AiFunding.ToBi.mapper.CustomerInformationRepository;
import com.AiFunding.ToBi.service.oauth.social.SocialOauth;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.io.*;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OAuthService {

    private final CustomerInformationRepository customerInformationRepository;
    private final JwtTokenUtils jwtTokenUtils;
    private final HttpServletResponse response;
    private final List<SocialOauth> socialOauthList;

    public void request(SocialLoginType socialLoginType) {
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

    public String requestUserInfo(SocialLoginType socialLoginType, String accessToken) {
        SocialOauth socialOauth = this.findSocialOauthByType(socialLoginType);
        return socialOauth.getUserId(accessToken);
    }

    public boolean isExistsUser(String loginType, String userId) {
        return customerInformationRepository.existsByUserIdAndLoginType(userId,loginType);
    }

    public boolean isEmailDuplicated(String email){
        return customerInformationRepository.existsByEmail(email);
    }

    public boolean isNicknameDuplicated(String nickname){
        return customerInformationRepository.existsByNickname(nickname);
    }

    /*public void saveUserInfo(HashMap<String, String> userInfo, String loginType) throws AlreadyExistUser {
        if (customerInformationRepository.findByUserIdAndLoginType(userInfo.get("id"), loginType) != null) {
            throw new AlreadyExistUser();
        }
        customerInformationRepository.save(
                CustomerInformationEntity.builder()
                        .loginType(loginType)
                        .refreshToken(userInfo.get("refreshToken"))
                        .userId(userInfo.get("id")).build()
        );
    }*/

    private SocialOauth findSocialOauthByType(SocialLoginType socialLoginType) {
        return socialOauthList.stream()
                .filter(x -> x.type() == socialLoginType)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("알 수 없는 SocialLoginType 입니다."));
    }

    @Transactional
    public TokenDto signIn(String userId, String loginType) {
        CustomerInformationEntity customerInformationEntity =
                customerInformationRepository
                        .findByUserIdAndLoginType(userId,loginType)
                        .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));

        String accessToken = "";
        String refreshToken = customerInformationEntity.getRefreshToken();

        if(jwtTokenUtils.isValidRefreshToken(refreshToken)){
            accessToken = jwtTokenUtils.createJwtToken(customerInformationEntity);
            return TokenDto.builder()
                    .accessToken(accessToken)
                    .refreshToken(refreshToken)
                    .build();
        }
        else{
            refreshToken = jwtTokenUtils.createRefreshToken();
            customerInformationEntity.builder()
                    .refreshToken(refreshToken)
                    .build();
        }

        return TokenDto.builder().accessToken(accessToken).refreshToken(refreshToken).build();
    }

    @Transactional
    public TokenDto signUp(LoginRequestDto loginRequestDto) {
        String refreshToken = jwtTokenUtils.createRefreshToken();
        String type="";
        if(loginRequestDto.getLoginType().equals("KAKAO")){
            type = "00";
        }
        else if(loginRequestDto.getLoginType().equals("GOOGLE")){
            type = "01";
        }
        CustomerInformationEntity customerInformationEntity =
                customerInformationRepository.save(
                        CustomerInformationEntity.builder()
                                .nickname(loginRequestDto.getNickname())
                                .userId(loginRequestDto.getUID())
                                .email(loginRequestDto.getEmail())
                                .loginType(type)
                                .refreshToken(refreshToken)
                                .build());
        String accessToken = jwtTokenUtils.createJwtToken(customerInformationEntity);
        return TokenDto.builder().accessToken(accessToken).refreshToken(refreshToken).build();
    }
}