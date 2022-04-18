package com.frog.system.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.frog.common.core.util.Assert;
import com.frog.system.domain.system.UserRole;
import com.frog.system.mapper.UserRoleMapper;
import com.frog.system.service.IUserRoleService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lh
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements IUserRoleService {

    @Override
    public void batchAdd(Long userId, List<Long> roleIdList) {
        Assert.notNull(userId, "添加用户角色关系失败: 用户id不能为空!");
        Assert.notEmpty(roleIdList, "添加用户角色关系失败: 角色id列表不能为空!");
        roleIdList.forEach(roleId -> {
            UserRole userRole = UserRole.builder()
                    .userId(userId)
                    .roleId(roleId)
                    .build();
            this.baseMapper.insert(userRole);
        });
    }
}
