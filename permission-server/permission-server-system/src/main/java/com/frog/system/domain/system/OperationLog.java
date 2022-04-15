package com.frog.system.domain.system;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.frog.system.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author lh
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = false)
@TableName("t_operation_log")
@ApiModel("操作日志信息")
@Builder
public class OperationLog extends BaseEntity {

    private static final long serialVersionUID = 3241865226956150894L;

    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty("id")
    private Long id;

    @TableField("description")
    @ApiModelProperty("操作描述")
    private String description;

    @TableField("request_id")
    @ApiModelProperty("请求id")
    private Long requestId;

    @TableField("ip")
    @ApiModelProperty("ip")
    private String ip;

    /**
     * @see com.frog.common.core.enums.StatusEnum
     */
    @TableField("status")
    @ApiModelProperty("状态")
    private Integer status;

}
