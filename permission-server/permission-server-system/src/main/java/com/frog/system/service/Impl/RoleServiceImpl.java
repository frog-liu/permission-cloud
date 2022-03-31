package com.frog.system.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.frog.system.domain.system.Role;
import com.frog.system.mapper.RoleMapper;
import com.frog.system.service.IRoleService;
import org.springframework.stereotype.Service;

/**
 * @author lh
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {
}
