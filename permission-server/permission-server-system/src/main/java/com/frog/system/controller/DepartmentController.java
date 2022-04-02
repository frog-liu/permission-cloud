package com.frog.system.controller;

import com.frog.common.core.domain.Result;
import com.frog.common.core.util.TreeUtils;
import com.frog.system.constant.ApiConstants;
import com.frog.system.domain.system.Department;
import com.frog.system.domain.system.Menu;
import com.frog.system.service.IDepartmentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author lh
 */
@Api(value = ApiConstants.DEPARTMENT, tags = "部门信息")
@RestController
@RequestMapping(value = ApiConstants.DEPARTMENT)
public class DepartmentController extends BaseController {

    @Autowired
    private IDepartmentService departmentService;

    @ApiOperation(value = "树状部门列表", httpMethod = "GET")
    @PreAuthorize("hasAuthority('system:department:tree')")
    @GetMapping(ApiConstants.TREE)
    public Result<List<Department>> listDepartmentTree(Department department) {
        List<Department> departmentList = departmentService.listDepartment(department);
        departmentList = (List<Department>) TreeUtils.build(departmentList);
        return Result.ok(departmentList);
    }

    @ApiOperation(value = "新增部门", httpMethod = "POST")
    @PreAuthorize("hasAuthority('system:department:add')")
    @PostMapping(value = "")
    public Result addDepartment(Department department) {
        departmentService.add(department);
        return Result.ok();
    }

    @ApiOperation(value = "修改部门", httpMethod = "PUT")
    @PreAuthorize("hasAuthority('system:department:update')")
    @PutMapping(value = "")
    public Result updateDepartment(Department department) {
        departmentService.update(department);
        return Result.ok();
    }

}
