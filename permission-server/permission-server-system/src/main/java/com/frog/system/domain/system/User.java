package com.frog.system.domain.system;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.frog.common.core.enums.SexEnum;
import com.frog.common.core.enums.StatusEnum;
import com.frog.system.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

/**
 * @author lh
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = false)
@TableName("t_user")
@ApiModel("用户信息")
public class User extends BaseEntity {

    private static final long serialVersionUID = 3005964765383248373L;

    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty("用户id")
    private Long id;

    @TableField("username")
    @ApiModelProperty("企业微信用户账号")
    private String username;

    @TableField("name")
    @ApiModelProperty("用户姓名")
    private String name;

    @TableField("password")
    private String password;

    @TableField("phone")
    @ApiModelProperty("手机号")
    private String phone;

    @TableField("email")
    @ApiModelProperty("邮箱")
    private String email;

    @TableField("department_id")
    @ApiModelProperty("所在部门id")
    private Long departmentId;

    @TableField(exist = false)
    @ApiModelProperty("所在部门名称")
    private String departmentName;

    /**
     * @see SexEnum
     */
    @TableField("sex")
    @ApiModelProperty("性别")
    private Integer sex;

    @TableField("avatar")
    @ApiModelProperty("头像url")
    private String avatar;

    /**
     * @see StatusEnum
     */
    @TableField("status")
    @ApiModelProperty("状态")
    private Integer status;

    @ApiModelProperty("角色id列表")
    private List<Long> roleIdList;

}
