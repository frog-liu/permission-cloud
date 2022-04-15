package com.frog.system.controller;

import com.frog.common.core.domain.PageData;
import com.frog.common.core.domain.Result;
import com.frog.common.core.constant.ApiConstants;
import com.frog.system.domain.system.OperationLog;
import com.frog.system.service.IOperationLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * @author lh
 */
@Api(value = ApiConstants.OPERATION_LOG, tags = "操作日志")
@RestController
@RequestMapping(value = ApiConstants.OPERATION_LOG)
public class OperationLogController extends BaseController {

    @Autowired
    private IOperationLogService operationLogService;

    @ApiOperation(value = "操作日志列表", httpMethod = "GET")
    @PreAuthorize("hasAuthority('system:operationLog:list')")
    @GetMapping(ApiConstants.LIST)
    public Result<PageData<OperationLog>> listOperationLog(OperationLog operationLog) {
        startPage();
        List<OperationLog> operationLogList = operationLogService.listOperationLog(operationLog);
        return Result.ok(PageData.getInstance(operationLogList));
    }

    @ApiOperation(value = "批量删除操作日志", httpMethod = "DELETE")
    @PreAuthorize("hasAuthority('system:operationLog:delete')")
    @DeleteMapping(value = ApiConstants.LIST)
    public Result batchDeleteById(List<Long> idList) {
        operationLogService.batchDeleteById(idList);
        return Result.ok();
    }

    @ApiOperation(value = "清除所有操作日志", httpMethod = "DELETE")
    @PreAuthorize("hasAuthority('system:operationLog:delete')")
    @DeleteMapping(value = "/clear")
    public Result clearAll() {
        operationLogService.clearAll();
        return Result.ok();
    }

}
