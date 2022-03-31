package com.frog.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.frog.system.domain.system.User;

import java.util.List;

/**
 * @author lh
 */
public interface IUserService extends IService<User> {

    /**
     * 查询用户信息
     * @param user 查询条件
     * @return 查询结果
     */
    List<User> listUser(User user);

    /**
     * 添加用户
     * @param user 用户信息
     */
    void add(User user);

    /**
     * 更新用户信息
     * @param user 用户信息
     */
    void update(User user);

    /**
     * 根据id删除用户
     * @param userId
     */
    void deleteById(Long userId);

    /**
     * 批量删除用户
     * @param userIdList 用户id列表
     */
    void batchDeleteById(List<Long> userIdList);
}
