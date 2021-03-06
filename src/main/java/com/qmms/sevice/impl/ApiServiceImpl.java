package com.qmms.sevice.impl;

import com.qmms.dao.*;
import com.qmms.entity.*;
import com.qmms.entity.api.*;
import com.qmms.sevice.ApiService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.net.URLEncoder;
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
    private SerCreditBankDao serCreditBankDao;
    @Resource
    private SerCreditProductDao serCreditProductDao;
    @Resource
    private SerCreditGroupDao serCreditGroupDao;

    @Resource
    private SerChangeShowDao serChangeShowDao;
    @Resource
    private SerRnUpdateDao serRnUpdateDao;

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
            serLoanProductList = serLoanProductDao.findAllByStatusOrderByOrderedByDesc(1);
        }else{
            SerLoanType loanType = new SerLoanType();
            loanType.setKey(hotType);
            Set<SerLoanType> loanTypeSet = new HashSet<>();
            loanTypeSet.add(loanType);
            serLoanProductList = serLoanProductDao.findAllByLoanTypeListInAndStatusOrderByOrderedByDesc(loanTypeSet,1);
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
                if(StringUtils.isBlank(data.getSubImg())){
                    target.setSubImg("");
                }else{
                    target.setSubImg(domainName+"/"+data.getSubImg());
                }
                if(data.getSubDesc() == null){
                    target.setSubDesc("");
                }else{
                    target.setSubDesc(data.getSubDesc());
                }
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
                    target.setPid(pid.toString());
                }else{
                    target.setPid("");
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
        target.setSuccessRate(data.getSuccessRate());
        String hotLevel="0";
        if(data.getHotLevel()!=null){
            hotLevel = data.getHotLevel().toString();
        }
        target.setHotLevel(hotLevel);
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
        String encodeUrl = url;
        try{
            encodeUrl = URLEncoder.encode(url,"utf8");
        }catch (Exception e){
        }
        buffer.append("/forward/loan?pkgKey=").append(pkgName).append("&source=").append(source)
                .append("&type=").append(type).append("&pid=").append(pid).append("&fallback="+ encodeUrl);
        return buffer.toString();
    }

    @Override
    public LoanPlist loanPlist(String domainName, String pkgName, String pkgKey, String source, final String type, final String balance, final String term) {
        LoanPlist loanPlist = new LoanPlist();
        List<SerLoanProduct> productList = serLoanProductDao.findAll(new Specification<SerLoanProduct>(){
            @Override
            public Predicate toPredicate(Root<SerLoanProduct> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list = new ArrayList<Predicate>();
                list.add(criteriaBuilder.equal(root.get("status").as(Integer.class),1));
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
        },new Sort(Sort.Direction.DESC,"orderedBy"));
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
        }
        loanTypes.setData(type);
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

        List<SerCreditBank> bankList = serCreditBankDao.findAllByIsJoinOrderByPriorityDesc(1);
        data.setBanks(convertCreditBank(domainName,bankList));

        List<SerCreditProduct> serCreditProductList = null;
        if(StringUtils.isBlank(hotType)){
            serCreditProductList = serCreditProductDao.findAllByStatusOrderByOrderedByDesc(1);
        }else{
            SerCreditType creditType = new SerCreditType();
            creditType.setKey(hotType);
            Set<SerCreditType> creditTypeSet = new HashSet<>();
            creditTypeSet.add(creditType);
            serCreditProductList = serCreditProductDao.findAllByCreditTypeListInAndStatusOrderByOrderedByDesc(creditTypeSet,1);
        }
        data.setHotps(convertCreditProductList(domainName, pkgKey, source, serCreditProductList));

        return rs;
    }

    @Override
    public CreditList creditList(String domainName, String pkgName, String pkgKey, String source, final String bankId, final String type) {
        List<SerCreditProduct> list = serCreditProductDao.findAll(new Specification<SerCreditProduct>(){
            @Override
            public Predicate toPredicate(Root<SerCreditProduct> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list = new ArrayList<Predicate>();
                list.add(criteriaBuilder.equal(root.get("status").as(Integer.class), 1));
                if(StringUtils.isNotBlank(bankId) && StringUtils.isNumeric(bankId)){
                    list.add(criteriaBuilder.equal(root.get("cardBankId").as(Long.class), Long.parseLong(bankId)));
                }
                if(StringUtils.isNotBlank(type)){
                    CriteriaBuilder.In<String> in = criteriaBuilder.in(root.join("creditTypeList").get("key").as(String.class));
                    in.value(type);
                    list.add(in);
                }
                Predicate[] predicates = new Predicate[list.size()];
                predicates = list.toArray(predicates);
                return criteriaBuilder.and(predicates);
            }
        },new Sort(Sort.Direction.DESC,"orderedBy"));

        CreditList creditList = new CreditList();
        if(list != null){
            creditList.setData(convertCreditProductList(domainName,pkgKey,source,list));
        }else{
            creditList.setData(new ArrayList<CreditProduct>());
        }
        return creditList;
    }

    @Override
    public CreditDetail creditDetail(String domainName, String pkgName, String pkgKey, String source, String cardId) {
        CreditDetail creditDetail = new CreditDetail();
        if(StringUtils.isNotBlank(cardId) && StringUtils.isNumeric(cardId)){
            SerCreditProduct product = serCreditProductDao.findOne(Long.parseLong(cardId));
            if(product != null){
                if(product.getStatus() == 1){
                    creditDetail.setCode(CodeSuccess);
                    creditDetail.setDesc("success");
                    creditDetail.setData(convertCreditProduct(domainName,pkgKey,source,product));
                }else{
                    creditDetail.setCode(CodeFailed);
                    creditDetail.setDesc("cardId status is not valid");
                }
            }else{
                creditDetail.setCode(CodeFailed);
                creditDetail.setDesc("cardId is not valid");
            }
        }else{
            creditDetail.setCode(CodeFailed);
            creditDetail.setDesc("cardId is not valid");
        }
        return creditDetail;
    }

    @Override
    public CreditTypes creditTypes(String domainName, String pkgName, String pkgKey, String source, String group) {
        if(StringUtils.isBlank(group)){
            group = "default";
        }
        CreditTypes creditTypes = new CreditTypes();
        SerCreditGroup creditGroup = serCreditGroupDao.findOne(group);
        List<CreditType> type = new ArrayList<>();
        if(creditGroup != null){
            type = convertCreditType(domainName,creditGroup.getCreditTypeList());
        }
        creditTypes.setData(type);
        creditTypes.setCode(CodeSuccess);
        creditTypes.setDesc("success");
        return creditTypes;
    }

    @Override
    public CreditBanks creditBanks(String domainName, String pkgName, String pkgKey, String source) {
        CreditBanks creditBanks = new CreditBanks();
        List<SerCreditBank> banks = serCreditBankDao.findAllByOrderByPriorityDesc();
        creditBanks.setData(convertCreditBanksData(domainName,banks));
        creditBanks.setCode(CodeSuccess);
        creditBanks.setDesc("success");
        return creditBanks;
    }

    @Override
    public CreditProgress creditProgress(String domainName, String pkgName, String pkgKey, String source) {
        CreditProgress creditProgress = new CreditProgress();
        List<SerCreditBank> banks = serCreditBankDao.findAllUseProcessUrl();
        creditProgress.setData(convertCreditProgressData(domainName,banks));
        creditProgress.setCode(CodeSuccess);
        creditProgress.setDesc("success");
        return creditProgress;
    }

    @Override
    public CreditPhone creditPhone(String domainName, String pkgName, String pkgKey, String source) {
        CreditPhone CreditPhone = new CreditPhone();
        List<SerCreditBank> banks = serCreditBankDao.findAllUseBankPhone();
        CreditPhone.setData(convertCreditPhoneData(domainName,banks));
        CreditPhone.setCode(CodeSuccess);
        CreditPhone.setDesc("success");
        return CreditPhone;
    }

    public List<CreditBank> convertCreditBank(String domainName, List<SerCreditBank> list){
        List<CreditBank> rs = new ArrayList<>();
        if(list != null){
            for(SerCreditBank data:list){
                CreditBank target = new CreditBank();
                target.setBankId(data.getBankId());
                target.setBankName(data.getBankName());
                target.setBankDesc(data.getBankDesc());
                target.setBankTag(data.getBankTag());
                target.setBankLogo(domainName+"/"+data.getBankLogo());
                rs.add(target);
            }
        }
        return rs;
    }

    public List<CreditBanksData> convertCreditBanksData(String domainName, List<SerCreditBank> list){
        List<CreditBanksData> rs = new ArrayList<>();
        if(list != null){
            for(SerCreditBank data:list){
                CreditBanksData target = new CreditBanksData();
                target.setBankId(data.getBankId());
                target.setBankName(data.getBankName());
                target.setBankDesc(data.getBankDesc());
                target.setBankTag(data.getBankTag()==null?"":data.getBankTag());
                target.setBankLogo(domainName+"/"+data.getBankLogo());
                target.setIsCOO(String.valueOf(data.getIsJoin()));
                rs.add(target);
            }
        }
        return rs;
    }
    public List<CreditProgressData> convertCreditProgressData(String domainName, List<SerCreditBank> list){
        List<CreditProgressData> rs = new ArrayList<>();
        if(list != null){
            for(SerCreditBank data:list){
                CreditProgressData target = new CreditProgressData();
                target.setBankId(data.getBankId());
                target.setBankName(data.getBankName());
                target.setBankDesc(data.getBankDesc());
                target.setBankLogo(domainName+"/"+data.getBankLogo());
                target.setUrl(data.getProcessUrl());
                rs.add(target);
            }
        }
        return rs;
    }

    public List<CreditPhoneData> convertCreditPhoneData(String domainName, List<SerCreditBank> list){
        List<CreditPhoneData> rs = new ArrayList<>();
        if(list != null){
            for(SerCreditBank data:list){
                CreditPhoneData target = new CreditPhoneData();
                target.setBankId(data.getBankId());
                target.setBankName(data.getBankName());
                target.setBankDesc(data.getBankDesc());
                target.setBankLogo(domainName+"/"+data.getBankLogo());
                target.setPhone(data.getBankPhone());
                rs.add(target);
            }
        }
        return rs;
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
                if(StringUtils.isBlank(data.getSubImg())){
                    target.setSubImg("");
                }else{
                    target.setSubImg(domainName+"/"+data.getSubImg());
                }
                if(data.getSubDesc() == null){
                    target.setSubDesc("");
                }else{
                    target.setSubDesc(data.getSubDesc());
                }
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
                    target.setPid(pid.toString());
                    SerCreditProduct product = serCreditProductDao.findOne(pid);
                    if(product != null){
                        target.setBankId(product.getCardBankId().toString());
                    }
                }else{
                    target.setPid("");
                    target.setBankId("");
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
        String encodeUrl = url;
        try{
            encodeUrl = URLEncoder.encode(url,"utf8");
        }catch (Exception e){
        }
        buffer.append("/forward/credit?pkgKey=").append(pkgName).append("&source=").append(source)
                .append("&type=").append(type).append("&cardId=").append(cardId).append("&bankId=").append(bankId).append("&fallback="+encodeUrl);
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


    @Override
    public ChangeShow changeShow(String domainName, String pkgName, String pkgKey, String source) {
        ChangeShow changeShow = new ChangeShow();
        ChangeShowData data = new ChangeShowData();
        SerChangeShow serData = null;
        //如果没有传入source值 取默认全部渠道的
        if(StringUtils.isBlank(source)){
            serData = serChangeShowDao.findOneByUmengAndMarketId(pkgKey,"");
        }else{
            //如果传入source值
            //先取匹配umeng和marketId
            serData = serChangeShowDao.findOneByUmengAndMarketId(pkgKey,source);
            //获取不到 取默认全渠道的
            if(serData == null){
                serData = serChangeShowDao.findOneByUmengAndMarketId(pkgKey,"");
            }
        }
        if(serData != null){
            data.setType(serData.getType());
            data.setUrl(serData.getUrl());
            changeShow.setData(data);
        }else{
            changeShow.setData("");
        }
        changeShow.setCode(CodeSuccess);
        changeShow.setDesc("success");
        return changeShow;
    }

    @Override
    public RnUpdate rnUpdate(String domainName, String pkgName, String pkgKey, String source,String version) {
        RnUpdate rnUpdate = new RnUpdate();
        if(StringUtils.isBlank(version) || !StringUtils.isNumeric(version)){
            rnUpdate.setCode(CodeFailed);
            rnUpdate.setDesc("version 不是数字");
            rnUpdate.setData("");
            return rnUpdate;
        }
        RnUpdateData data = new RnUpdateData();
        SerRnUpdate serData = null;

        //如果没有传入source值 取默认全部渠道的
        if(StringUtils.isBlank(source)){
            serData = serRnUpdateDao.findOneByUmengAndMarketIdAndVerson(pkgKey,"",Double.parseDouble(version));
        }else{
            //如果传入source值
            //先取匹配umeng和marketId
            serData = serRnUpdateDao.findOneByUmengAndMarketIdAndVerson(pkgKey,source,Double.parseDouble(version));
            //获取不到 取默认全渠道的
            if(serData == null){
                serData = serRnUpdateDao.findOneByUmengAndMarketIdAndVerson(pkgKey,"",Double.parseDouble(version));
            }
        }
        if(serData != null){
            data.setDesc(serData.getDesc());
            data.setUrl(domainName+"/"+serData.getUpdateFile());
            data.setVersion(String.valueOf(serData.getVersion().intValue()));
            rnUpdate.setData(data);
        }else{
            rnUpdate.setData("");
        }
        rnUpdate.setCode(CodeSuccess);
        rnUpdate.setDesc("success");
        return rnUpdate;
    }
}
