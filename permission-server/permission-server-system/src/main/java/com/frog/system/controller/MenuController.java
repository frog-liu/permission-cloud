package com.frog.system.controller;

import com.frog.common.core.domain.Result;
import com.frog.common.core.util.TreeUtils;
import com.frog.system.constant.ApiConstants;
import com.frog.system.domain.system.Menu;
import com.frog.system.service.IMenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @author lh
 */
@Api(value = ApiConstants.MENU, tags = "菜单信息")
@RestController
@RequestMapping(value = ApiConstants.MENU)
public class MenuController extends BaseController {

    @Autowired
    private IMenuService menuService;

    @ApiOperation(value = "树状菜单列表", httpMethod = "GET")
    @PreAuthorize("hasAuthority('system:menu:tree')")
    @GetMapping(ApiConstants.TREE)
    public Result<List<Menu>> listMenuTree(Menu menu) {
        List<Menu> menuList = menuService.listMenu(menu);
        menuList = (List<Menu>) TreeUtils.build(menuList);
        return Result.ok(menuList);
    }

    @ApiOperation(value = "新增菜单", httpMethod = "POST")
    @PreAuthorize("hasAuthority('system:menu:add')")
    @PostMapping(value = "")
    public Result addMenu(Menu menu) {
        menuService.add(menu);
        return Result.ok();
    }

    @ApiOperation(value = "修改菜单", httpMethod = "PUT")
    @PreAuthorize("hasAuthority('system:menu:update')")
    @PutMapping(value = "")
    public Result updateMenu(Menu menu) {
        menuService.update(menu);
        return Result.ok();
    }

    @ApiOperation(value = "批量删除菜单", httpMethod = "DELETE")
    @PreAuthorize("hasAuthority('system:menu:delete')")
    @DeleteMapping(value = "")
    public Result deleteById(Long id) {
        menuService.deleteById(id);
        return Result.ok();
    }

    @ApiOperation(value = "批量删除菜单", httpMethod = "DELETE")
    @PreAuthorize("hasAuthority('system:menu:delete')")
    @DeleteMapping(value = ApiConstants.LIST)
    public Result batchDeleteById(List<Long> idList) {
        menuService.batchDeleteById(idList);
        return Result.ok();
    }

}
