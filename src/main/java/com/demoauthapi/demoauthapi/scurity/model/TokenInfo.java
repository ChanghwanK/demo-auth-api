package com.demoauthapi.demoauthapi.scurity.model;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

@Getter
public class TokenInfo {
    private final String accessToken;
    private final String refreshToken;
    private final String expireTime;

    private TokenInfo(String accessToken, String refreshToken, int expireTime) {
        if (StringUtils.isBlank(accessToken)) throw new IllegalArgumentException("accessToken is blank");
        if (StringUtils.isBlank(refreshToken)) throw new IllegalArgumentException("refreshToken is blank");
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.expireTime = expireTime + "s";
    }

    public static TokenInfo of(String accessToken, String refreshToken, int expireTime) {
        return new TokenInfo(accessToken, refreshToken, expireTime);
    }
}
