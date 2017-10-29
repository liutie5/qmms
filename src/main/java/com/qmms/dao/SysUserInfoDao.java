package com.qmms.dao;

import com.qmms.entity.SysUserInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

public interface SysUserInfoDao extends CrudRepository<SysUserInfo,Integer>,JpaRepository<SysUserInfo,Integer>,JpaSpecificationExecutor<SysUserInfo> {
    /**通过username查找用户信息;*/
    public SysUserInfo findByUserName(String userName);

    public SysUserInfo findByUserId(int userId);

    public int deleteByUserId(int userId);

}