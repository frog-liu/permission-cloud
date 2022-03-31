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
@TableName("t_user_role")
public class UserRole extends BaseEntity {

    private static final long serialVersionUID = -7567111669977924112L;

    /**
     * 用户id
     */
    @TableField("user_id")
    private Long userId;

    /**
     * 角色id
     */
    @TableField("role_id")
    private Long roleId;
}
