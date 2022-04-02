package com.frog.common.core.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

/**
 * @author lh
 */
@Getter
public enum StatusEnum {

    /**
     * 无效
     */
    INVALID(0),

    /**
     * 有效
     */
    VALID(1),

    /**
     * 锁定
     */
    LOCKED(2),

    ;

    @EnumValue
    private Integer code;

    StatusEnum(Integer code) {
        this.code = code;
    }

    public boolean isMatch(Integer code) {
        return this.code.equals(code);
    }
}
