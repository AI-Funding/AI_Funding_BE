package com.AiFunding.ToBi.service.oauth.social;

import com.AiFunding.ToBi.dto.auth.TokenDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
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
        RestTemplate restTemplate = new RestTemplate();
        String requestUrl = UriComponentsBuilder.fromHttpUrl(GOOGLE_SNS_TOKEN_BASE_URL + "/tokeninfo").queryParam("id_token", accessToken).toUriString();
        String responseJson = restTemplate.getForObject(requestUrl,String.class);
        JsonParser parser = new JsonParser();
        JsonElement element = parser.parse(responseJson);
        String userId = element.getAsJsonObject().get("sub").getAsString();
        return userId;
    }
}
