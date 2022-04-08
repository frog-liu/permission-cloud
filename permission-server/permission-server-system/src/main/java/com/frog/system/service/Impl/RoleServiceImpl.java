package com.frog.system.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.frog.common.core.enums.StatusEnum;
import com.frog.common.core.util.Assert;
import com.frog.system.domain.system.Role;
import com.frog.system.mapper.RoleMapper;
import com.frog.system.service.IRoleService;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lh
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

    @Override
    public List<Role> listRole(Role role) {
        return lambdaQuery().like(StringUtils.isNotEmpty(role.getName()), Role::getName, role.getName())
                .eq(ObjectUtils.allNotNull(role.getStatus()), Role::getStatus, role.getStatus())
                .list();
    }

    @Override
    public void add(Role role) {
        check(role);
        role.setId(null);
        this.baseMapper.insert(role);
    }

    @Override
    public void update(Role role) {
        Assert.notNull(role.getId(), "修改角色失败: 角色id不能为空!");
        check(role);
        this.baseMapper.updateById(role);
    }

    @Override
    public void deleteById(Long id) {
        lambdaUpdate().set(Role::getStatus, StatusEnum.INVALID.getCode())
                .eq(Role::getId, id)
                .update();
    }

    @Override
    public void batchDeleteById(List<Long> idList) {
        lambdaUpdate().set(Role::getStatus, StatusEnum.INVALID.getCode())
                .in(Role::getId, idList)
                .update();
    }

    private void check(Role role) {
        Assert.notEmpty(role.getName(), "操作失败: 角色名称不能为空!");
        Assert.notNull(role.getStatus(), "操作失败: 角色状态不能为空!");
        checkRoleName(role);
    }

    private void checkRoleName(Role role) {
        Role oldRole = lambdaQuery().eq(Role::getName, role.getName()).one();
        Assert.isFalse(role.getId() == null && oldRole != null, "新增角色(%s)失败: 该角色名称已存在!", role.getName());
        Assert.isFalse(role.getId() != null && !role.getId().equals(oldRole.getId()), "修改角色(%s)失败: 该角色名称已存在!", role.getName());
    }
}
