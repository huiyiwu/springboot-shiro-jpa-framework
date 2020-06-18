package com.huchx.security.shiro;

import com.huchx.ApiContstants;
import org.apache.shiro.authc.AuthenticationToken;

import java.util.Calendar;

public class ShiroAuthToken implements AuthenticationToken {

    private String token;
    private String userId;
    private Long validTimeMillons;

    public ShiroAuthToken() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.SECOND, ApiContstants.SESSION_TIMEOUT_SECONDS);
        this.validTimeMillons = c.getTime().getTime();
    }

    public ShiroAuthToken(String token, String userId) {
        this.token = token;
        this.userId = userId;
        Calendar c = Calendar.getInstance();
        c.add(Calendar.SECOND, ApiContstants.SESSION_TIMEOUT_SECONDS);
        this.validTimeMillons = c.getTime().getTime();
    }

    public Long getValidTimeMillons() {
        return validTimeMillons;
    }

    public void setValidTimeMillons(Long validTimeMillons) {
        this.validTimeMillons = validTimeMillons;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public Object getPrincipal() {
        return this;
    }

    @Override
    public Object getCredentials() {
        return null;
    }
}
