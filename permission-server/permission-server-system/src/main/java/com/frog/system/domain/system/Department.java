package com.frog.system.domain.system;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.frog.common.core.enums.StatusEnum;
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
@TableName("t_department")
public class Department extends BaseEntity {

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 父菜单id
     */
    @TableField("parent_id")
    private Long parentId;

    /**
     * 部门名称
     */
    @TableField("name")
    private String name;

    /**
     * 部门总监id
     */
    @TableField("director_id")
    private Long directorId;

    /**
     * 首席运营官/首席技术官
     */
    @TableField("administrator_id")
    private Long administratorId;

    /**
     * 状态
     */
    @TableField("status")
    private StatusEnum status;

}
