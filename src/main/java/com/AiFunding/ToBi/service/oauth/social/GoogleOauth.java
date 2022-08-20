package com.AiFunding.ToBi.service.oauth.social;

import com.AiFunding.ToBi.dto.auth.GoogleDto;
import com.AiFunding.ToBi.dto.auth.TokenDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Exchanger;
import java.util.stream.Collectors;

@Component
@Slf4j//logging Facade
public class GoogleOauth implements SocialOauth {
    @Value("${spring.security.oauth2.client.registration.google.base-url}")
    private String GOOGLE_SNS_BASE_URL;
    @Value("${spring.security.oauth2.client.registration.google.client-id}")
    private String GOOGLE_SNS_CLIENT_ID;
    @Value("${spring.security.oauth2.client.registration.google.callback-url}")
    private String GOOGLE_SNS_CALLBACK_URL;
    @Value("${spring.security.oauth2.client.registration.google.client-secret}")
    private String GOOGLE_SNS_CLIENT_SECRET;
    @Value("${spring.security.oauth2.client.registration.google.token-base-url}")
    private String GOOGLE_SNS_TOKEN_BASE_URL;
    @Value("https://www.googleapis.com/oauth2/v1/")
    private String GOOGLE_SNS_TOKEN_GET_URL;

    @Override
    public String getOauthRedirectURL() {
        Map<String, Object> params = new HashMap<>();
        params.put("scope", "profile");
        params.put("response_type", "code");
        params.put("client_id", GOOGLE_SNS_CLIENT_ID);
        params.put("redirect_uri", GOOGLE_SNS_CALLBACK_URL);

        String parameterString = params.entrySet().stream()
                .map(x -> x.getKey() + "=" + x.getValue())
                .collect(Collectors.joining("&"));

        return GOOGLE_SNS_BASE_URL + "?" + parameterString;
    }

    @Override
    public String requestAccessToken(String code) {
        ObjectMapper mapper = new ObjectMapper();
        RestTemplate restTemplate = new RestTemplate();
        Map<String,String> responseMap = new HashMap<>();
        Map<String, Object> params = new HashMap<>();
        params.put("code", code);
        params.put("client_id", GOOGLE_SNS_CLIENT_ID);
        params.put("client_secret", GOOGLE_SNS_CLIENT_SECRET);
        params.put("redirect_uri", GOOGLE_SNS_CALLBACK_URL);
        params.put("grant_type", "authorization_code");

        ResponseEntity<String> responseEntity =
                restTemplate.postForEntity(GOOGLE_SNS_TOKEN_BASE_URL, params, String.class);

        try{
            responseMap = mapper.readValue(responseEntity.getBody(),Map.class);
        }catch (IOException e){
            e.printStackTrace();
        }

        System.out.println(responseEntity.getBody());
        String jwtToken = responseMap.get("access_token");
        log.info(jwtToken);
        String requestUrl = UriComponentsBuilder.fromHttpUrl(GOOGLE_SNS_TOKEN_GET_URL + "/tokeninfo").queryParam("access_token", jwtToken).toUriString();
        String responseJson = restTemplate.getForObject(requestUrl,String.class);
        log.info(responseJson);

        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            return jwtToken;
        }
         return "구글 로그인 요청 처리 실패";
    }

    @Override
    public String getUserId(String accessToken) {

        String GOOGLE_USERINFO_REQUEST_URL="https://www.googleapis.com/oauth2/v1/userinfo";

        RestTemplate restTemplate =new RestTemplate();
        //header에 accessToken을 담는다.
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization","Bearer "+accessToken);
        //restTemplate.exchange(GOOGLE_USERINFO_REQUEST_URL, HttpMethod.GET,request,String.class);
        //HttpEntity를 하나 생성해 헤더를 담아서 restTemplate으로 구글과 통신하게 된다.
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity(headers);
        ResponseEntity<GoogleDto> response=restTemplate.exchange(GOOGLE_USERINFO_REQUEST_URL, HttpMethod.GET,request,GoogleDto.class);
        System.out.println("response.getBody() = " + response.getBody());

        return response.getBody().getId();
    }
}
