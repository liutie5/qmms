package com.qmms.sevice.impl;

import com.qmms.dao.*;
import com.qmms.entity.*;
import com.qmms.entity.api.*;
import com.qmms.sevice.ApiService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ApiServiceImpl implements ApiService{
    @Resource
    private SerLoanBannerDao serLoanBannerDao;
    @Resource
    private SerLoanTypeDao serLoanTypeDao;
    @Resource
    private SerLoanProductDao serLoanProductDao;
    @Resource
    private SerLoanTipDao serLoanTipDao;
    @Resource
    private SerLoanGroupDao serLoanGroupDao;
    @Resource
    private SerChannelDao serChannelDao;

    @Resource
    private SerCreditBannerDao serCreditBannerDao;
    @Resource
    private SerCreditTypeDao serCreditTypeDao;
    @Resource
    private SerCreditProductDao serCreditProductDao;
    @Resource
    private SerCreditGroupDao serCreditGroupDao;

    private static final int CodeSuccess = 0;
    private static final int CodeFailed = 1;

    @Override
    public LoanMain loanMain(String domainName,String pkgName,String pkgKey,String source,String group,String hotType){
        LoanMain rs = new LoanMain();
        LoanMainData data = new LoanMainData();
        rs.setData(data);
        if(StringUtils.isBlank(group)){
            group = "default";
        }
        SerLoanGroup loanGroup = serLoanGroupDao.findOne(group);
        List<LoanType> type = new ArrayList<>();
        if(loanGroup != null){
            type = convertLoanType(domainName,loanGroup.getLoanTypeList());
        }
        data.setTypes(type);
        List<SerLoanBanner> bannerList = serLoanBannerDao.findAllByStatus(1);
        List<LoanBanner> banner = convertLoanBanner(domainName,pkgKey,source ,bannerList);
        data.setBanner(banner);

        List<SerLoanProduct> serLoanProductList = null;
        if(StringUtils.isBlank(hotType)){
            serLoanProductList = serLoanProductDao.findAllByStatus(1);
        }else{
            SerLoanType loanType = new SerLoanType();
            loanType.setKey(hotType);
            Set<SerLoanType> loanTypeSet = new HashSet<>();
            loanTypeSet.add(loanType);
            serLoanProductList = serLoanProductDao.findAllByLoanTypeListInAndStatus(loanTypeSet,1);
        }
        data.setHotps(convertLoanProductList(domainName, pkgKey, source, serLoanProductList));
        SerLoanTip loanTip = null;
        if(StringUtils.isNotBlank(pkgName)){
            loanTip = serLoanTipDao.findOneByRmPackageNotLike(pkgName);
        }else{
            loanTip = serLoanTipDao.findLastOne();
        }

        if(loanTip != null){
            data.setTipcfg(loanTip.getContext());
        }else{
            data.setTipcfg("");
        }
        return rs;

    }

    public  List<LoanType> convertLoanType(String domainName,List<SerLoanType> list){
        List<LoanType> rs = new ArrayList<>();
        if(list != null){
            for(SerLoanType data:list){
                LoanType target = new LoanType();
                target.setKey(data.getKey());
                target.setDesc(data.getDesc());
                target.setImg(domainName+"/"+data.getImg());
                target.setTitle(data.getTitle());
                target.setSubImg(domainName+"/"+data.getSubImg());
                target.setSubDesc(data.getSubDesc());
                rs.add(target);
            }
        }
        return  rs;
    }

    public  List<LoanBanner> convertLoanBanner(String domainName,String pkgKey,String source,List<SerLoanBanner> list){
        List<LoanBanner> rs = new ArrayList<>();
        if(list != null){
            for(SerLoanBanner data:list){
                Long pid = data.getPid();
                String url = data.getUrl();
                if((pid == null || pid <=0) && StringUtils.isBlank(url)){
                    continue;
                }
                LoanBanner target = new LoanBanner();

                if(pid != null && pid >0){
                    SerLoanProduct product = serLoanProductDao.findOne(pid);
                    if(product != null){
                        target.setPid(getLoanForwardUrl(domainName,pkgKey,source,"product",pid.toString(),product.getUrl()));
                    }
                }
                target.setTitle(data.getTitle());
                target.setImg(domainName+"/"+data.getImg());
                target.setUrl(getLoanForwardUrl(domainName,pkgKey,source,"product","-1",data.getUrl()));
                rs.add(target);
            }
        }
        return rs;
    }

    public  List<LoanProduct> convertLoanProductList(String domainName,String pkgKey,String source,List<SerLoanProduct> list){
        List<LoanProduct> rs = new ArrayList<>();
        if(list != null){
            for(SerLoanProduct data:list){
                rs.add(convertLoanProduct(domainName,pkgKey,source,data));
            }
        }
        return rs;

    }

    public LoanProduct convertLoanProduct(String domainName,String pkgKey,String source,SerLoanProduct data){
        LoanProduct target  = new LoanProduct();
        target.setId(String.valueOf(data.getId()));
        target.setName(data.getName());
        target.setCompany(data.getCompany());
        target.setBalanceDesc(data.getBalanceDesc());
        target.setMinBalance(String.valueOf(data.getMinBalance()));
        target.setMaxBalance(String.valueOf(data.getMaxBalance()));
        target.setRate(data.getRate());
        target.setRateDesc(data.getRateDesc());
        target.setTerm(data.getTerm());
        target.setTermDesc(data.getTermDesc());
        target.setSpeed(String.valueOf(data.getSpeed()));
        target.setSpeedDesc(data.getSpeedDesc());
        target.setTags(data.getTags());
        target.setSlogan(data.getSlogan());
        target.setProductDesc(data.getProductDesc());
        target.setProductCondition(data.getProductCondition());
        target.setApplyNum(String.valueOf(data.getApplyNum()));
        target.setProductProcess(data.getProductProcess());
        target.setImg(domainName+"/"+data.getImg());
        String urlRs = "";
        String allUrlRs = "";
        List<SerLoanProductChannelUrl> channelUrlList = data.getChannelUrls();
        for(SerLoanProductChannelUrl url:channelUrlList){
            long channelId = url.getChannelId();
            SerChannel channel = serChannelDao.findOne(channelId);
            if(channel != null){
                List<SerChannelUmeng> channelUmengList = channel.getChannelUmengList();
                for(SerChannelUmeng umeng:channelUmengList){
                    String umengKey = umeng.getUmengKey();
                    String marketId = umeng.getMarketId();
                    if(umengKey.equals(pkgKey) && marketId.equals(source)){
                        urlRs = url.getChannelUrl();
                        break;
                    }
                    //所有市场
                    if(umengKey.equals(pkgKey) && marketId.equals("")){
                        allUrlRs = url.getChannelUrl();
                    }
                }
            }
        }
        String finalUrl = "";
        if(StringUtils.isNotBlank(urlRs)){
            finalUrl = urlRs;
        }else if(StringUtils.isNotBlank(allUrlRs)){
            finalUrl = allUrlRs;
        }else{
            finalUrl = data.getUrl();
        }
        target.setUrl(getLoanForwardUrl(domainName,pkgKey,source,"product",data.getId().toString(),finalUrl));
        return target;
    }

    public String getLoanForwardUrl(String domainName,String pkgName,String source,String type,String pid,String url){
        StringBuffer buffer = new StringBuffer(domainName);
        buffer.append("/forward/loan?pkgKey=").append(pkgName).append("&source=").append(source)
                .append("&type=").append(type).append("&pid=").append(pid).append("&fallback="+url);
        return buffer.toString();
    }

    @Override
    public LoanPlist loanPlist(String domainName, String pkgName, String pkgKey, String source, final String type, final String balance, final String term) {
        LoanPlist loanPlist = new LoanPlist();
        List<SerLoanProduct> productList = serLoanProductDao.findAll(new Specification<SerLoanProduct>(){
            @Override
            public Predicate toPredicate(Root<SerLoanProduct> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list = new ArrayList<Predicate>();
                list.add(criteriaBuilder.lessThanOrEqualTo(root.get("status").as(Integer.class),1));
                if(StringUtils.isNotBlank(balance) && StringUtils.isNumeric(balance)){
                    list.add(criteriaBuilder.lessThanOrEqualTo(root.get("minBalance").as(Long.class),Long.parseLong(balance)));
                    list.add(criteriaBuilder.greaterThanOrEqualTo(root.get("maxBalance").as(Long.class),Long.parseLong(balance)));

                }
                if(StringUtils.isNotBlank(term) && StringUtils.isNumeric(term)){
                    list.add(criteriaBuilder.lessThanOrEqualTo(root.get("minTerm").as(Long.class),Long.parseLong(term)));
                    list.add(criteriaBuilder.greaterThanOrEqualTo(root.get("maxTerm").as(Long.class),Long.parseLong(term)));

                }
                if(StringUtils.isNotBlank(type)){
                    CriteriaBuilder.In<String> in = criteriaBuilder.in(root.join("loanTypeList").get("key").as(String.class));
                    in.value(type);
                    list.add(in);
                }
                Predicate[] predicates = new Predicate[list.size()];
                predicates = list.toArray(predicates);
                return criteriaBuilder.and(predicates);

            }
        });
        loanPlist.setData(convertLoanProductList(domainName, pkgKey, source, productList));
        loanPlist.setCode(CodeSuccess);
        loanPlist.setDesc("success");
        return loanPlist;
    }


    @Override
    public LoanPdetail loanPdetail(String domainName, String pkgName, String pkgKey, String source, String pid) {
        LoanPdetail loanPdetail = new LoanPdetail();
        if(StringUtils.isNotBlank(pid) && StringUtils.isNumeric(pid)){
            SerLoanProduct product = serLoanProductDao.findOne(Long.parseLong(pid));
            if(product != null){
                if(product.getStatus() == 1){
                    loanPdetail.setCode(CodeSuccess);
                    loanPdetail.setDesc("success");
                    loanPdetail.setData(convertLoanProduct(domainName,pkgKey,source,product));
                }else{
                    loanPdetail.setCode(CodeFailed);
                    loanPdetail.setDesc("pid status is not valid");
                }
            }else{
                loanPdetail.setCode(CodeFailed);
                loanPdetail.setDesc("pid is not valid");
            }
        }else{
            loanPdetail.setCode(CodeFailed);
            loanPdetail.setDesc("pid is not valid");
        }
        return loanPdetail;
    }

    @Override
    public LoanTypes loanTypes(String domainName, String pkgName, String pkgKey, String source, String group) {
        if(StringUtils.isBlank(group)){
            group = "default";
        }
        LoanTypes loanTypes = new LoanTypes();
        SerLoanGroup loanGroup = serLoanGroupDao.findOne(group);
        List<LoanType> type = new ArrayList<>();
        if(loanGroup != null){
            type = convertLoanType(domainName,loanGroup.getLoanTypeList());
            loanTypes.setData(type);
        }
        loanTypes.setCode(CodeSuccess);
        loanTypes.setDesc("success");
        return loanTypes;
    }


    @Override
    public CreditMain creditMain(String domainName, String pkgName, String pkgKey, String source, String group, String hotType) {
        CreditMain rs = new CreditMain();
        CreditMainData data = new CreditMainData();
        rs.setData(data);
        if(StringUtils.isBlank(group)){
            group = "default";
        }
        SerCreditGroup creditGroup = serCreditGroupDao.findOne(group);
        List<CreditType> type = new ArrayList<>();
        if(creditGroup != null){
            type = convertCreditType(domainName,creditGroup.getCreditTypeList());
        }
        data.setTypes(type);
        List<SerCreditBanner> bannerList = serCreditBannerDao.findAllByStatus(1);
        List<CreditBanner> banner = convertCreditBanner(domainName,pkgKey,source ,bannerList);
        data.setBanner(banner);

        List<SerCreditProduct> serCreditProductList = null;
        if(StringUtils.isBlank(hotType)){
            serCreditProductList = serCreditProductDao.findAllByStatus(1);
        }else{
            SerCreditType creditType = new SerCreditType();
            creditType.setKey(hotType);
            Set<SerCreditType> creditTypeSet = new HashSet<>();
            creditTypeSet.add(creditType);
            serCreditProductList = serCreditProductDao.findAllByLoanTypeListInAndStatus(creditTypeSet,1);
        }
        data.setHotps(convertCreditProductList(domainName, pkgKey, source, serCreditProductList));

        return rs;
    }

    @Override
    public CreditList creditList(String domainName, String pkgName, String pkgKey, String source, String bankId, String type) {
        return null;
    }

    @Override
    public CreditDetail creditDetail(String domainName, String pkgName, String pkgKey, String source, String cardId) {
        return null;
    }

    @Override
    public CreditTypes creditTypes(String domainName, String pkgName, String pkgKey, String source, String group) {
        return null;
    }


    public  List<CreditType> convertCreditType(String domainName,List<SerCreditType> list){
        List<CreditType> rs = new ArrayList<>();
        if(list != null){
            for(SerCreditType data:list){
                CreditType target = new CreditType();
                target.setKey(data.getKey());
                target.setDesc(data.getDesc());
                target.setImg(domainName+"/"+data.getImg());
                target.setTitle(data.getTitle());
                target.setSubImg(domainName+"/"+data.getSubImg());
                target.setSubDesc(data.getSubDesc());
                rs.add(target);
            }
        }
        return  rs;
    }

    public  List<CreditBanner> convertCreditBanner(String domainName,String pkgKey,String source,List<SerCreditBanner> list){
        List<CreditBanner> rs = new ArrayList<>();
        if(list != null){
            for(SerCreditBanner data:list){
                Long pid = data.getPid();
                String url = data.getUrl();
                if((pid == null || pid <=0) && StringUtils.isBlank(url)){
                    continue;
                }
                CreditBanner target = new CreditBanner();
                if(pid != null && pid >0){
                    SerCreditProduct product = serCreditProductDao.findOne(pid);
                    if(product != null){
                        target.setPid(getLoanForwardUrl(domainName,pkgKey,source,"product",pid.toString(),product.getUrl()));
                    }
                }
                target.setTitle(data.getTitle());
                target.setImg(domainName+"/"+data.getImg());
                target.setUrl(getLoanForwardUrl(domainName,pkgKey,source,"product","-1",data.getUrl()));
                rs.add(target);
            }
        }
        return rs;
    }

    public String getCreditForwardUrl(String domainName,String pkgName,String source,String type,String cardId,String bankId,String url){
        StringBuffer buffer = new StringBuffer(domainName);
        buffer.append("/forward/credit?pkgKey=").append(pkgName).append("&source=").append(source)
                .append("&type=").append(type).append("&cardId=").append(cardId).append("&bankId=").append(bankId).append("&fallback="+url);
        return buffer.toString();
    }

    public  List<CreditProduct> convertCreditProductList(String domainName,String pkgKey,String source,List<SerCreditProduct> list){
        List<CreditProduct> rs = new ArrayList<>();
        if(list != null){
            for(SerCreditProduct data:list){
                rs.add(convertCreditProduct(domainName,pkgKey,source,data));
            }
        }
        return rs;

    }


    public CreditProduct convertCreditProduct(String domainName,String pkgKey,String source,SerCreditProduct data){
        CreditProduct target  = new CreditProduct();
        target.setCardId(String.valueOf(data.getCardId()));
        target.setCardName(data.getCardName());
        target.setCardBankId(String.valueOf(data.getCardBankId()));
        target.setCardBankName(data.getCardBankName());
        target.setCardTags(data.getCardTags());
        target.setCardPrivilege(data.getCardPrivilege());
        target.setCardDesc(data.getCardDesc());
        target.setApplyNum(String.valueOf(data.getApplyNum()));
        target.setCardImg(domainName+"/"+data.getCardImg());
        String urlRs = "";
        String allUrlRs = "";
        Set<SerCreditProductChannelUrl> channelUrlList = data.getChannelUrls();
        for(SerCreditProductChannelUrl url:channelUrlList){
            long channelId = url.getChannelId();
            SerChannel channel = serChannelDao.findOne(channelId);
            if(channel != null){
                List<SerChannelUmeng> channelUmengList = channel.getChannelUmengList();
                for(SerChannelUmeng umeng:channelUmengList){
                    String umengKey = umeng.getUmengKey();
                    String marketId = umeng.getMarketId();
                    if(umengKey.equals(pkgKey) && marketId.equals(source)){
                        urlRs = url.getChannelUrl();
                        break;
                    }
                    //所有市场
                    if(umengKey.equals(pkgKey) && marketId.equals("")){
                        allUrlRs = url.getChannelUrl();
                    }
                }
            }
        }
        String finalUrl = "";
        if(StringUtils.isNotBlank(urlRs)){
            finalUrl = urlRs;
        }else if(StringUtils.isNotBlank(allUrlRs)){
            finalUrl = allUrlRs;
        }else{
            finalUrl = data.getUrl();
        }
        target.setUrl(getCreditForwardUrl(domainName,pkgKey,source,"product",data.getCardId().toString(),data.getCardBankId().toString(),finalUrl));
        return target;
    }
}
