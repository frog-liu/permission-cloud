package com.frog.system.controller;

import com.frog.common.core.domain.PageData;
import com.frog.common.core.domain.Result;
import com.frog.system.constant.ApiConstants;
import com.frog.system.domain.system.User;
import com.frog.system.service.IUserService;
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
@RequestMapping(value = ApiConstants.USER)
public class UserController extends BaseController {

    @Autowired
    private IUserService userService;

    @GetMapping(value = ApiConstants.LIST)
    @ApiOperation(value = "用户信息列表", httpMethod = "GET")
    @PreAuthorize("hasAuthority('system:user:list')")
    public Result<PageData<User>> listUser(User user) {
        startPage();
        List<User> userList = userService.listUser(user);
        return Result.ok(PageData.getInstance(userList));
    }

    @PostMapping(value = "")
    @ApiOperation(value = "新增用户", httpMethod = "POST")
    public Result addUser(User user) {
        userService.add(user);
        return Result.ok();
    }

    @PutMapping(value = "")
    @ApiOperation(value = "更新用户", httpMethod = "PUT")
    public Result update(User user) {
        this.userService.update(user);
        return Result.ok();
    }

    @DeleteMapping(value = "")
    @ApiOperation(value = "批量删除用户", httpMethod = "DELETE")
    public Result delete(List<Long> userIdList) {
        this.userService.batchDeleteById(userIdList);
        return Result.ok();
    }


}
