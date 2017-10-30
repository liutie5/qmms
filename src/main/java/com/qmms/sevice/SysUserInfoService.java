package com.qmms.sevice;

import com.qmms.entity.SysUserInfo;
import org.springframework.data.domain.Page;

import java.util.List;

public interface SysUserInfoService {
    /**通过username查找用户信息;*/
    public SysUserInfo findByUserName(String userName);
    public SysUserInfo findByUserId(Integer userId);

    Page<SysUserInfo> getUserList(int page,int pageSize);

    Page<SysUserInfo> getUserListWithCondition(int page,int pageSize,String username);

    Page<SysUserInfo> getUserListWithCondition(int page,int pageSize,String username,String name);

    public void addUser(SysUserInfo sysUserInfo);

    public void updateUser(SysUserInfo sysUserInfo);

    public void delUser(int userId);

    public List<SysUserInfo> getAllUserList();

}