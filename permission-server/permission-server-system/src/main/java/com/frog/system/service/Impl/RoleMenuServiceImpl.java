package com.frog.system.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.frog.system.domain.system.RoleMenu;
import com.frog.system.mapper.RoleMenuMapper;
import com.frog.system.service.IRoleMenuService;
import org.springframework.stereotype.Service;

/**
 * @author lh
 */
@Service
public class RoleMenuServiceImpl extends ServiceImpl<RoleMenuMapper, RoleMenu> implements IRoleMenuService {
}
