package com.frog.system.service.Impl;

import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.frog.common.core.enums.StatusEnum;
import com.frog.common.core.util.Assert;
import com.frog.system.domain.system.Role;
import com.frog.system.domain.system.User;
import com.frog.system.mapper.UserMapper;
import com.frog.system.service.IRoleService;
import com.frog.system.service.IUserRoleService;
import com.frog.system.service.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lh
 */
@Service
@AllArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    private final IRoleService roleService;

    private final IUserRoleService userRoleService;

    @Override
    public List<User> listUser(User user) {
        return this.baseMapper.listUser(user);
    }

    @Override
    public void add(User user) {
        user.setId(null);
        checkUser(user);
        Long userId = Long.valueOf(this.baseMapper.insert(user));
        userRoleService.batchAdd(userId, user.getRoleIdList());
    }

    @Override
    public void update(User user) {
        Assert.notNull(user.getId(), "更新用户信息失败: 用户id不能为空!");
        checkUser(user);
        this.baseMapper.updateById(user);
    }

    @Override
    public void deleteById(Long userId) {
        Assert.notNull(userId, "删除用户失败: 用户id不能为空!");
        this.baseMapper.deleteById(userId);
    }

    @Override
    public void batchDeleteById(List<Long> userIdList) {
        Assert.notEmpty(userIdList, "批量删除用户失败: 用户id列表不能为空!");
        this.baseMapper.deleteBatchIds(userIdList);
    }

    private void checkUser(User user) {
        Assert.notNull(user, "操作失败: 用户信息不能为空!");
        Assert.notEmpty(user.getUsername(), "操作失败: 用户帐号不能为空!");
        Assert.notEmpty(user.getName(), "操作失败: 用户名称不能为空!");
        Assert.notNull(user.getDepartmentId(), "操作失败: 用户所在部门不能为空!");
        Assert.notNull(user.getSex(), "操作失败: 用户性别不能为空!");
        Assert.notNull(user.getStatus(), "操作失败: 用户状态不能为空!");
        Assert.notEmpty(user.getRoleIdList(), "操作失败: 用户角色不能为空!");
        checkUsername(user);
        checkUserRole(user);
    }

    private void checkUsername(User user) {
        User oldUser = lambdaQuery()
                .eq(User::getUsername, user.getUsername())
                .notIn(ObjectUtils.isNotNull(user.getId()), User::getId, user.getId())
                .one();
        Assert.isNull(oldUser, "操作失败: 改用户(%s)已存在!", user.getUsername());
    }

    private void checkUserRole(User user) {
        List<Role> roleList = roleService.listRole(user.getRoleIdList());
        roleList.forEach(role -> {
            Assert.isTrue(StatusEnum.VALID.isMatch(role.getStatus()), "操作失败: 该用户角色(%s)已无效,请刷新页面重试!", role.getName());
        });
    }

}
