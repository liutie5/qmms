package com.qmms.sevice.impl;

import com.qmms.dao.SerChannelDao;
import com.qmms.dao.SerLoanProductDao;
import com.qmms.dao.StatLoanUvChannelDao;
import com.qmms.dao.StatLoanUvDao;
import com.qmms.entity.*;
import com.qmms.sevice.StatService;
import com.qmms.utils.DateUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
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
public class StatServiceImpl implements StatService{
    @Resource
    private StatLoanUvChannelDao statLoanUvChannelDao;
    @Resource
    private StatLoanUvDao statLoanUvDao;
    @Resource
    private SerChannelDao serChannelDao;
    @Resource
    private SerLoanProductDao serLoanProductDao;

    @Override
    @Transactional
    public String loanUvChannelStat(String beginDate, String endDate) {
        statLoanUvChannelDao.deleteByStatDateBetween(beginDate,endDate);
        statLoanUvChannelDao.insertLoanUvChannel(beginDate,endDate);
        statLoanUvChannelDao.insertLoanUvChannelSum(beginDate,endDate);
        return "ok";
    }

    @Override
    public String clearLoanUv(){
        Date today = new Date();
        String days3monthsBefore = DateUtil.date2str("yyyy-MM-dd",DateUtils.addMonths(today,-3));
        Long haveId = statLoanUvChannelDao.haveIdByAddDate(days3monthsBefore);
        if(haveId == null ){
            return "stat_loan_uv_channel has no data before "+days3monthsBefore;
        }else{
            statLoanUvDao.clearDateBeforeDate(days3monthsBefore);
        }
        return "clear stat_loan_uv data before "+days3monthsBefore +" success,at:"+DateUtil.date2str("yyyy-MM-dd HH:mm:ss",new Date());

    }


    @Override
    public Page<StatLoanUvSumByProduct> getLoanUvStatByPidListWithCondition(int page, int pageSize, final String beginDate,final String endDate) {
        Pageable pageable = new PageRequest(page,pageSize);
        Page<StatLoanUvSumByProduct> pageList = statLoanUvChannelDao.selectByPid(DateUtil.strToDate("yyyy-MM-dd",beginDate),DateUtil.strToDate("yyyy-MM-dd",endDate),pageable);
        if(pageList.getContent() != null){
            for(StatLoanUvSumByProduct data:pageList.getContent()){
                SerLoanProduct product = serLoanProductDao.findOne(data.getProductId());
                String pname = "未知";
                if(product != null){
                    pname = product.getName();
                }
                if(data.getProductId() == -100){
                    pname="汇总";
                }
                data.setProductName(pname);
            }
        }
        return pageList;
    }

    @Override
    public Page<StatLoanUvSumByProductDetail> getLoanUvStatByPidDetailListWithCondition(int page, int pageSize, final  Long productId, final String beginDate, final String endDate) {
        Pageable pageable = new PageRequest(page,pageSize);
        Page<StatLoanUvSumByProductDetail> pageList = statLoanUvChannelDao.selectByPidDetail(DateUtil.strToDate("yyyy-MM-dd",beginDate),DateUtil.strToDate("yyyy-MM-dd",endDate),productId,pageable);
        if(pageList != null && pageList.getContent() != null){
            for(StatLoanUvSumByProductDetail data:pageList.getContent()){
                String channelName= "未知";
                SerChannel channel = serChannelDao.findOne(data.getChannelId());
                if(channel != null){
                    channelName = channel.getName();
                }
                data.setChannelName(channelName);
            }
        }

        return pageList;
    }
}
