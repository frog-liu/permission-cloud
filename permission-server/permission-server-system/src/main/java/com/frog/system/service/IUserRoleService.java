package com.frog.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.frog.system.domain.system.UserRole;

import java.util.List;

/**
 * @author lh
 */
public interface IUserRoleService extends IService<UserRole> {

    /**
     * 批量添加用户角色关系
     * @param userId 用户id
     * @param roleIdList 角色id列表
     */
    void batchAdd(Long userId, List<Long> roleIdList);
}
