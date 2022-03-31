package com.frog.system.vo.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * @author lh
 */
@Data
@ToString
@ApiModel(value = "用户查询条件")
public class UserQueryVO extends QueryVO {

    private static final long serialVersionUID = -1565266491149373824L;

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

    @ApiModelProperty(value = "用户编号")
    private String userCode;

    @ApiModelProperty(value = "status")
    private Integer status;
}
