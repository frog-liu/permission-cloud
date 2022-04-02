package com.frog.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.frog.system.domain.system.OperationLog;

import java.util.List;


/**
 * @author lh
 */
public interface IOperationLogService extends IService<OperationLog> {

    /**
     * 查询操作日志列表
     * @param operationLog 操作日志
     * @return 操作日志列表
     */
    List<OperationLog> listOperationLog(OperationLog operationLog);

    /**
     * 批量删除操作日志
     * @param idList 删除id列表
     */
    void batchDeleteById(List<Long> idList);

    /**
     * 清除所有操作日志
     */
    void clearAll();
}
