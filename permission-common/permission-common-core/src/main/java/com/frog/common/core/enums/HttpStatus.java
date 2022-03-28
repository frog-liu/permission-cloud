package com.frog.common.core.enums;

import lombok.Getter;

/**
 * @author lh
 */
@Getter
public enum HttpStatus {

    /**
     * 成功
     */
    OK(200),

    /**
     * 服务异常
     */
    SERVER_ERROR(500),

    ;

    private Integer code;

    HttpStatus(Integer code) {
        this.code = code;
    }
}
