package com.qmms.sevice;

import com.qmms.entity.SysUserInfo;

public interface SysUserInfoService {
    /**通过username查找用户信息;*/
    public SysUserInfo findByUsername(String username);
}