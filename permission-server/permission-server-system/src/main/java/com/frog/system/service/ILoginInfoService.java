package com.frog.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.frog.system.domain.system.LoginInfo;

import java.util.List;

/**
 * @author lh
 */
public interface ILoginInfoService extends IService<LoginInfo> {

    /**
     * 查询登录日志
     * @param loginInfo 查询条件
     * @return 登录日志列表
     */
    List<LoginInfo> listLoginInfo(LoginInfo loginInfo);

    /**
     * 根据id列表批量删除
     * @param idList id列表
     */
    void batchDeleteById(List<Long> idList);

    /**
     * 清除登录日志
     */
    void clearAll();
}
