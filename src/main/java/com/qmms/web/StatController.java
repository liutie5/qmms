package com.qmms.web;

import com.qmms.entity.StatLoanUvChannel;
import com.qmms.sevice.StatService;
import com.qmms.utils.DateUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
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

    @RequestMapping("loanUvChannelStat")
    @ResponseBody
    public String statChannel(String type,String keyPwd,String beginDate,String endDate) {
        if(StringUtils.isBlank(keyPwd)||!KeyPwd.equals(keyPwd)){
            return "authError";
        }
        String typeMsg = checkType(type,beginDate,endDate);
        if(StringUtils.isNotBlank(typeMsg)){
            return typeMsg;
        }
        try{
            String dateStr = getDate(type,beginDate,endDate);
            String[] dateArr = dateStr.split(",");
            if(dateArr != null && dateArr.length==2){
                statService.loanUvChannelStat(dateArr[0],dateArr[1]);
                return "loan_uv_channel_stat: type:"+type+",beginDate:"+dateArr[0]+",endDate:"+dateArr[1]+",at:"+DateUtil.date2str("yyyy-MM-dd HH:mm:ss",new Date());
            }else{
                return "dateArr error!";
            }
        }catch (Exception e){
            e.printStackTrace();
            return e.getMessage();
        }



    }

    @RequestMapping("loanUvBeforeClear")
    @ResponseBody
    public String loanUvBeforeClear(String keyPwd) {
        if(StringUtils.isBlank(keyPwd)||!KeyPwd.equals(keyPwd)){
            return "authError";
        }
        return statService.clearLoanUv();
    }


    @RequestMapping("loanUvStatList")
    public String loanUvStatList(){
        return "/stat/loanUvStatList";
    }

    @RequestMapping("getLoanUvChannelList")
    @ResponseBody
    public Page<StatLoanUvChannel> getLoanProductList(int page, int pageSize, String searchDate){
        if(StringUtils.isBlank(searchDate)){
            searchDate = DateUtil.date2str("yyyy-MM-dd",new Date());
        }
        Page p1 = statService.getLoanUvStatListWithCondition(page, pageSize, searchDate);
        return p1;
    }



    public String checkType(String type,String beginDate,String endDate){
        if(StringUtils.isBlank(type)||(!"today".equals(type) && !"yesterday".equals(type) && !"range3".equals(type) && !"range".equals(type))){
            return "type error";
        }
        if(type.equals("range")){
            if(StringUtils.isBlank(beginDate) ||  StringUtils.isBlank(endDate)
                    || !StringUtils.isNumeric(beginDate) || !StringUtils.isNumeric(endDate)){
                return "range date error";
            }

            if(!DateUtil.validDateFormatString("yyyyMMdd",beginDate) || !DateUtil.validDateFormatString("yyyyMMdd",endDate))
            {
                return "rage date format error";
            }
        }
        return "";
    }

    public String getDate(String type,String beginDate,String endDate){
        if(type.equals("today")){
            String today = DateUtil.date2str("yyyy-MM-dd",new Date());
            return today+","+today;
        }else if(type.equals("yesterday")){
            Date today = new Date();
            String bdate = DateUtil.date2str("yyyy-MM-dd", DateUtils.addDays(today,-1));
            String edate = bdate;
            return bdate +","+edate;

        }else if(type.equals("range3")){
            Date today = new Date();
            String bdate = DateUtil.date2str("yyyy-MM-dd", DateUtils.addDays(today,-1));
            String edate = DateUtil.date2str("yyyy-MM-dd", DateUtils.addDays(today,-3));
            return bdate +","+edate;

        }else if(type.equals("range")){
            String bdate = DateUtil.date2str("yyyy-MM-dd",DateUtil.strToDate("yyyyMMdd",beginDate));
            String edate = DateUtil.date2str("yyyy-MM-dd",DateUtil.strToDate("yyyyMMdd",endDate));
            return bdate +","+edate;
        }
        return "";
    }



}