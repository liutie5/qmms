package com.qmms;

import com.qmms.dao.StatLoanUvChannelDao;
import com.qmms.entity.StatLoanUvChannel;
import com.qmms.entity.StatLoanUvSumByProduct;
import com.qmms.sevice.StatService;
import com.qmms.utils.DateUtil;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StatTest extends BasicJPATest{
    @Resource
    StatLoanUvChannelDao loanUvChannelDao;
    @Resource
    StatService statService;

    @Test
    public void select(){
//        Pageable pageable = new PageRequest(0,1);
//        System.out.println("kl");
//        Page<StatLoanUvChannel> list = loanUvChannelDao.selectByPid(DateUtil.strToDate("yyyy-MM-dd","2017-11-28"),DateUtil.strToDate("yyyy-MM-dd","2017-11-28"),pageable);
////        Page<StatLoanUvSumByProduct> list = loanUvChannelDao.selectByPidNative("2017-11-28","2017-11-28",pageable);
//        int pageSize = list.getTotalPages();
//        List<StatLoanUvChannel> ls = list.getContent();
//
//        System.out.println(pageSize+ls.get(0).getProductId()+ls.get(0).getProductName());

    }

    @Test
    public void selectService(){
        String beginDate = "2017-11-28";
        String endDate = "2017-11-28";
        if(StringUtils.isBlank(beginDate)){
            beginDate = DateUtil.date2str("yyyy-MM-dd",new Date());
        }
        if(StringUtils.isBlank(endDate)){
            endDate = DateUtil.date2str("yyyy-MM-dd",new Date());
        }
        Page p1 = statService.getLoanUvStatByPidListWithCondition(0, 10, beginDate, endDate);
        List<StatLoanUvChannel> channelList = p1.getContent();
        System.out.println(p1.getContent().size());

    }

    @Test
    public void selectServicePid(){
        String beginDate = "2017-11-28";
        String endDate = "2017-11-28";
        if(StringUtils.isBlank(beginDate)){
            beginDate = DateUtil.date2str("yyyy-MM-dd",new Date());
        }
        if(StringUtils.isBlank(endDate)){
            endDate = DateUtil.date2str("yyyy-MM-dd",new Date());
        }
        Page p1 = statService.getLoanUvStatByPidDetailListWithCondition(0, 10,1L, beginDate, endDate);
        List<StatLoanUvChannel> channelList = p1.getContent();
        System.out.println(p1.getContent().size());

    }



}
