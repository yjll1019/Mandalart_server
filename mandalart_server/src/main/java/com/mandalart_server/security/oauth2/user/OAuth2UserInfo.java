package com.mandalart_server.security.oauth2.user;

import java.util.Map;

public abstract class OAuth2UserInfo {

    protected Map<String, Object> attributes;

    public OAuth2UserInfo(Map<String, Object> attributes){
        this.attributes =attributes;
    }

    public Map<String, Object> getAttributes() {
        return attributes;
    }

    public abstract Long getUserId();

    public abstract String getNickname();

    public abstract String getEmail();


}
