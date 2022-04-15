package com.frog.security.filter;

import com.frog.common.core.constant.EndpointConstants;
import com.frog.common.core.constant.SecurityConstants;
import com.frog.common.core.enums.GrantType;
import com.frog.security.service.ICaptchaService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author lh
 */
@Component
@AllArgsConstructor
public class CaptchaFilter extends OncePerRequestFilter {

    private final ICaptchaService captchaService;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        RequestMatcher matcher = new AntPathRequestMatcher(EndpointConstants.OAUTH_TOKEN, HttpMethod.POST.name());
        if (matcher.matches(httpServletRequest)) {
            String grantType = httpServletRequest.getParameter(SecurityConstants.GRANT_TYPE);
            if (GrantType.PASSWORD.isMatch(grantType)) {
                String captchaKey = httpServletRequest.getParameter(SecurityConstants.CAPTCHA_KEY);
                String captchaCode = httpServletRequest.getParameter(SecurityConstants.CAPTCHA_CODE);
                captchaService.check(captchaKey, captchaCode);
            }
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
