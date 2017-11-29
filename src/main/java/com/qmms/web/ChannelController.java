package com.qmms.web;

import com.qmms.entity.SerCfgMarket;
import com.qmms.entity.SerChannel;
import com.qmms.entity.SerChannelUmeng;
import com.qmms.sevice.SerCfgMarketService;
import com.qmms.sevice.SerChannelService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/channel")
public class ChannelController {
    private static Logger logger = LoggerFactory.getLogger(ChannelController.class);
    @Resource
    private SerChannelService serChannelService;
    @Resource
    private SerCfgMarketService serCfgMarketService;


    /**
     * 渠道列表页
     * @return
     */
    @RequestMapping("/channelList")
    public String userInfo(){
        return "/channel/channelList";
    }

    /**
     * 获取渠道分页
     * @param page
     * @param pageSize
     * @param channelName
     * @return
     */
    @RequestMapping("/getChannelList")
    @ResponseBody
    public Page<SerChannel> getUserList(int page, int pageSize, String channelName){
        Page p1 = serChannelService.getChannelListWithCondition(page,pageSize,channelName);
        for(SerChannel channel:(List<SerChannel>)p1.getContent()){
            for(SerChannelUmeng umeng:channel.getChannelUmengList()){
                umeng.setSerChannel(null);
            }
        }
        return p1;
    }

    /**
     * 添加渠道页面
     * @return
     */
    @RequestMapping("/toChannelAdd")
    public String toChannelAdd(Model model){
        List<SerCfgMarket> markets = serCfgMarketService.getMarketList();
        model.addAttribute("markets",markets);
        return "/channel/channelAdd";
    }

    @PostMapping("/channelAdd")
    @ResponseBody
    public Map<String,String> channelAdd(String name,String desc,String[] umengmarket){
        Map<String,String> data = new HashMap<>();
        try{
            String umCheckMsg = serChannelService.addChannelUmengMarketExistCheck(umengmarket);
            if(StringUtils.isNotBlank(umCheckMsg)){
                data.put("success","0");
                data.put("msg",umCheckMsg);
                return data;
            }
            SerChannel channel = new SerChannel();
            channel.setName(name);
            channel.setDesc(desc);
            serChannelService.addChannel(channel,umengmarket);
            data.put("success","1");
        }catch (Exception e){
            logger.error("channelAddError",e);
            data.put("msg",e.getMessage());
        }
        return data;
    }

    @RequestMapping("/toChannelEdit")
    public String toEdit(Long id,Model model){
        SerChannel serChannel = serChannelService.getChannelById(id);
        model.addAttribute("channel",serChannel);
        List<SerChannelUmeng> umList = serChannel.getChannelUmengList();
        StringBuffer bf = new StringBuffer();
        for(SerChannelUmeng um:umList){
            String out = um.getUmengKey()+"_"+um.getMarketId();
            if(bf.length() ==0){
                bf.append(out);
            }else{
                bf.append(",").append(out);
            }
        }
        model.addAttribute("umengmarket",bf.toString());
        List<SerCfgMarket> markets = serCfgMarketService.getMarketList();
        model.addAttribute("markets",markets);
        return "/channel/channelEdit";
    }

    @PostMapping("/channelEdit")
    @ResponseBody
    public Map<String,String> channelEdit(SerChannel channel,String[] umengmarket){
        Map<String,String> data = new HashMap<>();
        try{
            String umCheckMsg = serChannelService.editChannelUmengMarketExistCheck(channel.getId(),umengmarket);
            if(StringUtils.isNotBlank(umCheckMsg)){
                data.put("success","0");
                data.put("msg",umCheckMsg);
                return  data;
            }
            serChannelService.editChannel(channel,umengmarket);
            data.put("success","1");
        }catch (Exception e){
            data.put("msg",e.getMessage());
            logger.error("channelEdit error:",e);
        }

        return data;
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @RequestMapping("/channelDel")
    @ResponseBody
    public Map<String,String> channelDel(Long id){
        Map<String,String> data = new HashMap<>();
        try{
            serChannelService.delChannel(id);
            data.put("success","1");
        }catch (Exception e){
            data.put("msg",e.getMessage());
            logger.error("channelDelError",e);
        }
        return data;
    }



}