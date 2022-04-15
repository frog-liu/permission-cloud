package com.frog.common.core.enums;

/**
 * @author lh
 */
public enum GrantType {

    /**
     * 密码模式
     */
    PASSWORD("password"),

    ;

    private String code;

    GrantType(String code) {
        this.code = code;
    }

    public boolean isMatch(String code) {
        return this.code.equalsIgnoreCase(code);
    }
}
