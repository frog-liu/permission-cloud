package com.frog.system.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.frog.system.domain.system.UserRole;
import com.frog.system.mapper.UserRoleMapper;
import com.frog.system.service.IUserRoleService;
import org.springframework.stereotype.Service;

/**
 * @author lh
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements IUserRoleService {
}
