package com.frog.system.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author lh
 */
@Data
@ToString
@ApiModel(value = "部门信息")
public class DepartmentVO implements Serializable {

    private static final long serialVersionUID = -7521808741852198352L;

    @ApiModelProperty(value = "部门id")
    private Long id;

    @ApiModelProperty(value = "父部门id")
    private Long parentId;

    @ApiModelProperty(value = "部门名称")
    private String name;

    @ApiModelProperty(value = "部门总监id")
    private Long directorId;

    @ApiModelProperty(value = "部门总监名称")
    private String directorName;

    @ApiModelProperty(value = "首席运营官/首席技术官")
    private Long administratorId;

    @ApiModelProperty(value = "首席运营官/首席技术官名称")
    private String administratorName;

    @ApiModelProperty(value = "状态")
    private Integer status;
}
