package com.frog.system.domain.system;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.frog.common.core.domain.TreeNode;
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
@ApiModel("菜单信息")
public class Menu extends BaseEntity implements TreeNode {

    private static final long serialVersionUID = 7002190080872161190L;

    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "菜单id")
    private Long id;

    @TableField("parent_id")
    @ApiModelProperty("父菜单id")
    private Long parentId;

    @TableField("name")
    @ApiModelProperty("菜单名称")
    private String name;

    @TableField("order")
    @ApiModelProperty("显示顺序")
    private Integer order;

    /**
     * @see com.frog.system.enums.MenuType
     */
    @TableField("type")
    @ApiModelProperty("菜单类型")
    private Integer type;

    @TableField("path")
    @ApiModelProperty("路由路径")
    private String path;

    @TableField("permission")
    @ApiModelProperty("权限")
    private String permission;

    @TableField("icon")
    @ApiModelProperty("图标")
    private String icon;

    /**
     * @see com.frog.common.core.enums.StatusEnum
     */
    @TableField("status")
    @ApiModelProperty("状态")
    private Integer status;

    @TableField(exist = false)
    @ApiModelProperty("子菜单列表")
    private List<Menu> childrenList;

    @Override
    public boolean isSuperParent() {
        return this.parentId == null || this.parentId.equals(-1L);
    }

    @Override
    public <T extends TreeNode> void addChildren(T treeNode) {
        if (this.childrenList == null) {
            this.childrenList = new LinkedList<>();
        }
        childrenList.add((Menu) treeNode);
    }
}
