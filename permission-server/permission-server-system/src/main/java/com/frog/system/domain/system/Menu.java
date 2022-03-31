package com.frog.system.domain.system;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.frog.common.core.enums.StatusEnum;
import com.frog.system.domain.BaseEntity;
import com.frog.system.enums.MenuType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author lh
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = false)
public class Menu extends BaseEntity {

    private static final long serialVersionUID = 7002190080872161190L;

    /**
     * 菜单id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 父菜单id
     */
    @TableField("parent_id")
    private Long parentId;

    /**
     * 菜单名称
     */
    @TableField("name")
    private String name;

    /**
     * 显示顺序
     */
    @TableField("order")
    private Integer order;

    /**
     * 菜单类型
     */
    @TableField("type")
    private MenuType type;

    /**
     * 路由路径
     */
    @TableField("path")
    private String path;

    /**
     * 权限
     */
    @TableField("permission")
    private String permission;

    /**
     * 图标
     */
    @TableField("icon")
    private String icon;

    /**
     * 状态
     */
    @TableField("status")
    private StatusEnum status;

}
