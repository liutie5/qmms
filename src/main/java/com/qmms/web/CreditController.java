package com.qmms.web;

import com.qmms.entity.SerCreditBank;
import com.qmms.entity.SerCreditType;
import com.qmms.entity.SerCreditBanner;
import com.qmms.entity.SerCreditProduct;
import com.qmms.sevice.SerCreditService;
import com.qmms.utils.UploadUtil;
import com.sun.org.apache.xpath.internal.operations.Mod;
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
import java.util.*;

@Controller
@RequestMapping("/credit")
public class CreditController {
    @Value("${web.upload-path}")
    private String webUploadPath;
    @Value("${creditBank.img.path}")
    private String creditBankImgPath;
    @Value("${creditType.img.path}")
    private String creditTypeImgPath;
    @Value("${creditProduct.img.path}")
    private String creditProdutImgPath;
    @Value("${creditBanner.img.path}")
    private String creditBannerImgPath;

    @Resource
    private SerCreditService serCreditService;

    //信用卡银行
    @RequestMapping("/creditBankList")
    public String creditBankList(){
        return "/credit/creditBankList";
    }

    @RequestMapping("/getCreditBankList")
    @ResponseBody
    public Page<SerCreditBank> getCreditBankList(int page, int pageSize,String name){
        Page p1 = serCreditService.getCreditBankListWithCondition(page, pageSize, name);
        return p1;
    }

    @RequestMapping("/toCreditBankAdd")
    public String toCreditBankAdd(){
        return "/credit/creditBankAdd";
    }


    @PostMapping("/creditBankAdd")
    @ResponseBody
    public Map<String,String> creditBankAdd(SerCreditBank bank){
        Map<String,String> data = new HashMap<>();
        try{
            serCreditService.addCreditBank(bank);
            data.put("success","1");
            data.put("msg","添加成功");
        }catch (Exception e){
            data.put("msg","添加失败："+e.getMessage());
        }
        return data;
    }

    @RequestMapping("/toCreditBankEdit")
    public String toCreditBankEdit(Long id,Model model){
        SerCreditBank bank = serCreditService.getCreditBank(id);
        model.addAttribute("bank",bank);
        return "/credit/creditBankEdit";
    }

    @PostMapping("/creditBankEdit")
    @ResponseBody
    public Map<String,String> creditBankEdit(SerCreditBank bank){
        Map<String,String> data = new HashMap<>();
        try{
            serCreditService.editCreditBank(bank);
            data.put("success","1");
            data.put("msg","编辑成功");
        }catch (Exception e){
            data.put("msg","编辑失败："+e.getMessage());
        }
        return data;
    }


    @RequestMapping("/creditBankDel")
    @ResponseBody
    public Map<String,String> creditBankDel(Long id){
        Map<String,String> data = new HashMap<>();
        try{
            serCreditService.delCreditBank(id);
            data.put("success","1");
            data.put("msg","删除成功");
        }catch (Exception e){
            data.put("msg","删除失败："+e.getMessage());
        }
        return data;
    }

