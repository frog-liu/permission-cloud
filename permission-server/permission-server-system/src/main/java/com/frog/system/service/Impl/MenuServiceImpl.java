package com.frog.system.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.frog.common.core.enums.StatusEnum;
import com.frog.common.core.util.Assert;
import com.frog.system.domain.system.Menu;
import com.frog.system.enums.MenuType;
import com.frog.system.mapper.MenuMapper;
import com.frog.system.service.IMenuService;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author lh
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {

    @Override
    public List<Menu> listMenu(Menu menu) {
        return lambdaQuery().like(StringUtils.isNotEmpty(menu.getName()), Menu::getName, menu.getName())
                .eq(ObjectUtils.allNotNull(menu.getStatus()), Menu::getStatus, menu.getStatus())
                .orderBy(true, true, Menu::getOrder)
                .list();
    }

    @Override
    public void add(Menu menu) {
        check(menu);
        menu.setId(null);
        this.baseMapper.insert(menu);
    }

    @Override
    public void update(Menu menu) {
        check(menu);
        Assert.notNull(menu.getId(), "更新菜单失败: 菜单id不能为空");
        List<Menu> allChildList = listAllChild(Collections.singletonList(menu.getId()), new LinkedList<>());
        allChildList.forEach(child -> {
            Assert.isTrue(!child.getId().equals(menu.getParentId()), "更新菜单失败: 父类菜单不能指向子级菜单!");
        });
        this.baseMapper.updateById(menu);
    }

    private void check(Menu menu) {
        Assert.notNull(menu, "操作失败: 菜单信息为空!");
        Assert.notEmpty(menu.getName(), "操作失败: 菜单名称不能为空!");
        Assert.notNull(menu.getOrder(), "操作失败: 菜单顺序不能为空!");
        Assert.notNull(menu.getType(), "操作失败: 菜单类型不能为空!");
        Assert.isFalse(MenuType.MENU.isMatch(menu.getType()) && StringUtils.isEmpty(menu.getPath()), "操作失败: 菜单路径不能为空!");
        Assert.notNull(menu.getStatus(), "操作失败: 菜单状态不能为空");
    }

    @Override
    public void deleteById(Long id) {
        Assert.notNull(id, "删除菜单失败: 菜单id不能为空!");
        List<Menu> allChildList = listAllChild(Collections.singletonList(id), new LinkedList<>());
        allChildList.forEach(child -> {
            Assert.isTrue(StatusEnum.INVALID.isMatch(child.getStatus()), "删除菜单失败: 请先将所有子菜单置为无效!");
        });
        lambdaUpdate().set(Menu::getStatus, StatusEnum.INVALID.getCode())
                .eq(Menu::getId, id)
                .update();
    }

    @Override
    public void batchDeleteById(List<Long> idList) {
        Assert.notEmpty(idList, "批量删除菜单失败: 菜单id列表不能为空!");
        List<Menu> allChildList = listAllChild(idList, new LinkedList<>());
        allChildList.forEach(child -> {
            Assert.isTrue(StatusEnum.INVALID.isMatch(child.getStatus()) || idList.contains(child.getId()), "删除菜单失败: 请先将所有子菜单置为无效!");
        });
        lambdaUpdate().set(Menu::getStatus, StatusEnum.INVALID.getCode())
                .in(Menu::getId, idList)
                .update();
    }

    private List<Menu> listAllChild(List<Long> parentIdList, List<Menu> childrenList) {
        if (childrenList == null) {
            childrenList = new LinkedList<>();
        }
        if (!CollectionUtils.isEmpty(parentIdList)) {
            List<Menu> firstChildrenList = lambdaQuery().in(Menu::getParentId, parentIdList).list();
            if (!CollectionUtils.isEmpty(firstChildrenList)) {
                childrenList.addAll(firstChildrenList);
                parentIdList = firstChildrenList.stream().map(Menu::getId).collect(Collectors.toList());
                listAllChild(parentIdList, childrenList);
            }
        }
        return childrenList;
    }

}
