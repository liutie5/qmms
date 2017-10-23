package com.qmms.sevice.impl;

import com.qmms.dao.SysUserInfoDao;
import com.qmms.entity.SysUserInfo;
import com.qmms.sevice.SysUserInfoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SysUserInfoServiceImpl implements SysUserInfoService {
    @Resource
    private SysUserInfoDao userInfoDao;

    @Override
    public SysUserInfo findByUsername(String username) {
        System.out.println("UserInfoServiceImpl.findByUsername()");
        return userInfoDao.findByUsername(username);
    }

    @Override
    public Page<SysUserInfo> getUserList(int page, int pageSize) {
        Pageable pageable = new PageRequest(page,pageSize);
        return userInfoDao.findAll(pageable);

    }
}