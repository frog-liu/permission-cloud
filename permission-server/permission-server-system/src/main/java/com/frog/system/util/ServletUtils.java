package com.frog.system.util;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author lh
 */
public class ServletUtils {

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
}
