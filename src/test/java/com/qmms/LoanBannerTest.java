package com.qmms;

import com.qmms.entity.SerLoanBanner;
import com.qmms.entity.SerLoanProduct;
import com.qmms.entity.SerLoanType;
import com.qmms.sevice.SerLoanService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LoanBannerTest {
    @Resource
    SerLoanService serLoanService;

    @Test
    public void add(){
        SerLoanBanner banner = new SerLoanBanner();
        banner.setImg("img");
        banner.setTitle("test");
        banner.setPid(1L);
        banner.setStatus(1);
        banner.setUrl("url");
        serLoanService.addLoanBanner(banner);
    }

    @Test
    public void edit() throws Exception{
        SerLoanBanner banner = new SerLoanBanner();
        banner.setId(2L);
        banner.setImg("imgdd");
        banner.setTitle("testddq");
        banner.setPid(2L);
        banner.setStatus(0);
        serLoanService.editLoanBanner(banner);
    }

    @Test
    public void del(){
        serLoanService.delLoanBanner(1L);
    }

}
