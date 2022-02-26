package com.AiFunding.ToBi.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()//Rest API를 사용하기 때문에 CSRF 비활성화
                .headers().frameOptions().disable()//X-Frame-Options 비활성화
                .and()
                    .authorizeRequests() // URL별 권한 권리
                    .antMatchers("/").permitAll()
                    .anyRequest().authenticated()//그 외 요청에는 로그인이 필요
                .and()
                    .logout()
                        .logoutSuccessUrl("/")//로그아웃하면 로그인 페이지로 이동
                .and()
                    .oauth2Login()
                        .userInfoEndpoint()//로그인 성공 이후 설정 시작
                            .userService(customOAuth2UserService);
        super.configure(http);
    }
}
