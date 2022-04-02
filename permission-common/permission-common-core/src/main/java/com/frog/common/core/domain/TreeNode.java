package com.frog.common.core.domain;

/**
 * @author lh
 */
public interface TreeNode {

    /**
     * 是否为最高级父类
     * @return
     */
    boolean isSuperParent();

    /**
     * 获取id
     * @return id
     */
    Long getId();

    /**
     * 获取父类id
     * @return 父类id
     */
    Long getParentId();

    /**
     * 添加子集
     * @param treeNode 字节点
     */
    <T extends TreeNode> void addChildren(T treeNode);
}
