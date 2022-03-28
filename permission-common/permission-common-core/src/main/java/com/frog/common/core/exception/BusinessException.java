package com.frog.common.core.exception;

import lombok.NoArgsConstructor;

/**
 * @author lh
 */
@NoArgsConstructor
public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = -5076026546296022203L;

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(Throwable throwable) {
        super(throwable);
    }

    public BusinessException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
