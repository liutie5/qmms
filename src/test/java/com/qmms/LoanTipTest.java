package com.qmms;

import com.qmms.dao.SerLoanTipDao;
import com.qmms.entity.SerLoanTip;
import com.qmms.sevice.SerLoanService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LoanTipTest extends BasicJPATest{
    @Resource
    SerLoanService serLoanService;
    @Resource
    SerLoanTipDao serLoanTipDao;

    @Test
    public void add(){
        SerLoanTip tip = new SerLoanTip();
        tip.setContext("xiaomi");
        tip.setRmPackage("com.xiaomi.com;com.mi.com");
        tip.setDisplay(1);
        serLoanService.addLoanTip(tip);
    }

    @Test
    public void edit() throws Exception{
        SerLoanTip tip = new SerLoanTip();
        tip.setId(1L);
        tip.setContext("xiaomidddd");
        tip.setRmPackage("com.xiaomi.com;com.mi.comdddd");
        tip.setDisplay(0);
        serLoanService.editLoanTip(tip);
    }

    @Test
    public void del(){
        serLoanService.delLoanTip(1L);
    }

}
