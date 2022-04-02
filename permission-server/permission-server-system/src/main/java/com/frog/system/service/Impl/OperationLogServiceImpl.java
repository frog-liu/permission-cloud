package com.frog.system.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.frog.common.core.enums.StatusEnum;
import com.frog.common.core.util.Assert;
import com.frog.system.domain.system.OperationLog;
import com.frog.system.mapper.OperationLogMapper;
import com.frog.system.service.IOperationLogService;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lh
 */
@Service
public class OperationLogServiceImpl extends ServiceImpl<OperationLogMapper, OperationLog> implements IOperationLogService {

    @Override
    public List<OperationLog> listOperationLog(OperationLog operationLog) {
        return lambdaQuery().eq(OperationLog::getStatus, StatusEnum.VALID.getCode())
                .eq(StringUtils.isNotEmpty(operationLog.getTitle()), OperationLog::getTitle, operationLog.getTitle())
                .eq(ObjectUtils.allNotNull(operationLog.getCreateBy()), OperationLog::getCreateBy, operationLog.getCreateBy())
                .eq(StringUtils.isNotEmpty(operationLog.getIp()), OperationLog::getIp, operationLog.getIp())
                .le(ObjectUtils.allNotNull(operationLog.getStartTime()), OperationLog::getCreateTime, operationLog.getStartTime())
                .ge(ObjectUtils.allNotNull(operationLog.getEndTime()), OperationLog::getCreateTime, operationLog.getEndTime())
                .list();
    }

    @Override
    public void batchDeleteById(List<Long> idList) {
        Assert.notEmpty(idList, "批量删除操作日志失败: id列表不能为空!");
        lambdaUpdate().set(OperationLog::getStatus, StatusEnum.INVALID.getCode())
                .eq(OperationLog::getStatus, StatusEnum.VALID.getCode())
                .in(OperationLog::getId, idList)
                .update();
    }

    @Override
    public void clearAll() {
        lambdaUpdate().set(OperationLog::getStatus, StatusEnum.INVALID.getCode())
                .eq(OperationLog::getStatus, StatusEnum.VALID.getCode())
                .update();
    }
}
