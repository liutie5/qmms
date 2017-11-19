package com.qmms.sevice.impl;

import com.qmms.dao.*;
import com.qmms.entity.*;
import com.qmms.entity.api.*;
import com.qmms.sevice.ApiService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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


    @Override
    public LoanMain loanMain(String domainName,String pkgName,String pkgKey,String source,String group,String hotType){
        LoanMain rs = new LoanMain();
        LoanMainData data = new LoanMainData();
        rs.setData(data);
        if(StringUtils.isBlank(group)){
            group = "default";
        }
        SerLoanGroup loanGroup = serLoanGroupDao.getOne(group);
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
        data.setHotps(convertLoanProduct(domainName,pkgKey,source,serLoanProductList));
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

    public  List<LoanProduct> convertLoanProduct(String domainName,String pkgKey,String source,List<SerLoanProduct> list){
        List<LoanProduct> rs = new ArrayList<>();
        if(list != null){
            for(SerLoanProduct data:list){
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
                target.setUrl(getLoanForwardUrl(domainName,pkgKey,source,"product",data.getId().toString(),data.getUrl()));
                rs.add(target);
            }

        }
        return rs;

    }

    public String getLoanForwardUrl(String domainName,String pkgName,String source,String type,String pid,String url){
        StringBuffer buffer = new StringBuffer(domainName);
        buffer.append("/forward/loan?umeng=").append(pkgName).append("&mrid=").append(source)
                .append("&type=").append(type).append("&pid=").append(pid).append("&fallback="+url);
        return buffer.toString();
    }

}
