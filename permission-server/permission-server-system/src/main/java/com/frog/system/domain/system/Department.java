package com.frog.system.domain.system;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.frog.common.core.domain.TreeNode;
import com.frog.common.core.enums.StatusEnum;
import com.frog.system.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.LinkedList;
import java.util.List;

/**
 * @author lh
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = false)
@TableName("t_department")
@ApiModel("部门信息")
public class Department extends BaseEntity implements TreeNode {

    private static final long serialVersionUID = 4431553929045715928L;

    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "id")
    private Long id;

    @TableField("parent_id")
    @ApiModelProperty(value = "上级部门id")
    private Long parentId;

    @TableField("name")
    @ApiModelProperty(value = "部门名称")
    private String name;

    @TableField("director_id")
    @ApiModelProperty(value = "部门总监id")
    private Long directorId;

    @TableField("administrator_id")
    @ApiModelProperty(value = "首席运营官/首席技术官")
    private Long administratorId;

    /**
     * @see StatusEnum
     */
    @TableField("status")
    @ApiModelProperty(value = "状态")
    private Integer status;

    @TableField(exist = false)
    @ApiModelProperty("子菜单列表")
    private List<Department> childrenList;

    @Override
    public boolean isSuperParent() {
        return this.parentId == null || this.parentId.equals(-1L);
    }

    @Override
    public <T extends TreeNode> void addChildren(T treeNode) {
        if (this.childrenList == null) {
            this.childrenList = new LinkedList<>();
        }
        childrenList.add((Department) treeNode);
    }
}
