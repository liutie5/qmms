package com.qmms.web;

import com.qmms.entity.SerCfgMarket;
import com.qmms.entity.SerChangeShow;
import com.qmms.entity.SerRnUpdate;
import com.qmms.sevice.SerCfgMarketService;
import com.qmms.sevice.SerConfigService;
import com.qmms.utils.UploadUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/cfg")
public class ConfigController {
    private static Logger logger = LoggerFactory.getLogger(ConfigController.class);

    @Value("${web.upload-path}")
    private String webUploadPath;
    @Value("${rnupdate.file.path}")
    private String rnUpdateFilePath;

    @Resource
    private SerCfgMarketService serCfgMarketService;
    @Resource
    private SerConfigService serConfigService;

    //显示下发

    @RequestMapping("/changeShowList")
    public String changeShowList(){
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

    //升级管理
    @RequestMapping("/rnUpdateList")
    public String rnUpdateList(){
        return "/cfg/rnUpdateList";
    }


    @RequestMapping("/getRnUpdateList")
    @ResponseBody
    public Page<SerRnUpdate> getRnUpdateList(int page, int pageSize, String seachValue){
        Page p1 = serConfigService.getRnUpdateListWithCondition(page,pageSize,seachValue);
        return p1;
    }


    @RequestMapping("/toRnUpdateAdd")
    public String toRnUpdateAdd(Model model){
        List<SerCfgMarket> markets = serCfgMarketService.getMarketList();
        model.addAttribute("markets",markets);
        return "cfg/rnUpdateAdd";
    }

    @PostMapping("/rnUpdateAdd")
    @ResponseBody
    public Map<String,String> rnUpdateAdd(SerRnUpdate rnUpdate){
        Map<String,String> data = new HashMap<>();
        try{
            serConfigService.addRnUpdate(rnUpdate);
            data.put("success","1");
        }catch (Exception e){
            logger.error("rnUpdateAddError",e);
            data.put("msg",e.getMessage());
        }
        return data;
    }

    @RequestMapping("/toRnUpdateEdit")
    public String toRnUpdateEdit(Long id,Model model){
        SerRnUpdate update = serConfigService.getRnUpdate(id);
        model.addAttribute("update",update);
        List<SerCfgMarket> markets = serCfgMarketService.getMarketList();
        model.addAttribute("markets",markets);
        return "/cfg/rnUpdateEdit";
    }

    @PostMapping("/rnUpdateEdit")
    @ResponseBody
    public Map<String,String> rnUpdateEdit(SerRnUpdate update){
        Map<String,String> data = new HashMap<>();
        try{
            serConfigService.editRnUpdate(update);
            data.put("success","1");
        }catch (Exception e){
            data.put("msg",e.getMessage());
            logger.error("rnUpdateEditError:",e);
        }

        return data;
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @RequestMapping("/rnUpdateDel")
    @ResponseBody
    public Map<String,String> rnUpdateDel(Long id){
        Map<String,String> data = new HashMap<>();
        try{
            serConfigService.delRnUpdate(id);
            data.put("success","1");
        }catch (Exception e){
            data.put("msg",e.getMessage());
            logger.error("rnUpdateDelError",e);
        }
        return data;
    }

    @PostMapping(value = "/uploadRnUpdateFile", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map<String,String> uploadCreditProductImg(@RequestParam("file") MultipartFile file){
        return UploadUtil.uploadFile(file,webUploadPath,rnUpdateFilePath,null);
    }

}