package com.frog.system.domain.system;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.frog.system.domain.BaseEntity;
import lombok.Data;

/**
 * @author lh
 */
@Data
@TableName("operation_log")
public class OperationLog extends BaseEntity {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("title")
    private String title;

    @TableField("username")
    private String username;

    @TableField("params")
    private String params;

    @TableField("ip")
    private String ip;
}
