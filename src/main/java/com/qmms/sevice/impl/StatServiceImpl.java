package com.qmms.sevice.impl;

import com.qmms.dao.StatLoanUvChannelDao;
import com.qmms.dao.StatLoanUvDao;
import com.qmms.sevice.StatService;
import com.qmms.utils.DateUtil;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.Date;

@Service
public class StatServiceImpl implements StatService{
    @Resource
    private StatLoanUvChannelDao statLoanUvChannelDao;
    @Resource
    private StatLoanUvDao statLoanUvDao;

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
}
