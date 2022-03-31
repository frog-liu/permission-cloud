package com.frog.system.util;

import lombok.extern.slf4j.Slf4j;

/**
 * @author lh
 */
@Slf4j
public class NumberUtils {

    public static Integer toInteger(String number, Integer defaultValue) {
        try {
            return Integer.parseInt(number);
        } catch (NumberFormatException e) {
            log.error("{}转换成Integer类型异常{}", number, e.getMessage());
            return defaultValue;
        }
    }
}
