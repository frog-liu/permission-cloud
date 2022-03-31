package com.frog.system.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * @author lh
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = false)
public class BaseEntity implements Serializable {

    private static final long serialVersionUID = 8162434478191705628L;

    /**
     * 创建人
     */
    @TableField("create_by")
    private Long createBy;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;

    /**
     * 最后更新人
     */
    @TableField("last_update_by")
    private Long lastUpdateBy;

    /**
     * 最后更新时间
     */
    @TableField("last_update_time")
    private Date lastUpdateTime;
}
