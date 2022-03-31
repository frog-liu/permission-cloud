package com.frog.system.domain.system;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.frog.system.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author lh
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = false)
@TableName("t_role_menu")
public class RoleMenu extends BaseEntity {

    private static final long serialVersionUID = -8935912658055042798L;

    /**
     * 角色id
     */
    @TableField("role_id")
    private Long roleId;

    /**
     * 菜单id
     */
    @TableField("menu_id")
    private Long menuId;
}
