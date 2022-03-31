package com.frog.system.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.frog.system.domain.system.OperationLog;
import com.frog.system.mapper.OperationLogMapper;
import com.frog.system.service.IOperationLogService;
import org.springframework.stereotype.Service;

/**
 * @author lh
 */
@Service
public class OperationLogServiceImpl extends ServiceImpl<OperationLogMapper, OperationLog> implements IOperationLogService {

}
