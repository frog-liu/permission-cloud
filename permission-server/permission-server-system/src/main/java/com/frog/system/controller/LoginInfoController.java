package com.frog.system.controller;

import com.frog.common.core.domain.PageData;
import com.frog.common.core.domain.Result;
import com.frog.common.core.constant.ApiConstants;
import com.frog.system.domain.system.LoginInfo;
import com.frog.system.service.ILoginInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @author lh
 */
@Api(value = ApiConstants.USER, tags = "用户信息")
@RestController
@RequestMapping(value = ApiConstants.LOGIN_INFO)
public class LoginInfoController extends BaseController {

    @Autowired
    private ILoginInfoService loginInfoService;

    @ApiOperation(value = "登录日志列表", httpMethod = "GET")
    @PreAuthorize("hasAuthority('system:loginInfo:list')")
    @GetMapping(ApiConstants.LIST)
    public Result<PageData<LoginInfo>> listLoginInfo(LoginInfo loginInfo) {
        startPage();
        List<LoginInfo> loginInfoList = loginInfoService.listLoginInfo(loginInfo);
        return Result.ok(PageData.getInstance(loginInfoList));
    }

    @ApiOperation(value = "批量删除", httpMethod = "DELETE")
    @PreAuthorize("hasAuthority('system:loginInfo:delete')")
    @DeleteMapping(value = ApiConstants.LIST)
    public Result batchDeleteById(List<Long> idList) {
        loginInfoService.batchDeleteById(idList);
        return Result.ok();
    }

    @ApiOperation(value = "登录日志列表", httpMethod = "DELETE")
    @PreAuthorize("hasAuthority('system:loginInfo:delete')")
    @DeleteMapping(value = "/clear")
    public Result clearAll() {
        loginInfoService.clearAll();
        return Result.ok();
    }

}
