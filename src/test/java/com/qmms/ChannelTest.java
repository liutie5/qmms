package com.qmms;

import com.qmms.dao.SerChannelUmengDao;
import com.qmms.entity.SerChannel;
import com.qmms.entity.SerChannelUmeng;
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
    @Resource
    SerChannelUmengDao serChannelUmengDao;

    @Test
    public void add(){
        SerChannel channel = new SerChannel();
        channel.setName("dd");
        String umeng = "ddsadfasf_huawei";
        serChannelService.addChannel(channel,umeng.split(","));
    }

    @Test
    public void edit() throws Exception{
//        SerChannelUmeng umeng = serChannelUmengDao.findFirstByUmengKeyAndMarketId("um123", "");
//        System.out.println(umeng==null);
//        SerChannelUmeng um = serChannelUmengDao.findFirstByUmengKeyAndMarketIdAndChannelIdNot("um123","",1l);
//        System.out.println(um != null);

        String umengStr = "um123_,um123_xiaomi";
//        System.out.println(serChannelService.addChannelUmengMarketExistCheck(umengStr.split(",")));
        System.out.println(serChannelService.editChannelUmengMarketExistCheck(2l,umengStr.split(",")));

    }

    @Test
    public void del(){
        serChannelService.delChannel(1L);
    }

}
