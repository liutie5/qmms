package com.qmms.sevice.impl;

import com.qmms.dao.SysUserInfoDao;
import com.qmms.entity.SysPermission;
import com.qmms.entity.SysUserInfo;
import com.qmms.sevice.SysUserInfoService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.criteria.*;
import javax.transaction.Transactional;
import java.util.*;

@Service
public class SysUserInfoServiceImpl implements SysUserInfoService {
    @Resource
    private SysUserInfoDao userInfoDao;

    @Override
    public SysUserInfo findByUserName(String username) {
        return userInfoDao.findByUserName(username);
    }

    @Override
    public SysUserInfo findByUserId(Integer userId) {
        return userInfoDao.findByUserId(userId);
    }

    @Override
    public Page<SysUserInfo> getUserList(int page, int pageSize) {
        Pageable pageable = new PageRequest(page,pageSize);
        return userInfoDao.findAll(pageable);

    }

    @Override
    public Page<SysUserInfo> getUserListWithCondition(int page, int pageSize, final String userName) {
        Pageable pageable = new PageRequest(page,pageSize);
        Page<SysUserInfo> userInfoPage = userInfoDao.findAll(new Specification<SysUserInfo>(){

            @Override
            public Predicate toPredicate(Root<SysUserInfo> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list = new ArrayList<Predicate>();
                if(StringUtils.isNotBlank(userName)){
                    list.add(criteriaBuilder.like(root.get("userName").as(String.class),"%"+userName+"%"));
                }
                Predicate[] predicates = new Predicate[list.size()];
                predicates = list.toArray(predicates);
                return criteriaBuilder.and(predicates);
            }
        },pageable);
        return userInfoPage;
    }

    @Override
    public Page<SysUserInfo> getUserListWithCondition(int page, int pageSize, final String userName, final String name) {
        Pageable pageable = new PageRequest(page,pageSize);
        Page<SysUserInfo> userInfoPage = userInfoDao.findAll(new Specification<SysUserInfo>(){

            @Override
            public Predicate toPredicate(Root<SysUserInfo> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list = new ArrayList<Predicate>();
                if(StringUtils.isNotBlank(userName)){
                    list.add(criteriaBuilder.like(root.get("userName").as(String.class),"%"+userName+"%"));
                }

                Predicate[] predicates = new Predicate[list.size()];
                predicates = list.toArray(predicates);
                return criteriaBuilder.and(predicates);


            }
        },pageable);
        return userInfoPage;
    }


    @Override
    public void addUser(SysUserInfo addUser){
        SysUserInfo currentUser = (SysUserInfo) SecurityUtils.getSubject().getPrincipal();
        String salt = getRandomString(16);
        addUser.setSalt(salt);
        SimpleHash password = new SimpleHash("md5", addUser.getPassword(),addUser.getCredentialsSalt() , 1);
        addUser.setPassword(password.toString());
        addUser.setAddUserId(currentUser.getUserId());
        int currentTime = (int)(new Date().getTime()/1000);
        addUser.setAddTime(currentTime);
        addUser.setUpdateUserId(currentUser.getUserId());
        addUser.setUpdateTime(currentTime);
        userInfoDao.save(addUser);

    }
    public static String getRandomString(int length){
        String str="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random=new Random();
        StringBuffer sb=new StringBuffer();
        for(int i=0;i<length;i++){
            int number=random.nextInt(62);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }

    @Override
    public void updateUser(SysUserInfo editUser) {
        Integer userId = editUser.getUserId();
        SysUserInfo rawUserInfo = userInfoDao.findByUserId(userId);
        if(StringUtils.isNotBlank(editUser.getPassword())){
            SimpleHash password = new SimpleHash("md5", editUser.getPassword(),rawUserInfo.getCredentialsSalt() , 1);
            rawUserInfo.setPassword(password.toString());
        }
        if(editUser.getUserType() != null){
            rawUserInfo.setUserType(editUser.getUserType());
        }
        if(editUser.getState() != null){
            rawUserInfo.setState(editUser.getState());
        }
        if(editUser.getRemark() != null){
            rawUserInfo.setRemark(editUser.getRemark());
        }
        SysUserInfo opUser = (SysUserInfo) SecurityUtils.getSubject().getPrincipal();
        rawUserInfo.setUpdateUserId(opUser.getUserId());
        rawUserInfo.setUpdateTime((int)(new Date().getTime()/1000));

        userInfoDao.save(rawUserInfo);

    }

    @Override
    public void delUser(int userId) {
        try{
            userInfoDao.delete(userId);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    /**
     * 获取所有用户列表
     * @return
     */
    @Override
    public List<SysUserInfo> getAllUserList() {
        return userInfoDao.findAll();
    }

    @Transactional
    @Override
    public void addPermission(int userId, String permission) {
        SysUserInfo userInfo = userInfoDao.findByUserId(userId);
        Set<String> pset = new HashSet<>();
        String[] arr = permission.split(",");
        List<SysPermission> permissions = new ArrayList<>();
        for(String p:arr){
            pset.add(p);
            permissions.add(new SysPermission(p,""));
        }
        userInfo.setPermissionList(permissions);
        try{
            userInfoDao.save(userInfo);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}