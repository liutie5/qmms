package com.qmms.sevice;

import com.qmms.entity.SysUserInfo;
import org.springframework.data.domain.Page;

public interface SysUserInfoService {
    /**通过username查找用户信息;*/
    public SysUserInfo findByUsername(String username);

    Page<SysUserInfo> getUserList(int page,int pageSize);
}