    @PostMapping(value = "/uploadCreditBankImg", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map<String,String> uploadCreditBankImg(@RequestParam("file") MultipartFile file){
        return UploadUtil.uploadImg(file,webUploadPath,creditBankImgPath);
    }


    //信用卡类型
    @RequestMapping("/creditTypeList")
    public String creditTypeList(){
        return "/credit/creditTypeList";
    }

    @RequestMapping("/getCreditTypeList")
    @ResponseBody
    public Page<SerCreditType> getCreditTypeList(int page, int pageSize,String title){
        Page p1 = serCreditService.getCreditTypeListWithCondition(page, pageSize, title);
        return p1;
    }

    @RequestMapping("/toCreditTypeAdd")
    public String toUserAdd(){
        return "/credit/creditTypeAdd";
    }

    @RequestMapping("/creditTypeIsExist")
    @ResponseBody
    public Map<String,Boolean> isUserExist(String key){
        SerCreditType creditType = serCreditService.getCreditType(key);
        Map<String,Boolean> rs = new HashMap<String,Boolean>();
        if(creditType!=null){
            rs.put("valid",false);
        }else{
            rs.put("valid",true);
        }
        return rs;
    }
    @PostMapping("/creditTypeAdd")
    @ResponseBody
    public Map<String,String> creditTypeAdd(SerCreditType creditType){
        String key = creditType.getKey();
        SerCreditType have = serCreditService.getCreditType(key);
        Map<String,String> data = new HashMap<>();
        if(have != null && have.getTitle() != null){
            data.put("errorCode","1");
            data.put("msg","类型ID已经存在");
            return data;
        }
        try{
            serCreditService.addCreditType(creditType);
            data.put("success","1");
            data.put("msg","添加成功");
        }catch (Exception e){
            data.put("msg","添加失败："+e.getMessage());
        }

        return data;
    }

    @RequestMapping("/toCreditTypeEdit")
    public String toUserEdit(String id,Model model){
        SerCreditType creditType = serCreditService.getCreditType(id);
        model.addAttribute("creditType",creditType);
        return "/credit/creditTypeEdit";
    }

    @PostMapping("/creditTypeEdit")
    @ResponseBody
    public Map<String,String> creditTypeEdit(SerCreditType creditType){
        Map<String,String> data = new HashMap<>();
        try{
            serCreditService.editCreditType(creditType);
            data.put("success","1");
            data.put("msg","编辑成功");
        }catch (Exception e){
            data.put("msg","编辑失败："+e.getMessage());
        }
        return data;
    }

    /**
     * 贷款类型删除
     * @return
     */
    @RequestMapping("/creditTypeDel")
    @ResponseBody
    public Map<String,String> laonTypeDel(String id){
        Map<String,String> data = new HashMap<>();
        try{
            serCreditService.delCreditType(id);
            data.put("success","1");
            data.put("msg","删除成功");
        }catch (Exception e){
            data.put("msg","删除失败："+e.getMessage());
        }
        return data;
    }

    @PostMapping(value = "/uploadCreditTypeImg", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map<String,String> uploadImg(@RequestParam("file") MultipartFile file){
        return UploadUtil.uploadImg(file,webUploadPath,creditTypeImgPath);
    }

    @PostMapping(value = "/uploadCreditTypeSubImg", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map<String,String> uploadSubImg(@RequestParam("subImgFile") MultipartFile subImgFile){
        return UploadUtil.uploadImg(subImgFile,webUploadPath,creditTypeImgPath);
    }

    //信用卡广告
    /**
     * 列表
     * @return
     */
    @RequestMapping("/creditBannerList")
    public String creditBannerList(){
        return "/credit/creditBannerList";
    }

    /**
     * 新增页面
     * @return
     */
    @RequestMapping("/toCreditBannerAdd")
    public String toCreditBannerAdd(Model model){
        List<SerCreditProduct> serCreditProductList = serCreditService.getAllCreditProducts();
        model.addAttribute("products",serCreditProductList);
        return "/credit/creditBannerAdd";
    }
    /**
     * 编辑页面
     * @return
     */
    @RequestMapping("/toCreditBannerEdit")
    public String toCreditBannerEdit(Long id,Model model){
        SerCreditBanner banner = serCreditService.getCreditBanner(id);
        model.addAttribute("banner",banner);
        List<SerCreditProduct> serCreditProductList = serCreditService.getAllCreditProducts();
        model.addAttribute("products",serCreditProductList);
        return "/credit/creditBannerEdit";
    }

    @RequestMapping("/getCreditBannerList")
    @ResponseBody
    public Page<SerCreditBanner> getCreditBannerList(int page, int pageSize, String title){
        Page p1 = serCreditService.getCreditBannerList(page, pageSize, title);
        return p1;
    }

    @RequestMapping("/creditBannerDel")
    @ResponseBody
    public Map<String,String> creditBannerDel(Long id){
        Map<String,String> data = new HashMap<>();
        try{
            serCreditService.delCreditBanner(id);
            data.put("success","1");
            data.put("msg","删除成功");
        }catch (Exception e){
            data.put("msg","删除失败："+e.getMessage());
        }
        return data;
    }

    @RequestMapping("/creditBannerAdd")
    @ResponseBody
    public Map<String,String> laonBannerAdd(SerCreditBanner banner){
        Map<String,String> data = new HashMap<>();
        try{
            serCreditService.addCreditBanner(banner);
            data.put("success","1");
            data.put("msg","添加成功");
        }catch (Exception e){
            data.put("msg","添加失败："+e.getMessage());
        }
        return data;
    }

    @RequestMapping("/creditBannerEdit")
    @ResponseBody
    public Map<String,String> creditBannerEdit(SerCreditBanner banner){
        Map<String,String> data = new HashMap<>();
        try{
            serCreditService.editCreditBanner(banner);
            data.put("success","1");
            data.put("msg","编辑成功");
        }catch (Exception e){
            data.put("msg","编辑失败："+e.getMessage());
        }
        return data;
    }

    @PostMapping(value = "/uploadCreditBannerImg", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map<String,String> uploadCreditBannerImg(@RequestParam("file") MultipartFile file){
        return UploadUtil.uploadImg(file,webUploadPath,creditBannerImgPath);
    }

    //信用卡产品
    @RequestMapping("/creditProductList")
    public String creditProductList(Model model){
        List<SerCreditBank> banks = serCreditService.getAllCreditBank();
        model.addAttribute("banks",banks);
        model.addAttribute("lt","liutieshuode");
        return "/credit/creditProductList";
    }

    @RequestMapping("/getCreditProductList")
    @ResponseBody
    public Page<SerCreditProduct> getCreditProductList(int page, int pageSize, String bankId){
        Page p1 = serCreditService.getCreditProductList(page, pageSize, bankId);
        return p1;
    }

}