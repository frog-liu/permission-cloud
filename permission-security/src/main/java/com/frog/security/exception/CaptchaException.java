package com.frog.security.exception;

/**
 * @author lh
 */
public class CaptchaException extends RuntimeException {

    private static final long serialVersionUID = -7906479485477463338L;

    public CaptchaException(String message) {
        super(message);
    }
}
