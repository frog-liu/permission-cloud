package com.frog.system.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

/**
 * @author lh
 */
@Getter
public enum MenuType {

    /**
     * 目录
     */
    CONTENT(0),

    /**
     * 菜单
     */
    MENU(1),

    /**
     * 按钮
     */
    BUTTON(2),

    ;

    @EnumValue
    private Integer code;

    MenuType(Integer code) {
        this.code = code;
    }
}
