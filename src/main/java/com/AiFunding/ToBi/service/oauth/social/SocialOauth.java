package com.AiFunding.ToBi.service.oauth.social;

import com.AiFunding.ToBi.dto.auth.SocialLoginType;

public interface SocialOauth {
    /**
     * 각 Social Login 페이지로 Redirect 처리할 URL Build
     * 사용자로부터 로그인 요청을 받아 Social Login Server 인증용 code 요청
     */
    String getOauthRedirectURL();

    /**
     * API Server로부터 받은 code를 활용하여 사용자 고유 id 반환
     * @param code API Server 에서 받아온 code
     * @return API 서버로 부터 응답받은 Access Token 반환
     */
    String requestAccessToken(String code);

    /**
     * Access Token으로 사용자 정보 요청
     * @param accessToken API Server 에서 받아온 AccessToken
     * @return API 서버로 부터 응답받은 사용자 고유 id 반환
     */
    public String getUserId(String accessToken);

    default SocialLoginType type() {
        if (this instanceof GoogleOauth) {
            return SocialLoginType.GOOGLE;
        } else if (this instanceof KakaoOauth) {
            return SocialLoginType.KAKAO;
        } else {
            return null;
        }
    }
}
