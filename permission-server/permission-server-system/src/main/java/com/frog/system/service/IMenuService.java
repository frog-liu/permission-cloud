package com.frog.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.frog.system.domain.system.Menu;

import java.util.List;

/**
 * @author lh
 */
public interface IMenuService extends IService<Menu> {

    /**
     * 查询所有菜单
     * @param menu
     * @return
     */
    List<Menu> listMenu(Menu menu);

    /**
     * 新增部门信息
     * @param menu 部门信息
     */
    void add(Menu menu);

    /**
     * 更新菜单
     * @param menu 更新信息
     */
    void update(Menu menu);

    /**
     * 删除菜单
     * @param id 菜单id
     */
    void deleteById(Long id);

    /**
     * 删除菜单
     * @param idList 菜单id
     */
    void batchDeleteById(List<Long> idList);
}
