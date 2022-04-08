package com.frog.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.frog.system.domain.system.Department;

import java.util.List;

/**
 * @author lh
 */
public interface IDepartmentService extends IService<Department> {

    /**
     * 根据条件查询部门列表
     * @param department 查询条件
     * @return 部门列表
     */
    List<Department> listDepartment(Department department);

    /**
     * 添加部门
     * @param department 部门信息
     */
    void add(Department department);

    /**
     * 更新部门信息
     * @param department 部门信息
     */
    void update(Department department);

    /**
     * 删除部门
     * @param id 部门id
     */
    void deleteById(Long id);

    /**
     * 批量删除部门
     * @param idList 部门id列表
     */
    void batchDeleteById(List<Long> idList);
}
