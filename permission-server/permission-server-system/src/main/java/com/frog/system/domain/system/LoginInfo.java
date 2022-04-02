package com.frog.system.domain.system;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author lh
 */
@Data
@ApiModel("登录信息")
@TableName("t_login_info")
public class LoginInfo implements Serializable {

    private static final long serialVersionUID = -6597217439855410752L;

    @ApiModelProperty("id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("登录帐号")
    @TableField("username")
    private String username;

    /**
     * @see com.frog.common.core.enums.StatusEnum
     */
    @ApiModelProperty("登录状态")
    @TableField("status")
    private Integer status;

    @ApiModelProperty("ip")
    @TableField("ip")
    private String ip;

    @ApiModelProperty("提示消息")
    @TableField("message")
    private String message;

    @ApiModelProperty("登录时间")
    @TableField("login_time")
    private Date loginTime;

    @ApiModelProperty("起始时间")
    @TableField(exist = false)
    private Date startTime;

    @ApiModelProperty("结束时间")
    @TableField(exist = false)
    private Date endTime;

    /**
     * 是否删除
     */
    @TableField("is_delete")
    private boolean delete;
}
