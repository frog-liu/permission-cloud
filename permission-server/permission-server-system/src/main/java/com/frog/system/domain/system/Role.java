package com.frog.system.domain.system;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.frog.common.core.enums.StatusEnum;
import com.frog.system.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author lh
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = false)
@TableName("t_role")
@ApiModel(value = "角色信息")
public class Role extends BaseEntity {

    private static final long serialVersionUID = 1057667736438859814L;

    @ApiModelProperty(value = "主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "角色名")
    @TableField("name")
    private String name;

    @ApiModelProperty(value = "角色描述")
    @TableField("description")
    private String description;

    /**
     * @see StatusEnum
     */
    @ApiModelProperty(value = "状态")
    @TableField("status")
    private Integer status;

}
