package com.frog.security.service;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author lh
 */
public interface ICaptchaService {

    /**
     * 创建验证码
     * @param request 请求
     * @param response 返回
     */
    void create(HttpServletRequest request, HttpServletResponse response) throws IOException;

    /**
     * 校验验证码
     * @param captchaKey 校验key
     * @param captchaCode 验证码code
     */
    void check(String captchaKey, String captchaCode);
}
