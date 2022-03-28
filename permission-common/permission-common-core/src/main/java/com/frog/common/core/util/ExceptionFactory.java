package com.frog.common.core.util;


import com.frog.common.core.exception.BusinessException;

/**
 * @author lh
 * ExceptionFactory: 异常工厂类
 */
public class ExceptionFactory {

    /**
     * 生成业务异常对象
     * @param message 异常信息
     * @param args 异常信息填充参数
     * @return 业务异常对象
     */
    public static BusinessException be(String message, Object... args) {
        return new BusinessException(String.format(message, args));
    }

    /**
     * 生成业务异常对象
     * @param message 异常信息
     * @param throwable 异常对象
     * @param args 异常信息填充参数
     * @return 业务异常对象
     */
    public static BusinessException be(String message, Throwable throwable, Object... args) {
        return new BusinessException(String.format(message, args), throwable);
    }
}
