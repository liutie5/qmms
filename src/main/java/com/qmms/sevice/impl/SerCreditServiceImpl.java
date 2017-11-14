package com.qmms.sevice.impl;

import com.qmms.dao.SerCreditBankDao;
import com.qmms.dao.SerCreditTypeDao;
import com.qmms.entity.SerCreditBank;
import com.qmms.entity.SerCreditType;
import com.qmms.entity.SysUserInfo;
import com.qmms.sevice.SerCreditService;
import com.qmms.utils.UpdateUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SerCreditServiceImpl implements SerCreditService{
    @Resource
    private SerCreditBankDao serCreditBankDao;
    @Resource
    private SerCreditTypeDao serCreditTypeDao;


    //信用卡银行
    
    @Override
    public Page<SerCreditBank> getCreditBankListWithCondition(int page, int pageSize, final String bankName) {
        Pageable pageable = new PageRequest(page,pageSize);
        Page<SerCreditBank> pageList = serCreditBankDao.findAll(new Specification<SerCreditBank>(){
            @Override
            public Predicate toPredicate(Root<SerCreditBank> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list = new ArrayList<Predicate>();
                if(StringUtils.isNotBlank(bankName)){
                    list.add(criteriaBuilder.like(root.get("bankName").as(String.class),"%"+bankName+"%"));
                }
                Predicate[] predicates = new Predicate[list.size()];
                predicates = list.toArray(predicates);
                return criteriaBuilder.and(predicates);

            }
        },pageable);
        return pageList;
    }

    @Override
    public SerCreditBank addCreditBank(SerCreditBank creditBank) {
        SysUserInfo currentUser = (SysUserInfo) SecurityUtils.getSubject().getPrincipal();
        int currentTime = (int)(new Date().getTime()/1000);
        creditBank.setAddTime(currentTime);
        creditBank.setAddUserId(currentUser.getUserId());
        creditBank.setUpdateUserId(currentUser.getUserId());
        creditBank.setUpdateTime(currentTime);
        return serCreditBankDao.save(creditBank);
    }

    @Override
    public SerCreditBank getCreditBank(Long id) {
        return serCreditBankDao.findOne(id);
    }

    @Override
    public void delCreditBank(Long id) {
        serCreditBankDao.delete(id);
    }

    @Override
    public SerCreditBank editCreditBank(SerCreditBank creditBank) throws Exception {
        SerCreditBank rawObject = serCreditBankDao.findOne(creditBank.getBankId());
        UpdateUtils.updateNotNullField(rawObject,creditBank);
        SysUserInfo currentUser = (SysUserInfo) SecurityUtils.getSubject().getPrincipal();
        int currentTime = (int)(new Date().getTime()/1000);
        rawObject.setUpdateUserId(currentUser.getUserId());
        rawObject.setUpdateTime(currentTime);
        return serCreditBankDao.save(rawObject);
    }

    //信用卡类型
    /**
     * 分页查找
     * @param page
     * @param pageSize
     * @param title
     * @return
     */
    @Override
    public Page<SerCreditType> getCreditTypeListWithCondition(int page, int pageSize, final String title) {
        Pageable pageable = new PageRequest(page,pageSize);
        Page<SerCreditType> channelPage = serCreditTypeDao.findAll(new Specification<SerCreditType>(){
            @Override
            public Predicate toPredicate(Root<SerCreditType> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list = new ArrayList<Predicate>();
                if(StringUtils.isNotBlank(title)){
                    list.add(criteriaBuilder.like(root.get("title").as(String.class),"%"+title+"%"));
                }
                Predicate[] predicates = new Predicate[list.size()];
                predicates = list.toArray(predicates);
                return criteriaBuilder.and(predicates);

            }
        },pageable);
        return channelPage;
    }

    /**
     * 添加
     * @param creditType
     * @return
     */
    @Transactional
    @Override
    public SerCreditType addCreditType(SerCreditType creditType) {
        SysUserInfo currentUser = (SysUserInfo) SecurityUtils.getSubject().getPrincipal();
        int currentTime = (int)(new Date().getTime()/1000);
        creditType.setAddTime(currentTime);
        creditType.setAddUserId(currentUser.getUserId());
        creditType.setUpdateUserId(currentUser.getUserId());
        creditType.setUpdateTime(currentTime);
        creditType = serCreditTypeDao.save(creditType);
        return creditType;
    }

    /**
     * 删除
     * @param key
     */
    @Transactional
    public void delCreditType(String key){
        serCreditTypeDao.delete(key);
    }

    /**
     * 获取
     * @param key
     * @return
     */
    @Override
    public SerCreditType getCreditType(String key) {
        return serCreditTypeDao.findOne(key);
    }

    /**
     * 编辑
     * @param creditType
     * @return
     */
    @Override
    public SerCreditType editCreditType(SerCreditType creditType) throws Exception {
        SerCreditType cu = serCreditTypeDao.findOne(creditType.getKey());
        UpdateUtils.updateNotNullField(cu,creditType);
        SysUserInfo currentUser = (SysUserInfo) SecurityUtils.getSubject().getPrincipal();
        int currentTime = (int)(new Date().getTime()/1000);
        cu.setUpdateTime(currentTime);
        cu.setUpdateUserId(currentUser.getUserId());
        return serCreditTypeDao.save(cu);
    }

    @Override
    public List<SerCreditType> getAllCreditTypes() {
        return serCreditTypeDao.findAll();
    }


}
