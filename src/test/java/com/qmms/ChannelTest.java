package com.qmms;

import com.qmms.entity.SerChannel;
import com.qmms.sevice.SerChannelService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ChannelTest extends BasicJPATest{
    @Resource
    SerChannelService serChannelService;


    @Test
    public void add(){
        SerChannel channel = new SerChannel();
        channel.setName("dd");
        String umeng = "ddsadfasf_huawei";
        serChannelService.addChannel(channel,umeng.split(","));
    }

    @Test
    public void edit() throws Exception{

    }

    @Test
    public void del(){
        serChannelService.delChannel(1L);
    }

}
