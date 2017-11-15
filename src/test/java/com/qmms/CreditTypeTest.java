package com.qmms;

import com.qmms.entity.SerCreditBank;
import com.qmms.entity.SerCreditType;
import com.qmms.sevice.SerCreditService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CreditTypeTest extends BasicJPATest{
    @Resource
    SerCreditService serCreditService;

    @Test
    public void add(){
        SerCreditType type = new SerCreditType();
        type.setKey("da");
        type.setTitle("db");
        type.setImg("d1");
        type.setDesc("ad");
        type.setSubImg("sb");
        type.setSubDesc("subdesc");
        serCreditService.addCreditType(type);
    }

    @Test
    public void edit() throws Exception{
        SerCreditType type = new SerCreditType();
        type.setKey("da");
        type.setTitle("dbddd");
        type.setImg("d1ddd");
        type.setDesc("ad");
        type.setSubImg("sb");
        type.setSubDesc("dasfasdfadfa");
        serCreditService.editCreditType(type);
    }

    @Test
    public void del(){
        serCreditService.delCreditType("da");
    }

}
