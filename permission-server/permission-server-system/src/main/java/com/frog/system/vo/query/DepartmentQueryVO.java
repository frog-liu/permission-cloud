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
@ApiModel(value = "部门查询条件")
public class DepartmentQueryVO extends QueryVO {

    private static final long serialVersionUID = -1565266491149373824L;

    @ApiModelProperty(value = "部门id")
    private Long id;

    @ApiModelProperty(value = "部门名称")
    private String name;

    @ApiModelProperty(value = "状态")
    private Integer status;
}
