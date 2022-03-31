package com.frog.system.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author lh
 */
@Data
@ApiModel(value = "用户信息")
public class UserVO implements Serializable {

    private static final long serialVersionUID = 3057015608672542694L;

    @ApiModelProperty(value = "用户id")
    private Long id;

    @ApiModelProperty(value = "帐号")
    private String username;

    @ApiModelProperty(value = "用户姓名")
    private String name;

    @ApiModelProperty(value = "手机号")
    private String phone;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "所在部门id")
    private Long departmentId;

    @ApiModelProperty(value = "所在部门名称")
    private String departmentName;

    @ApiModelProperty(value = "性别")
    private Integer sex;

    @ApiModelProperty(value = "性别文本")
    private String sexString;

    @ApiModelProperty(value = "用户编号")
    private String userCode;

    @ApiModelProperty(value = "状态")
    private Integer status;

    @ApiModelProperty(value = "状态文本")
    private String statusString;

}
