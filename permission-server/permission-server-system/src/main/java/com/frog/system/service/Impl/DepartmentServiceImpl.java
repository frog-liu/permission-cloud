package com.frog.system.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.frog.common.core.enums.StatusEnum;
import com.frog.common.core.util.Assert;
import com.frog.system.domain.system.Department;
import com.frog.system.domain.system.Menu;
import com.frog.system.mapper.DepartmentMapper;
import com.frog.system.service.IDepartmentService;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author lh
 */
@Service
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements IDepartmentService {

    @Override
    public List<Department> listDepartment(Department department) {
        return lambdaQuery().like(StringUtils.isNotEmpty(department.getName()), Department::getName, department.getName())
                .eq(ObjectUtils.allNotNull(department.getStatus()), Department::getStatus, department.getStatus())
                .list();
    }

    @Override
    public void add(Department department) {
        check(department);
        department.setId(null);
        this.baseMapper.insert(department);
    }

    @Override
    public void update(Department department) {
        Assert.notNull(department.getId(), "更新部门失败: 部门id不能为空!");
        check(department);
        List<Department> allChildList = listAllChild(Collections.singletonList(department.getId()), new LinkedList<>());
        allChildList.forEach(child -> {
            Assert.isTrue(!child.getId().equals(department.getParentId()), "更新部门失败: 上级部门不能指向下级部门!");
        });
        this.baseMapper.updateById(department);
    }

    @Override
    public void deleteById(Long id) {
        Assert.notNull(id, "删除部门失败: 部门id不能为空!");
        List<Department> allChildList = listAllChild(Collections.singletonList(id), new LinkedList<>());
        allChildList.forEach(child -> {
            Assert.isTrue(StatusEnum.INVALID.isMatch(child.getStatus()), "删除部门失败: 请先删除该部门下的子部门!");
        });
        lambdaUpdate().set(Department::getStatus, StatusEnum.INVALID.getCode())
                .eq(Department::getId, id)
                .update();
    }

    @Override
    public void batchDeleteById(List<Long> idList) {
        Assert.notEmpty(idList, "批量删除部门失败: 部门id列表不能为空!");
        List<Department> allChildList = listAllChild(idList, new LinkedList<>());
        allChildList.forEach(child -> {
            Assert.isTrue(StatusEnum.INVALID.isMatch(child.getStatus()) || idList.contains(child.getId()), "删除部门失败: 请先删除该部门下的子部门!");
        });
        lambdaUpdate().set(Department::getStatus, StatusEnum.INVALID.getCode())
                .in(Department::getId, idList)
                .update();
    }

    private void check(Department department) {
        Assert.notEmpty(department.getName(), "操作失败: 部门名称不能为空!");
        Assert.notNull(department.getParentId(), "操作失败: 上级部门不能为空!");
        Assert.notNull(department.getStatus(), "操作失败: 部门状态不能为空!");
        checkDepartmentName(department);
    }

    private void checkDepartmentName(Department department) {
        Department oldDepartment = lambdaQuery().eq(Department::getName, department).one();
        Long departmentId = department.getId();
        Long oldDepartmentId = oldDepartment.getId();
        Assert.isFalse(departmentId == null && oldDepartment != null, "新增部门失败: 部门名称不能重复!");
        Assert.isFalse(departmentId != null && !departmentId.equals(oldDepartmentId), "更新部门失败: 部门名称不能重复!");
    }

    private List<Department> listAllChild(List<Long> parentIdList, List<Department> childList) {
        if (!CollectionUtils.isEmpty(parentIdList)) {
            List<Department> firstChildList = lambdaQuery().in(Department::getParentId, parentIdList).list();
            if (!CollectionUtils.isEmpty(firstChildList)) {
                childList.addAll(firstChildList);
                parentIdList = firstChildList.stream().map(Department::getId).collect(Collectors.toList());
                listAllChild(parentIdList, childList);
            }
        }
        return childList;
    }
}
