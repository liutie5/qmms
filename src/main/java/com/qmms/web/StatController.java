package com.qmms.web;

import com.qmms.entity.*;
import com.qmms.sevice.StatService;
import com.qmms.utils.DateUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;

@Controller
@RequestMapping("/stat")
public class StatController {
    private static final String KeyPwd ="qmmsstat1846";
    private static Logger logger = LoggerFactory.getLogger(StatController.class);
    @Resource
    private StatService statService;



    @RequestMapping("loanUvStatList")
    public String loanUvStatList(){
        return "/stat/loanUvStatList";
    }

    @RequestMapping("getLoanUvPidList")
    @ResponseBody
    public Page<StatLoanUvSumByProduct> getLoanUvProductChannelList(int page, int pageSize, String beginDate,String endDate){
        if(StringUtils.isBlank(beginDate)){
            beginDate = DateUtil.date2str("yyyy-MM-dd",new Date());
        }
        if(StringUtils.isBlank(endDate)){
            endDate = DateUtil.date2str("yyyy-MM-dd",new Date());
        }
        Page p1 = statService.getLoanUvStatByPidListWithCondition(page, pageSize, beginDate, endDate);
        return p1;
    }


    @RequestMapping("getLoanUvPidDetailList")
    @ResponseBody
    public Page<StatLoanUvSumByProductDetail> getLoanUvPidDetailList(int page, int pageSize, Long pid, String beginDate, String endDate){
        if(StringUtils.isBlank(beginDate)){
            return null;
        }
        if(StringUtils.isBlank(endDate)){
            return null;
        }
        Page p1 = statService.getLoanUvStatByPidDetailListWithCondition(page, pageSize,pid, beginDate, endDate);
        return p1;
    }

    @RequestMapping("creditUvStatList")
    public String creditUvStatList(){
        return "/stat/creditUvStatList";
    }

    @RequestMapping("getCreditUvBankList")
    @ResponseBody
    public Page<StatCreditUvSumByBank> getCreditUvBankList(int page, int pageSize, String beginDate, String endDate){
        if(StringUtils.isBlank(beginDate)){
            beginDate = DateUtil.date2str("yyyy-MM-dd",new Date());
        }
        if(StringUtils.isBlank(endDate)){
            endDate = DateUtil.date2str("yyyy-MM-dd",new Date());
        }
        Page p1 = statService.getCreditUvStatByBankListWithCondition(page, pageSize, beginDate, endDate);
        return p1;
    }


    @RequestMapping("getCreditUvBankDetailList")
    @ResponseBody
    public Page<StatCreditUvSumByBankDetail> getCreditUvBankDetailList(int page, int pageSize, Long bankId, String beginDate, String endDate){
        if(StringUtils.isBlank(beginDate)){
            return null;
        }
        if(StringUtils.isBlank(endDate)){
            return null;
        }
        Page p1 = statService.getCreditUvStatByBankDetailListWithCondition(page, pageSize,bankId, beginDate, endDate);
        return p1;
    }



}