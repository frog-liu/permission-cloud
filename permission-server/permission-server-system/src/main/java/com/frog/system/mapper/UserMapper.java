package com.frog.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.frog.system.domain.system.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author lh
 */
public interface UserMapper extends BaseMapper<User> {

    /**
     * 查询用户信息
     * @param user 查询条件
     * @return 查询结果
     */
    List<User> listUser(@Param("user") User user);
}
