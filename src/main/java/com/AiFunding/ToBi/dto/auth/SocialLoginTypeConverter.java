package com.AiFunding.ToBi.dto.auth;


import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import com.AiFunding.ToBi.dto.auth.SocialLoginType;

@Configuration
public class SocialLoginTypeConverter implements Converter<String, SocialLoginType> {
    @Override
    public SocialLoginType convert(String source) {
        return SocialLoginType.valueOf(source.toUpperCase());
    }
}
