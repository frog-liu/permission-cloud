package com.frog.security.service.Impl;

import cn.hutool.captcha.AbstractCaptcha;
import cn.hutool.captcha.CaptchaUtil;
import com.frog.common.core.constant.RedisKey;
import com.frog.common.core.enums.ImageType;
import com.frog.common.core.util.Assert;
import com.frog.common.redis.service.RedisService;
import com.frog.security.config.AuthProperties;
import com.frog.security.config.CaptchaProperties;
import com.frog.security.exception.CaptchaException;
import com.frog.security.service.ICaptchaService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @author lh
 */
@Service
@RequiredArgsConstructor
public class CaptchaService implements ICaptchaService {

    private static final String CAPTCHA_KEY = "captchaKey";

    private static final String NO_CACHE = "No-cache";

    private final RedisService redisService;

    private final AuthProperties authProperties;

    @Override
    public void create(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 获取前端生成的验证码key uuid并校验不能为空
        String captchaKey = request.getParameter(CAPTCHA_KEY);
        Assert.notEmpty(captchaKey, "验证码key不能为空!");
        // 根据验证码配置生成验证码
        CaptchaProperties captchaProperties = authProperties.getCaptchaProperties();
        // 返回头设置
        setHeader(response, captchaProperties.getImageType());
        AbstractCaptcha captcha = createCaptcha(captchaProperties);
        String captchaCode = captcha.getCode().toLowerCase();
        Long expireTime = captchaProperties.getExpireTime();
        // 验证码存放到redis缓存中,并设置过期时间
        redisService.set(RedisKey.Auth.CAPTCHA_PREFIX + captchaKey, captchaCode, expireTime, TimeUnit.SECONDS);
        // 验证码写入返回中
        captcha.write(response.getOutputStream());
    }

    @Override
    public void check(String captchaKey, String captchaCode) {
        Assert.notEmpty(captchaKey, "验证码Key不能为空!");
        Assert.notEmpty(captchaCode, "验证码不能为空!");
        String rightCaptchaCode = redisService.get(RedisKey.Auth.CAPTCHA_PREFIX + captchaKey);
        if (StringUtils.isBlank(rightCaptchaCode)) {
            throw new CaptchaException("验证码已过期!");
        }
        if (!StringUtils.equalsIgnoreCase(rightCaptchaCode, captchaCode)) {
            throw new CaptchaException("验证码错误!");
        }
    }

    private AbstractCaptcha createCaptcha(CaptchaProperties captchaProperties) {
        int width = captchaProperties.getWidth();
        int height = captchaProperties.getHeight();
        int length = captchaProperties.getLength();
        ImageType type = captchaProperties.getImageType();
        switch (type) {
            case GIF:
                return CaptchaUtil.createGifCaptcha(width, height, length);
            default:
                return CaptchaUtil.createCircleCaptcha(width, height, length, length);
        }
    }

    private void setHeader(HttpServletResponse response, ImageType imageType) {
        // 设置返回对象媒体类型
        switch (imageType) {
            case GIF:
                response.setContentType(MediaType.IMAGE_GIF_VALUE);
                break;
            default:
                response.setContentType(MediaType.IMAGE_PNG_VALUE);
        }
        // 禁止缓存
        response.setHeader(HttpHeaders.CACHE_CONTROL, NO_CACHE);
        response.setHeader(HttpHeaders.PRAGMA, NO_CACHE);
        response.setDateHeader(HttpHeaders.EXPIRES, 0l);
    }
}
