package com.qmms;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.qmms.dao.SerLoanBannerDao;
import com.qmms.dao.SerLoanProductDao;
import com.qmms.dao.SerLoanTipDao;
import com.qmms.dao.SerLoanTypeDao;
import com.qmms.entity.SerLoanBanner;
import com.qmms.entity.SerLoanProduct;
import com.qmms.entity.SerLoanType;
import com.qmms.entity.api.LoanMain;
import com.qmms.entity.api.LoanMainData;
import com.qmms.sevice.SerLoanService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LoanApiTest {
    @Resource
    private SerLoanBannerDao serLoanBannerDao;
    @Resource
    private SerLoanTypeDao serLoanTypeDao;
    @Resource
    private SerLoanProductDao serLoanProductDao;
    @Resource
    private SerLoanTipDao serLoanTipDao;

    @Test
    public void loanMainTest() throws Exception{
        Set<SerLoanType> typeSet = new HashSet<>();
        SerLoanType loanType = new SerLoanType();
        loanType.setKey("d1");
        typeSet.add(loanType);
        List<SerLoanProduct> productList = serLoanProductDao.findAllByLoanTypeListInAndStatus(typeSet,1);
        System.out.println(productList.size());


    }
}
