package com.frog.system.service.Impl;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import com.frog.system.service.IdService;
import org.springframework.stereotype.Service;

/**
 * @author lh
 */
@Service
public class IdServiceImpl implements IdService {

    private static final Snowflake snowflake = IdUtil.getSnowflake(1L ,1L);

    @Override
    public Long nextId() {
        return snowflake.nextId();
    }
}
