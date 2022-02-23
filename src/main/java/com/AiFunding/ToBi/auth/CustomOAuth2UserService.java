package com.AiFunding.ToBi.auth;

import com.AiFunding.ToBi.dto.OAuthAttributes;
import com.AiFunding.ToBi.entity.CustomerInformationEntity;
import com.AiFunding.ToBi.mapper.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Collections;

@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    private final UserRepository userRepository;
    private final HttpSession httpSession;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService<OAuth2UserRequest, OAuth2User>
                delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        String registrationId= userRequest.getClientRegistration().getRegistrationId();//어떤 소셜 로그인인지 구별
        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails()
                .getUserInfoEndpoint().getUserNameAttributeName();//로그인 진행 시 키가 되는 필드값. PK와 같은 의미

        OAuthAttributes attributes = OAuthAttributes.of(registrationId, userNameAttributeName,
                                                        oAuth2User.getAttributes());//scope에 따라서 수정

        CustomerInformationEntity customerInformationEntity = saveOrUpdate(attributes);
        httpSession.setAttribute("user", new SessionUser(customerInformationEntity));//세션에 사용자 정보 저장

        return new DefaultOAuth2User(Collections.singleton(new SimpleGrantedAuthority(customerInformationEntity.getRoleKey())),
                                                                                    attributes.getAttributes(),
                                                                                    attributes.getNameAttributeKey());
    }

    private CustomerInformationEntity saveOrUpdate(OAuthAttributes attributes){
        CustomerInformationEntity customerInformationEntity = userRepository.findByEmail(attributes.getEmail())
                .map(entity -> entity.update(attributes.getName()))
                .orElse(attributes.toEntity());

        return userRepository.save(customerInformationEntity);
    }

}
