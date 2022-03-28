package com.frog.common.core.domain;


import com.frog.common.core.enums.HttpStatus;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author lh
 */
@Getter
@ToString
public class Result<T> implements Serializable {

    private static final long serialVersionUID = -1675318165343472310L;

    private int code;

    private String message;

    private T data;

    private Result(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static<T> Result<T> ok() {
        return ok(null, null);
    }

    public static<T> Result<T> ok(String message) {
        return ok(message, (T) message);
    }

    public static<T> Result<T> ok(T data) {
        return ok(null, data);
    }

    public static<T> Result<T> ok(String message, T data) {
        return new Result<>(HttpStatus.OK.getCode(), message, data);
    }

    public static<T> Result<T> fail(String message) {
        return fail(message, (T) message);
    }

    public static<T> Result<T> fail(T data) {
        return fail(null, data);
    }

    public static<T> Result<T> fail(String message, T data) {
        return new Result<>(HttpStatus.SERVER_ERROR.getCode(), message, data);
    }
}
