package com.frog.system.util;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.frog.common.core.constant.HttpConstants;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author lh
 */
@Slf4j
public class ServletUtils {

    private static final String UNKNOWN = "unknown";

    /**
     * 根据paraName获取值
     * @param paramName
     * @return
     */
    public static Integer getParameterToInt(String paramName) {
        HttpServletRequest request = getHttpServletRequest();
        String value = request.getParameter(paramName);
        return NumberUtils.toInteger(value, null);
    }

    private static HttpServletRequest getHttpServletRequest() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes instanceof ServletRequestAttributes) {
            ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) requestAttributes;
            return servletRequestAttributes.getRequest();
        }
        return null;
    }

    /**
     * 获取客户端真实ip
     * @see HttpConstants.HeaderIp
     * @return ip
     */
    public static String getRealIp() {
        HttpServletRequest request = getHttpServletRequest();
        String ip = request.getHeader(HttpConstants.HeaderIp.X_FORWARDED_FOR);
        log.info("X-Forwarded-For:{}", ip);
        if (isValidIp(ip)) {
            if (ip.indexOf(StringPool.COMMA) != -1) {
                ip = ip.split(StringPool.COMMA)[0];
            }
        }
        if (!isValidIp(ip)) {
            ip = request.getHeader(HttpConstants.HeaderIp.PROXY_CLIENT_IP);
            log.info("Proxy-Client-IP:{}", ip);
        }
        if (!isValidIp(ip)) {
            ip = request.getHeader(HttpConstants.HeaderIp.WL_PROXY_CLIENT_IP);
            log.info("WL-Proxy-Client-IP:{}", ip);
        }
        if (!isValidIp(ip)) {
            ip = request.getHeader(HttpConstants.HeaderIp.HTTP_CLIENT_IP);
            log.info("HTTP_CLIENT_IP:{}", ip);
        }
        if (!isValidIp(ip)) {
            ip = request.getHeader(HttpConstants.HeaderIp.HTTP_X_FORWARDED_FOR);
            log.info("HTTP_X_FORWARDED_FOR:{}", ip);
        }
        if (!isValidIp(ip)) {
            ip = request.getHeader(HttpConstants.HeaderIp.X_REAL_IP);
            log.info("X-Real-IP:{}", ip);
        }
        if (!isValidIp(ip)) {
            ip = request.getRemoteAddr();
            log.info("Remote Address:{}", ip);
        }
        return ip;
    }

    private static boolean isValidIp(String ip) {
        return !StringUtils.isBlank(ip) && !StringUtils.equalsIgnoreCase(UNKNOWN, ip);
    }
}
