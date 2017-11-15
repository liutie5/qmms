package com.qmms.sevice.impl;

import com.qmms.dao.*;
import com.qmms.entity.*;
import com.qmms.sevice.SerLoanService;
import com.qmms.utils.UpdateUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.hibernate.sql.Update;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    @Resource
    private SerLoanProductDao serLoanProductDao;
    @Resource
    private SerLoanBannerDao serLoanBannerDao;
    @Resource
    private SerLoanTipDao serLoanTipDao;
    @Resource
    private SerLoanGroupDao serLoanGroupDao;
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
    public SerLoanType editLoanType(SerLoanType loanType) throws Exception {
        SerLoanType cu = serLoanTypeDao.findOne(loanType.getKey());
        UpdateUtils.updateNotNullField(cu,loanType);
        SysUserInfo currentUser = (SysUserInfo) SecurityUtils.getSubject().getPrincipal();
        int currentTime = (int)(new Date().getTime()/1000);
        cu.setUpdateTime(currentTime);
        cu.setUpdateUserId(currentUser.getUserId());
        return serLoanTypeDao.save(cu);
    }

    @Override
    public List<SerLoanType> getAllLoanTypes() {
        return serLoanTypeDao.findAll();
    }

    //贷款产品
    @Override
    public Page<SerLoanProduct> getLoanProductList(int page, int pageSize, final String name) {
        Pageable pageable = new PageRequest(page,pageSize,new Sort(Sort.Direction.DESC,"orderedBy"));
        Page<SerLoanProduct> pageList = serLoanProductDao.findAll(new Specification<SerLoanProduct>(){
            @Override
            public Predicate toPredicate(Root<SerLoanProduct> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list = new ArrayList<Predicate>();
                if(StringUtils.isNotBlank(name)){
                    list.add(criteriaBuilder.like(root.get("name").as(String.class),"%"+name+"%"));
                }
                Predicate[] predicates = new Predicate[list.size()];
                predicates = list.toArray(predicates);
                return criteriaBuilder.and(predicates);

            }
        },pageable);
        return pageList;
    }

    @Override
    public SerLoanProduct getLoanProduct(Long productId) {
        return serLoanProductDao.findOne(productId);
    }

    @Override
    public SerLoanProduct addLoanProduct(SerLoanProduct product,String[] loanTypes,String[] channelUrls) {
        List<SerLoanType> loanTypeList = new ArrayList<>();
        for(String loanType:loanTypes){
            SerLoanType serLoanType = new SerLoanType();
            serLoanType.setKey(loanType);
            loanTypeList.add(serLoanType);
        }
        product.setLoanTypeList(loanTypeList);
        List<SerLoanProductChannelUrl> channelUrlList = new ArrayList<>();
        for(String data:channelUrls){
            String[] arr = data.split("\\$\\$");
            SerLoanProductChannelUrl url = new SerLoanProductChannelUrl();
            url.setChannelId(Long.parseLong(arr[0]));
            url.setChannelUrl(arr[1]);
            url.setLoanProduct(product);
            channelUrlList.add(url);
        }
        product.setChannelUrls(channelUrlList);
        SysUserInfo currentUser = (SysUserInfo) SecurityUtils.getSubject().getPrincipal();
        int currentTime = (int)(new Date().getTime()/1000);
        product.setAddTime(currentTime);
        product.setAddUserId(currentUser.getUserId());
        product.setUpdateUserId(currentUser.getUserId());
        product.setUpdateTime(currentTime);
        return serLoanProductDao.save(product);
    }

    @Override
    @Transactional
    public SerLoanProduct editLoanProduct(SerLoanProduct product,String[] loanTypes,String[] channelUrls) throws Exception{
        SerLoanProduct rawObject = serLoanProductDao.findOne(product.getId());
        UpdateUtils.updateNotNullField(rawObject,product);
        List<SerLoanType> loanTypeList = rawObject.getLoanTypeList();
        loanTypeList.clear();
        for(String loanType:loanTypes){
            SerLoanType serLoanType = new SerLoanType();
            serLoanType.setKey(loanType);
            loanTypeList.add(serLoanType);
        }
        List<SerLoanProductChannelUrl> channelUrlList = rawObject.getChannelUrls();
        channelUrlList.clear();
        for(String data:channelUrls){
            String[] arr = data.split("\\$\\$");
            SerLoanProductChannelUrl url = new SerLoanProductChannelUrl();
            url.setChannelId(Long.parseLong(arr[0]));
            url.setChannelUrl(arr[1]);
            url.setLoanProduct(rawObject);
            channelUrlList.add(url);
        }
        SysUserInfo currentUser = (SysUserInfo) SecurityUtils.getSubject().getPrincipal();
        int currentTime = (int)(new Date().getTime()/1000);
        rawObject.setUpdateUserId(currentUser.getUserId());
        rawObject.setUpdateTime(currentTime);
        return serLoanProductDao.save(rawObject);
    }

    @Override
    public void delLoanProduct(Long productId) {
        serLoanProductDao.delete(productId);
    }

    @Override
    public List<SerLoanProduct> getAllLoanProducts() {
        return serLoanProductDao.findAll(new Sort(Sort.Direction.DESC,"orderedBy"));
    }

    //贷款banner
    @Override
    public Page<SerLoanBanner> getLoanBannerList(int page, int pageSize, final String title) {
        Pageable pageable = new PageRequest(page,pageSize);
        Page<SerLoanBanner> pageList = serLoanBannerDao.findAll(new Specification<SerLoanBanner>(){
            @Override
            public Predicate toPredicate(Root<SerLoanBanner> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list = new ArrayList<Predicate>();
                if(StringUtils.isNotBlank(title)){
                    list.add(criteriaBuilder.like(root.get("title").as(String.class),"%"+title+"%"));
                }
                Predicate[] predicates = new Predicate[list.size()];
                predicates = list.toArray(predicates);
                return criteriaBuilder.and(predicates);

            }
        },pageable);
        return pageList;
    }

    @Override
    public SerLoanBanner getLoanBanner(Long id) {
        return serLoanBannerDao.getOne(id);
    }

    @Override
    public SerLoanBanner addLoanBanner(SerLoanBanner banner) {
        SysUserInfo currentUser = (SysUserInfo) SecurityUtils.getSubject().getPrincipal();
        int currentTime = (int)(new Date().getTime()/1000);
        banner.setAddTime(currentTime);
        banner.setAddUserId(currentUser.getUserId());
        banner.setUpdateUserId(currentUser.getUserId());
        banner.setUpdateTime(currentTime);
        return serLoanBannerDao.save(banner);
    }

    @Override
    public SerLoanBanner editLoanBanner(SerLoanBanner banner) throws Exception {
        SerLoanBanner rawBanner = serLoanBannerDao.findOne(banner.getId());
        UpdateUtils.updateNotNullField(rawBanner,banner);
        SysUserInfo currentUser = (SysUserInfo) SecurityUtils.getSubject().getPrincipal();
        int currentTime = (int)(new Date().getTime()/1000);
        rawBanner.setUpdateUserId(currentUser.getUserId());
        rawBanner.setUpdateTime(currentTime);
        return serLoanBannerDao.save(rawBanner);
    }

    @Override
    public void delLoanBanner(Long id) {
        serLoanBannerDao.delete(id);
    }

    @Override
    public Page<SerLoanTip> getLoanTipListWithCondition(int page, int pageSize, final String context) {
        Pageable pageable = new PageRequest(page,pageSize);
        Page<SerLoanTip> pageList = serLoanTipDao.findAll(new Specification<SerLoanTip>(){
            @Override
            public Predicate toPredicate(Root<SerLoanTip> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list = new ArrayList<Predicate>();
                if(StringUtils.isNotBlank(context)){
                    list.add(criteriaBuilder.like(root.get("context").as(String.class),"%"+context+"%"));
                }
                Predicate[] predicates = new Predicate[list.size()];
                predicates = list.toArray(predicates);
                return criteriaBuilder.and(predicates);

            }
        },pageable);
        return pageList;
    }

    @Override
    public SerLoanTip addLoanTip(SerLoanTip loanTip) {
        SysUserInfo currentUser = (SysUserInfo) SecurityUtils.getSubject().getPrincipal();
        int currentTime = (int)(new Date().getTime()/1000);
        loanTip.setAddTime(currentTime);
        loanTip.setAddUserId(currentUser.getUserId());
        loanTip.setUpdateUserId(currentUser.getUserId());
        loanTip.setUpdateTime(currentTime);
        return serLoanTipDao.save(loanTip);
    }

    @Override
    public SerLoanTip getLoanTip(Long id) {
        return serLoanTipDao.findOne(id);
    }

    @Override
    public void delLoanTip(Long id) {
        serLoanTipDao.delete(id);
    }

    @Override
    public SerLoanTip editLoanTip(SerLoanTip loanTip) throws Exception{
        SerLoanTip rawObj = serLoanTipDao.findOne(loanTip.getId());
        UpdateUtils.updateNotNullField(rawObj,loanTip);
        SysUserInfo currentUser = (SysUserInfo) SecurityUtils.getSubject().getPrincipal();
        int currentTime = (int)(new Date().getTime()/1000);
        rawObj.setUpdateUserId(currentUser.getUserId());
        rawObj.setUpdateTime(currentTime);
        return serLoanTipDao.save(rawObj);
    }

    @Override
    public Page<SerLoanGroup> getLoanGroupListWithCondition(int page, int pageSize, final String id) {
        Pageable pageable = new PageRequest(page,pageSize);
        Page<SerLoanGroup> pageList = serLoanGroupDao.findAll(new Specification<SerLoanGroup>(){
            @Override
            public Predicate toPredicate(Root<SerLoanGroup> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list = new ArrayList<Predicate>();
                if(StringUtils.isNotBlank(id)){
                    list.add(criteriaBuilder.like(root.get("id").as(String.class),"%"+id+"%"));
                }
                Predicate[] predicates = new Predicate[list.size()];
                predicates = list.toArray(predicates);
                return criteriaBuilder.and(predicates);

            }
        },pageable);
        return pageList;
    }

    @Override
    public SerLoanGroup addLoanGroup(SerLoanGroup loanGroup,String[] loanTypes) {
        List<SerLoanType> loanTypeList = new ArrayList<>();
        for(String loanType:loanTypes){
            SerLoanType type = new SerLoanType();
            type.setKey(loanType);
            loanTypeList.add(type);
        }
        loanGroup.setLoanTypeList(loanTypeList);
        SysUserInfo currentUser = (SysUserInfo) SecurityUtils.getSubject().getPrincipal();
        int currentTime = (int)(new Date().getTime()/1000);
        loanGroup.setAddTime(currentTime);
        loanGroup.setAddUserId(currentUser.getUserId());
        loanGroup.setUpdateUserId(currentUser.getUserId());
        loanGroup.setUpdateTime(currentTime);
        return serLoanGroupDao.save(loanGroup);
    }

    @Override
    public SerLoanGroup getLoanGroup(String id) {
        return serLoanGroupDao.findOne(id);
    }

    @Override
    public void delLoanGroup(String id) {
        serLoanGroupDao.delete(id);
    }

    @Override
    public SerLoanGroup editLoanGroup(SerLoanGroup loanGroup,String[] loanTypes) throws Exception {
        SerLoanGroup rawObj = serLoanGroupDao.findOne(loanGroup.getId());
        UpdateUtils.updateNotNullField(rawObj,loanGroup);
        rawObj.getLoanTypeList().clear();
        for(String loanType:loanTypes){
            SerLoanType type = new SerLoanType();
            type.setKey(loanType);
            rawObj.getLoanTypeList().add(type);
        }
        SysUserInfo currentUser = (SysUserInfo) SecurityUtils.getSubject().getPrincipal();
        int currentTime = (int)(new Date().getTime()/1000);
        rawObj.setUpdateUserId(currentUser.getUserId());
        rawObj.setUpdateTime(currentTime);
        return serLoanGroupDao.save(rawObj);
    }
}
