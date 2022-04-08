package com.frog.system.controller;

import com.frog.common.core.domain.Result;
import com.frog.system.constant.ApiConstants;
import com.frog.system.domain.system.Role;
import com.frog.system.service.IRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @author lh
 */
@Api(value = ApiConstants.ROLE, tags = "角色信息")
@RestController
@RequestMapping(value = ApiConstants.ROLE)
public class RoleController extends BaseController {

    @Autowired
    private IRoleService roleService;

    @ApiOperation(value = "角色列表", httpMethod = "GET")
    @PreAuthorize("hasAuthority('system:role:list')")
    @GetMapping(ApiConstants.LIST)
    public Result<List<Role>> listRole(Role role) {
        startPage();
        List<Role> roleList = roleService.listRole(role);
        return Result.ok(roleList);
    }

    @ApiOperation(value = "新增角色", httpMethod = "POST")
    @PreAuthorize("hasAuthority('system:role:add')")
    @PostMapping(value = "")
    public Result addRole(Role role) {
        roleService.add(role);
        return Result.ok();
    }

    @ApiOperation(value = "修改角色", httpMethod = "PUT")
    @PreAuthorize("hasAuthority('system:role:update')")
    @PutMapping(value = "")
    public Result updateRole(Role role) {
        roleService.update(role);
        return Result.ok();
    }

    @ApiOperation(value = "删除角色", httpMethod = "DELETE")
    @PreAuthorize("hasAuthority('system:role:delete')")
    @DeleteMapping(value = "")
    public Result deleteById(Long id) {
        roleService.deleteById(id);
        return Result.ok();
    }

    @ApiOperation(value = "批量删除角色", httpMethod = "DELETE")
    @PreAuthorize("hasAuthority('system:role:delete')")
    @DeleteMapping(value = ApiConstants.LIST)
    public Result batchDeleteById(List<Long> idList) {
        roleService.batchDeleteById(idList);
        return Result.ok();
    }

}
