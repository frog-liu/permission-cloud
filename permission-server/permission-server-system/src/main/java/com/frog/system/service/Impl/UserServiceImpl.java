package com.frog.system.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.frog.common.core.util.Assert;
import com.frog.system.domain.system.User;
import com.frog.system.mapper.UserMapper;
import com.frog.system.service.IUserService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lh
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Override
    public List<User> listUser(User user) {
        return this.baseMapper.listUser(user);
    }

    @Override
    public void add(User user) {
        checkUser(user);
        this.baseMapper.insert(user);
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
        Assert.notEmpty(user.getUsername(), "操作失败: 用户帐号不能为空!");
        Assert.notEmpty(user.getName(), "操作失败: 用户名称不能为空!");
        Assert.notNull(user.getDepartmentId(), "操作失败: 用户所在部门不能为空!");
        Assert.notNull(user.getSex(), "操作失败: 用户性别不能为空!");
        Assert.notNull(user.getStatus(), "操作失败: 用户状态不能为空!");
        checkUsername(user);
    }

    private void checkUsername(User user) {
        User oldUser = lambdaQuery().eq(User::getUsername, user.getUsername()).one();
        boolean addSuccess = user.getId() == null && oldUser == null;
        Assert.isTrue(addSuccess, "新增用户(%s)失败: 已存在该用户!", user.getUsername());
        boolean updateSuccess = user.getId() != null && (oldUser == null || !oldUser.getId().equals(user.getId()));
        Assert.isTrue(updateSuccess, "更新用户信息失败: 用户username(%s)已存在!", user.getUsername());
    }

}
