package com.frog.common.core.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

/**
 * @author lh
 */
@Getter
public enum SexEnum {

    /**
     * 女性
     */
    FEMALE(0),

    /**
     * 男性
     */
    MALE(1),

    /**
     * 未知
     */
    UN_KNOW(2),

    ;

    @EnumValue
    private Integer code;

    SexEnum(Integer code) {
        this.code = code;
    }
}
