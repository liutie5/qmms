package com.qmms.sevice.impl;

import com.qmms.dao.*;
import com.qmms.entity.*;
import com.qmms.sevice.SerCreditService;
import com.qmms.utils.UpdateUtils;
import com.qmms.utils.UploadUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Value;
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
import java.util.*;

@Service
public class SerCreditServiceImpl implements SerCreditService{
    @Value("${web.upload-path}")
    private String webUploadPath;

    @Resource
    private SerCreditBankDao serCreditBankDao;
    @Resource
    private SerCreditTypeDao serCreditTypeDao;
    @Resource
    private SerCreditBannerDao serCreditBannerDao;
    @Resource
    private SerCreditProductDao serCreditProductDao;
    @Resource
    private SerCreditGroupDao serCreditGroupDao;

    //信用卡银行
    
    @Override
    public Page<SerCreditBank> getCreditBankListWithCondition(int page, int pageSize, final String bankName) {
        Pageable pageable = new PageRequest(page,pageSize,new Sort(Sort.Direction.DESC,"priority"));
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
        SerCreditBank bank = serCreditBankDao.findOne(id);
        serCreditBankDao.delete(id);
        if(bank != null){
            UploadUtil.rmUploadFile(webUploadPath+bank.getBankLogo());
        }
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

    @Override
    public List<SerCreditBank> getAllCreditBank() {
        return serCreditBankDao.findAll();
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
    public void delCreditType(String key){
        SerCreditType creditType = serCreditTypeDao.findOne(key);
        delCreditTypeTrans(key);
        if(creditType != null){
            UploadUtil.rmUploadFile(webUploadPath+creditType.getImg());
            if(StringUtils.isNotBlank(creditType.getSubImg())){
                UploadUtil.rmUploadFile(webUploadPath+creditType.getSubImg());
            }

        }
    }

    public void delCreditTypeTrans(String key){
        serCreditTypeDao.deleteCreditGroupType(key);
        serCreditTypeDao.deleteCreditProductType(key);
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


    //信用卡广告

    @Override
    public Page<SerCreditBanner> getCreditBannerList(int page, int pageSize, final String title) {
        Pageable pageable = new PageRequest(page,pageSize);
        Page<SerCreditBanner> pageList = serCreditBannerDao.findAll(new Specification<SerCreditBanner>(){
            @Override
            public Predicate toPredicate(Root<SerCreditBanner> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
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
    public SerCreditBanner getCreditBanner(Long id) {
        return serCreditBannerDao.findOne(id);
    }

    @Transactional
    @Override
    public SerCreditBanner addCreditBanner(SerCreditBanner banner) {
        SysUserInfo currentUser = (SysUserInfo) SecurityUtils.getSubject().getPrincipal();
        int currentTime = (int)(new Date().getTime()/1000);
        banner.setAddTime(currentTime);
        banner.setAddUserId(currentUser.getUserId());
        banner.setUpdateUserId(currentUser.getUserId());
        banner.setUpdateTime(currentTime);
        banner = serCreditBannerDao.save(banner);
        return banner;
    }

    @Transactional
    @Override
    public SerCreditBanner editCreditBanner(SerCreditBanner banner) throws Exception {
        SerCreditBanner rawObj = serCreditBannerDao.findOne(banner.getId());
        UpdateUtils.updateNotNullField(rawObj,banner);
        rawObj.setPid(banner.getPid());
        SysUserInfo currentUser = (SysUserInfo) SecurityUtils.getSubject().getPrincipal();
        int currentTime = (int)(new Date().getTime()/1000);
        rawObj.setUpdateUserId(currentUser.getUserId());
        rawObj.setUpdateTime(currentTime);
        return serCreditBannerDao.save(rawObj);
    }

    @Override
    public void delCreditBanner(Long id) {
        SerCreditBanner banner = serCreditBannerDao.findOne(id);
        serCreditBannerDao.delete(id);
        if(banner != null){
            UploadUtil.rmUploadFile(webUploadPath+banner.getImg());
        }
    }

    //信用卡产品
    @Override
    public Page<SerCreditProduct> getCreditProductList(int page, int pageSize, final String cardBandId) {
        Pageable pageable = new PageRequest(page,pageSize,new Sort(Sort.Direction.DESC,"orderedBy"));
        Page<SerCreditProduct> pageList = serCreditProductDao.findAll(new Specification<SerCreditProduct>(){
            @Override
            public Predicate toPredicate(Root<SerCreditProduct> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list = new ArrayList<Predicate>();
                if(StringUtils.isNotBlank(cardBandId) && StringUtils.isNumeric(cardBandId)){
                    list.add(criteriaBuilder.equal(root.get("cardBankId").as(Long.class), Long.parseLong(cardBandId)));
                }
                Predicate[] predicates = new Predicate[list.size()];
                predicates = list.toArray(predicates);
                return criteriaBuilder.and(predicates);

            }
        },pageable);
        return pageList;
    }

    @Override
    public SerCreditProduct getCreditProduct(Long cartId) {
        return serCreditProductDao.findOne(cartId);
    }

    @Override
    public SerCreditProduct addCreditProduct(SerCreditProduct product, String[] creditTypes, String[] channelUrls) {
        Set<SerCreditType> creditTypeList = new HashSet<>();
        if(creditTypes != null){
            for(String creditType:creditTypes){
                SerCreditType serCreditType = new SerCreditType();
                serCreditType.setKey(creditType);
                creditTypeList.add(serCreditType);
            }
        }
        product.setCreditTypeList(creditTypeList);
        Set<SerCreditProductChannelUrl> channelUrlList = new HashSet<>();
        if(channelUrls != null){
            for(String data:channelUrls){
                String[] arr = data.split("\\$\\$");
                SerCreditProductChannelUrl url = new SerCreditProductChannelUrl();
                url.setChannelId(Long.parseLong(arr[0]));
                url.setChannelUrl(arr[1]);
                url.setCreditProduct(product);
                channelUrlList.add(url);
            }
        }
        product.setChannelUrls(channelUrlList);
        SerCreditBank bank = serCreditBankDao.findOne(product.getCardBankId());
        String bankName = "";
        if(bank != null){
            bankName = bank.getBankName();
        }
        product.setCardBankName(bankName);
        SysUserInfo currentUser = (SysUserInfo) SecurityUtils.getSubject().getPrincipal();
        int currentTime = (int)(new Date().getTime()/1000);
        product.setAddTime(currentTime);
        product.setAddUserId(currentUser.getUserId());
        product.setUpdateUserId(currentUser.getUserId());
        product.setUpdateTime(currentTime);
        return serCreditProductDao.save(product);
    }

    @Transactional
    @Override
    public SerCreditProduct editCreditProduct(SerCreditProduct product, String[] creditTypes, String[] channelUrls) throws Exception {
        SerCreditProduct rawObject = serCreditProductDao.findOne(product.getCardId());
        UpdateUtils.updateNotNullField(rawObject,product);
        Set<SerCreditType> typeList = rawObject.getCreditTypeList();
        if(typeList != null){
            typeList.clear();
        }
        if(creditTypes != null){
            for(String creditType:creditTypes){
                SerCreditType serCreditType = new SerCreditType();
                serCreditType.setKey(creditType);
                typeList.add(serCreditType);
            }
        }

        Set<SerCreditProductChannelUrl> channelUrlList = rawObject.getChannelUrls();
        if(channelUrlList != null){
            channelUrlList.clear();
        }
        if(channelUrls != null){
            for(String data:channelUrls){
                String[] arr = data.split("\\$\\$");
                SerCreditProductChannelUrl url = new SerCreditProductChannelUrl();
                url.setChannelId(Long.parseLong(arr[0]));
                url.setChannelUrl(arr[1]);
                url.setCreditProduct(product);
                channelUrlList.add(url);
            }
        }
        SerCreditBank bank = serCreditBankDao.findOne(product.getCardBankId());
        String bankName = "";
        if(bank != null){
            bankName = bank.getBankName();
        }
        rawObject.setCardBankName(bankName);
        SysUserInfo currentUser = (SysUserInfo) SecurityUtils.getSubject().getPrincipal();
        int currentTime = (int)(new Date().getTime()/1000);
        rawObject.setUpdateUserId(currentUser.getUserId());
        rawObject.setUpdateTime(currentTime);
        return serCreditProductDao.save(rawObject);
    }

    @Override
    public void delCreditProduct(Long cardId) {
        SerCreditProduct product = serCreditProductDao.findOne(cardId);
        serCreditProductDao.delete(cardId);
        if(product != null){
            UploadUtil.rmUploadFile(webUploadPath+product.getCardImg());
        }
    }

    @Override
    public List<SerCreditProduct> getAllCreditProducts() {
        return serCreditProductDao.findAll(new Sort(Sort.Direction.DESC,"orderedBy"));
    }

    //分类组
    @Override
    public Page<SerCreditGroup> getCreditGroupListWithCondition(int page, int pageSize, final String id) {
        Pageable pageable = new PageRequest(page,pageSize);
        Page<SerCreditGroup> pageList = serCreditGroupDao.findAll(new Specification<SerCreditGroup>(){
            @Override
            public Predicate toPredicate(Root<SerCreditGroup> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
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
    public SerCreditGroup addCreditGroup(SerCreditGroup creditGroup,String[] creditTypes) {
        List<SerCreditType> creditTypeList = new ArrayList<>();
        for(String creditType:creditTypes){
            SerCreditType type = new SerCreditType();
            type.setKey(creditType);
            creditTypeList.add(type);
        }
        creditGroup.setCreditTypeList(creditTypeList);
        SysUserInfo currentUser = (SysUserInfo) SecurityUtils.getSubject().getPrincipal();
        int currentTime = (int)(new Date().getTime()/1000);
        creditGroup.setAddTime(currentTime);
        creditGroup.setAddUserId(currentUser.getUserId());
        creditGroup.setUpdateUserId(currentUser.getUserId());
        creditGroup.setUpdateTime(currentTime);
        return serCreditGroupDao.save(creditGroup);
    }

    @Override
    public SerCreditGroup getCreditGroup(String id) {
        return serCreditGroupDao.findOne(id);
    }

    @Override
    public void delCreditGroup(String id) {
        serCreditGroupDao.delete(id);
    }

    @Override
    public SerCreditGroup editCreditGroup(SerCreditGroup creditGroup,String[] creditTypes) throws Exception {
        SerCreditGroup rawObj = serCreditGroupDao.findOne(creditGroup.getId());
        UpdateUtils.updateNotNullField(rawObj,creditGroup);
        rawObj.getCreditTypeList().clear();
        for(String creditType:creditTypes){
            SerCreditType type = new SerCreditType();
            type.setKey(creditType);
            rawObj.getCreditTypeList().add(type);
        }
        SysUserInfo currentUser = (SysUserInfo) SecurityUtils.getSubject().getPrincipal();
        int currentTime = (int)(new Date().getTime()/1000);
        rawObj.setUpdateUserId(currentUser.getUserId());
        rawObj.setUpdateTime(currentTime);
        return serCreditGroupDao.save(rawObj);
    }
}
