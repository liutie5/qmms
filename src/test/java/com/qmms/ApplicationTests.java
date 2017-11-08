package com.qmms;

import com.qmms.entity.*;
import com.qmms.sevice.SerChannelService;
import com.qmms.sevice.SerLoanService;
import com.qmms.sevice.SysUserInfoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {
    @Resource
    SysUserInfoService sysUserInfoService;
    @Resource
    SerLoanService serLoanService;
    @Resource
    SerChannelService serChannelService;

	@Test
	public void contextLoads() {
	}

    @Test
    public void testAddChannel(){
        SerChannel channel = new SerChannel();
        channel.setName("test");
        channel.setDesc("ddd");
        String[] umengmarkets = new String[2];
        umengmarkets[0] = "aad_xiaomi";
        umengmarkets[1] = "ads_xiaomi";
        serChannelService.addChannel(channel,umengmarkets);
    }

    @Test
    public void testEditChannel(){
        SerChannel channel = new SerChannel();
        channel.setId(3L);
        channel.setName("test2d");
        channel.setDesc("ddd2d");
        String[] umengmarkets = new String[2];
        umengmarkets[0] = "aad_hwd";
        umengmarkets[1] = "ads_hwd";
        serChannelService.editChannel(channel, umengmarkets);
    }

    @Test
    public void delChannel(){
        serChannelService.delChannel(3L);
    }

}
