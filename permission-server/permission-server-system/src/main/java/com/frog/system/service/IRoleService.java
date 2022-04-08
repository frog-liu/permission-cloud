package com.frog.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.frog.system.domain.system.Role;

import java.util.List;

/**
 * @author lh
 */
public interface IRoleService extends IService<Role> {

    /**
     * 根据条件查询角色
     * @param role 角色信息
     * @return 符合条件的角色信息
     */
    List<Role> listRole(Role role);

    /**
     * 添加角色
     * @param role 添加的角色信息
     */
    void add(Role role);

    /**
     * 修改角色
     * @param role 修改后的角色信息
     */
    void update(Role role);


    /**
     * 删除角色
     * @param id 角色id
     */
    void deleteById(Long id);

    /**
     * 批量删除角色
     * @param idList 角色id列表
     */
    void batchDeleteById(List<Long> idList);
}
