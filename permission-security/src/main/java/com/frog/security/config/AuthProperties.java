package com.frog.security.config;

import lombok.Data;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author lh
 */
@Data
@SpringBootApplication
@ConfigurationProperties(prefix = "auth")
public class AuthProperties {

    /**
     * 验证码配置
     */
    private CaptchaProperties captchaProperties = new CaptchaProperties();

    /**
     * jwt加密签证
     */
    private String jwtAccessKey;

    /**
     * 是否启用jwt令牌
     */
    private Boolean enableJwt;

    /**
     * 社交登录客户端id
     */
    private String socialLoginClientId;
}
