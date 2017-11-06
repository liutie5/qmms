package com.qmms.sevice.impl;

import com.qmms.dao.SerLoanTypeDao;
import com.qmms.entity.SerLoanType;
import com.qmms.entity.SysUserInfo;
import com.qmms.sevice.SerLoanService;
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
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class SerLoanServiceImpl implements SerLoanService {
    @Resource
    private SerLoanTypeDao serLoanTypeDao;
   
    /**
     * 分页查找
     * @param page
     * @param pageSize
     * @param title
     * @return
     */
    @Override
    public Page<SerLoanType> getLoanTypeListWithCondition(int page, int pageSize, final String title) {
        Pageable pageable = new PageRequest(page,pageSize);
        Page<SerLoanType> channelPage = serLoanTypeDao.findAll(new Specification<SerLoanType>(){
            @Override
            public Predicate toPredicate(Root<SerLoanType> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
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
     * @param loanType
     * @return
     */
    @Transactional
    @Override
    public SerLoanType addLoanType(SerLoanType loanType) {
        SysUserInfo currentUser = (SysUserInfo) SecurityUtils.getSubject().getPrincipal();
        int currentTime = (int)(new Date().getTime()/1000);
        loanType.setAddTime(currentTime);
        loanType.setAddUserId(currentUser.getUserId());
        loanType.setUpdateUserId(currentUser.getUserId());
        loanType.setUpdateTime(currentTime);
        loanType = serLoanTypeDao.save(loanType);
        return loanType;
    }

    /**
     * 删除
     * @param key
     */
    @Transactional
    public void delLoanType(String key){
        serLoanTypeDao.delete(key);
    }

    /**
     * 获取
     * @param key
     * @return
     */
    @Override
    public SerLoanType getLoanType(String key) {
        return serLoanTypeDao.findOne(key);
    }

    /**
     * 编辑
     * @param loanType
     * @return
     */
    @Override
    public SerLoanType editLoanType(SerLoanType loanType) {
        SerLoanType cu = serLoanTypeDao.findOne(loanType.getKey());
        if(loanType.getTitle() != null){
            cu.setTitle(loanType.getTitle());
        }
        if(loanType.getDesc() != null){
            cu.setDesc(loanType.getDesc());
        }
        if(loanType.getImg() != null){
            cu.setImg(loanType.getImg());
        }
        SysUserInfo currentUser = (SysUserInfo) SecurityUtils.getSubject().getPrincipal();
        int currentTime = (int)(new Date().getTime()/1000);
        cu.setUpdateTime(currentTime);
        cu.setUpdateUserId(currentUser.getUserId());
        return serLoanTypeDao.save(cu);
    }
}
