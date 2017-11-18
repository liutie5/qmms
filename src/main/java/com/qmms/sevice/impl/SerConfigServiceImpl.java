package com.qmms.sevice.impl;

import com.qmms.dao.SerChangeShowDao;
import com.qmms.dao.SerRnUpdateDao;
import com.qmms.entity.SerChangeShow;
import com.qmms.entity.SerRnUpdate;
import com.qmms.entity.SysUserInfo;
import com.qmms.sevice.SerConfigService;
import com.qmms.utils.UpdateUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class SerConfigServiceImpl implements SerConfigService{
    @Resource
    private SerChangeShowDao serChangeShowDao;
    @Resource
    private SerRnUpdateDao serRnUpdateDao;

    @Override
    public Page<SerChangeShow> getChangeShowListWithCondition(int page, int pageSize, final String desc) {
        Pageable pageable = new PageRequest(page,pageSize);
        Page<SerChangeShow> pageList = serChangeShowDao.findAll(new Specification<SerChangeShow>(){
            @Override
            public Predicate toPredicate(Root<SerChangeShow> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list = new ArrayList<Predicate>();
                if(StringUtils.isNotBlank(desc)){
                    list.add(criteriaBuilder.like(root.get("`desc`").as(String.class),"%"+desc+"%"));
                }
                Predicate[] predicates = new Predicate[list.size()];
                predicates = list.toArray(predicates);
                return criteriaBuilder.and(predicates);

            }
        },pageable);
        return pageList;
    }

    @Override
    public SerChangeShow addChangeShow(SerChangeShow changeShow) {
        SysUserInfo currentUser = (SysUserInfo) SecurityUtils.getSubject().getPrincipal();
        int currentTime = (int)(new Date().getTime()/1000);
        changeShow.setAddTime(currentTime);
        changeShow.setAddUserId(currentUser.getUserId());
        changeShow.setUpdateUserId(currentUser.getUserId());
        changeShow.setUpdateTime(currentTime);
        return serChangeShowDao.save(changeShow);
    }

    @Override
    public SerChangeShow getChangeShow(Long id) {
        return serChangeShowDao.findOne(id);
    }

    @Override
    public void delChangeShow(Long id) {
        serChangeShowDao.delete(id);
    }

    @Override
    public SerChangeShow editChangeShow(SerChangeShow changeShow) throws Exception{
        SerChangeShow rawObject = serChangeShowDao.findOne(changeShow.getId());
        UpdateUtils.updateNotNullField(rawObject,changeShow);
        SysUserInfo currentUser = (SysUserInfo) SecurityUtils.getSubject().getPrincipal();
        int currentTime = (int)(new Date().getTime()/1000);
        rawObject.setUpdateUserId(currentUser.getUserId());
        rawObject.setUpdateTime(currentTime);
        return serChangeShowDao.save(rawObject);
    }

    //更新配置

    @Override
    public Page<SerRnUpdate> getRnUpdateListWithCondition(int page, int pageSize, final String desc) {
        Pageable pageable = new PageRequest(page,pageSize);
        Page<SerRnUpdate> pageList = serRnUpdateDao.findAll(new Specification<SerRnUpdate>(){
            @Override
            public Predicate toPredicate(Root<SerRnUpdate> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list = new ArrayList<Predicate>();
                if(StringUtils.isNotBlank(desc)){
                    list.add(criteriaBuilder.like(root.get("`desc`").as(String.class),"%"+desc+"%"));
                }
                Predicate[] predicates = new Predicate[list.size()];
                predicates = list.toArray(predicates);
                return criteriaBuilder.and(predicates);

            }
        },pageable);
        return pageList;
    }

    @Override
    public SerRnUpdate addRnUpdate(SerRnUpdate rnUpdate) {
        SysUserInfo currentUser = (SysUserInfo) SecurityUtils.getSubject().getPrincipal();
        int currentTime = (int)(new Date().getTime()/1000);
        rnUpdate.setAddTime(currentTime);
        rnUpdate.setAddUserId(currentUser.getUserId());
        rnUpdate.setUpdateUserId(currentUser.getUserId());
        rnUpdate.setUpdateTime(currentTime);
        return serRnUpdateDao.save(rnUpdate);
    }

    @Override
    public SerRnUpdate getRnUpdate(Long id) {
        return serRnUpdateDao.findOne(id);
    }

    @Override
    public void delRnUpdate(Long id) {
        serRnUpdateDao.delete(id);
    }

    @Override
    public SerRnUpdate editRnUpdate(SerRnUpdate serRnUpdate) throws Exception {
        SerRnUpdate rawObject = serRnUpdateDao.findOne(serRnUpdate.getId());
        UpdateUtils.updateNotNullField(rawObject,serRnUpdate);
        SysUserInfo currentUser = (SysUserInfo) SecurityUtils.getSubject().getPrincipal();
        int currentTime = (int)(new Date().getTime()/1000);
        rawObject.setUpdateUserId(currentUser.getUserId());
        rawObject.setUpdateTime(currentTime);
        return serRnUpdateDao.save(rawObject);
    }
}
