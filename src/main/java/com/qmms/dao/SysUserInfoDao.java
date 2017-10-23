package com.qmms.dao;

import com.qmms.entity.SysUserInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface SysUserInfoDao extends JpaRepository<SysUserInfo,Long> {
    /**通过username查找用户信息;*/
    public SysUserInfo findByUsername(String username);

}