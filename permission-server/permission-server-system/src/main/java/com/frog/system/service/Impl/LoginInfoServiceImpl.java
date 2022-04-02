package com.frog.system.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.frog.common.core.util.Assert;
import com.frog.system.domain.system.LoginInfo;
import com.frog.system.mapper.LoginInfoMapper;
import com.frog.system.service.ILoginInfoService;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lh
 */
@Service
public class LoginInfoServiceImpl extends ServiceImpl<LoginInfoMapper, LoginInfo> implements ILoginInfoService {

    @Override
    public List<LoginInfo> listLoginInfo(LoginInfo loginInfo) {
        return lambdaQuery().eq(LoginInfo::isDelete, Boolean.FALSE)
                .eq(StringUtils.isNotEmpty(loginInfo.getUsername()), LoginInfo::getUsername, loginInfo.getUsername())
                .eq(StringUtils.isNotEmpty(loginInfo.getIp()), LoginInfo::getIp, loginInfo.getIp())
                .eq(ObjectUtils.allNotNull(loginInfo.getStatus()), LoginInfo::getStatus, loginInfo.getStatus())
                .ge(ObjectUtils.allNotNull(loginInfo.getStartTime()), LoginInfo::getLoginTime, loginInfo.getStartTime())
                .le(ObjectUtils.allNotNull(loginInfo.getEndTime()), LoginInfo::getEndTime, loginInfo.getEndTime())
                .list();
    }

    @Override
    public void batchDeleteById(List<Long> idList) {
        Assert.notEmpty(idList, "批量删除失败: id列表不能为空!");
        lambdaUpdate().set(LoginInfo::isDelete, Boolean.TRUE)
                .eq(LoginInfo::isDelete, Boolean.FALSE)
                .in(LoginInfo::getId, idList)
                .update();
    }

    @Override
    public void clearAll() {
        lambdaUpdate().set(LoginInfo::isDelete, Boolean.TRUE)
                .eq(LoginInfo::isDelete, Boolean.FALSE)
                .update();
    }
}
