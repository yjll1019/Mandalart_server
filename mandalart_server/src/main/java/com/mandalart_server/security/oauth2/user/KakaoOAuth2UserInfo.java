package com.mandalart_server.security.oauth2.user;

import java.util.Map;

public class KakaoOAuth2UserInfo extends OAuth2UserInfo {

    public KakaoOAuth2UserInfo(Map<String, Object> attributes){
        super(attributes);
    }

    @Override
    public Long getUserId(){
        return (Long) attributes.get("userId");
    }

    @Override
    public String getNickname() {
        return (String) attributes.get("nickname");
    }

    @Override
    public String getEmail(){
        return (String) attributes.get("email");
    }


}
