package com.frog.system.controller;

import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.frog.system.util.ServletUtils;
import com.github.pagehelper.PageHelper;

/**
 * @author lh
 */
public class BaseController {

    /**
     * 当前页
     */
    private static final String PAGE_NUM = "pageNum";

    /**
     * 分页大小
     */
    private static final String PAGE_SIZE = "pageSize";

    protected void startPage() {
        Integer pageNum = ServletUtils.getParameterToInt(PAGE_NUM);
        Integer pageSize = ServletUtils.getParameterToInt(PAGE_SIZE);
        if(ObjectUtils.isNotNull(pageNum) && ObjectUtils.isNotNull(pageSize)) {
            PageHelper.startPage(pageNum, pageSize);
        }
    }
}
