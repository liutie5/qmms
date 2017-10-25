package com.qmms.sevice.impl;

import com.qmms.dao.SysUserInfoDao;
import com.qmms.entity.SysUserInfo;
import com.qmms.sevice.SysUserInfoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

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

    @Override
    public Page<SysUserInfo> getUserListWithCondition(int page, int pageSize, final String username) {
        Pageable pageable = new PageRequest(page,pageSize);
        Page<SysUserInfo> userInfoPage = userInfoDao.findAll(new Specification<SysUserInfo>(){

            @Override
            public Predicate toPredicate(Root<SysUserInfo> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list = new ArrayList<Predicate>();
                if(StringUtils.isNotBlank(username)){
                    list.add(criteriaBuilder.like(root.get("username").as(String.class),"%"+username+"%"));
                }
                Predicate[] predicates = new Predicate[list.size()];
                if(StringUtils.isNotBlank(username)){
                    return criteriaBuilder.like(root.get("username").as(String.class),"%"+username+"%");
                }else{
                    return null;
                }


            }
        },pageable);
        return userInfoPage;
    }
}