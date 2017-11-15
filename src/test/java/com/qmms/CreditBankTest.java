package com.qmms;

import com.qmms.entity.SerCreditBank;
import com.qmms.sevice.SerCreditService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CreditBankTest extends BasicJPATest{
    @Resource
    SerCreditService serCreditService;

    @Test
    public void add(){
        SerCreditBank bank = new SerCreditBank();
        bank.setBankName("d1");
        bank.setBankDesc("d2");
        bank.setBankLogo("d3");
        serCreditService.addCreditBank(bank);
    }

    @Test
    public void edit() throws Exception{
        SerCreditBank bank = new SerCreditBank();
        bank.setBankId(1L);
        bank.setBankName("dd1");
        bank.setBankDesc("dd2");
        bank.setBankLogo("dd3");
        bank.setIsJoin(1);
        serCreditService.editCreditBank(bank);
    }

    @Test
    public void del(){
        serCreditService.delCreditBank(1L);
    }

}
