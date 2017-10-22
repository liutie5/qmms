package com.qmms.dao;

import com.qmms.entity.SysUserInfo;
import org.springframework.data.repository.CrudRepository;

public interface SysUserInfoDao extends CrudRepository<SysUserInfo,Long> {
    /**通过username查找用户信息;*/
    public SysUserInfo findByUsername(String username);
}