package com.qmms.web;

import com.qmms.entity.SerCfgMarket;
import com.qmms.entity.SerChangeShow;
import com.qmms.sevice.SerCfgMarketService;
import com.qmms.sevice.SerChannelService;
import com.qmms.sevice.SerConfigService;
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
@RequestMapping("/cfg")
public class ConfigController {
    private static Logger logger = LoggerFactory.getLogger(ConfigController.class);
    @Resource
    private SerCfgMarketService serCfgMarketService;
    @Resource
    private SerConfigService serConfigService;

    //显示下发

    @RequestMapping("/changeShowList")
    public String userInfo(){
        return "/cfg/changeShowList";
    }


    @RequestMapping("/getChangeShowList")
    @ResponseBody
    public Page<SerChangeShow> getChangeShowList(int page, int pageSize, String seachValue){
        Page p1 = serConfigService.getChangeShowListWithCondition(page,pageSize,seachValue);
        return p1;
    }


    @RequestMapping("/toChangeShowAdd")
    public String toChangeShowAdd(Model model){
        List<SerCfgMarket> markets = serCfgMarketService.getMarketList();
        model.addAttribute("markets",markets);
        return "cfg/changeShowAdd";
    }

    @PostMapping("/changeShowAdd")
    @ResponseBody
    public Map<String,String> changeShowAdd(SerChangeShow changeShow){
        Map<String,String> data = new HashMap<>();
        try{
            serConfigService.addChangeShow(changeShow);
            data.put("success","1");
        }catch (Exception e){
            logger.error("changeShowAddError",e);
            data.put("msg",e.getMessage());
        }
        return data;
    }

    @RequestMapping("/toChangeShowEdit")
    public String toChangeShowEdit(Long id,Model model){
        SerChangeShow show = serConfigService.getChangeShow(id);
        model.addAttribute("show",show);
        List<SerCfgMarket> markets = serCfgMarketService.getMarketList();
        model.addAttribute("markets",markets);
        return "/cfg/changeShowEdit";
    }

    @PostMapping("/changeShowEdit")
    @ResponseBody
    public Map<String,String> changeShowEdit(SerChangeShow show){
        Map<String,String> data = new HashMap<>();
        try{
            serConfigService.editChangeShow(show);
            data.put("success","1");
        }catch (Exception e){
            data.put("msg",e.getMessage());
            logger.error("changeShowEditError:",e);
        }

        return data;
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @RequestMapping("/changeShowDel")
    @ResponseBody
    public Map<String,String> changeShowDel(Long id){
        Map<String,String> data = new HashMap<>();
        try{
            serConfigService.delChangeShow(id);
            data.put("success","1");
        }catch (Exception e){
            data.put("msg",e.getMessage());
            logger.error("changeShowDelError",e);
        }
        return data;
    }



}