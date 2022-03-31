package com.frog.system.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.frog.system.domain.system.Menu;
import com.frog.system.mapper.MenuMapper;
import com.frog.system.service.IMenuService;
import org.springframework.stereotype.Service;

/**
 * @author lh
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {
}
