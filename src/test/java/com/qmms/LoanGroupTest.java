package com.qmms;

import com.qmms.entity.SerLoanGroup;
import com.qmms.entity.SerLoanTip;
import com.qmms.sevice.SerLoanService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LoanGroupTest extends BasicJPATest{
    @Resource
    SerLoanService serLoanService;

    @Test
    public void add(){
        SerLoanGroup group = new SerLoanGroup();
        group.setId("default");
        group.setName("默认组");
        String channelType = "dd,dd1";
        serLoanService.addLoanGroup(group, channelType.split(","));
    }

    @Test
    public void edit() throws Exception{
        SerLoanGroup group = new SerLoanGroup();
        group.setId("default");
        group.setName("默认组1");
        String channelType = "dd";
        serLoanService.editLoanGroup(group, channelType.split(","));
    }

    @Test
    public void del(){
        serLoanService.delLoanGroup("default");
    }

}
