package com.qmms.web;

import com.qmms.entity.*;
import com.qmms.sevice.SerCreditService;
import com.qmms.utils.UploadUtil;
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
    public String loanTypeList(){
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


}