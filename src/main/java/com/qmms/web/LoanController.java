package com.qmms.web;

import com.qmms.entity.SerLoanBanner;
import com.qmms.entity.SerLoanProduct;
import com.qmms.entity.SerLoanType;
import com.qmms.sevice.SerLoanService;
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
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/loan")
public class LoanController {
    @Value("${web.upload-path}")
    private String webUploadPath;
    @Value("${loanType.img.path}")
    private String loanTypeImgPath;
    @Value("${loanProduct.img.path}")
    private String loanProdutImgPath;
    @Value("${loanBanner.img.path}")
    private String loanBannerImgPath;

    @Resource
    private SerLoanService serLoanService;

    /**
     * 贷款类型
     * @return
     */
    @RequestMapping("/loanTypeList")
    public String loanTypeList(){
        return "/loan/loanTypeList";
    }

    @RequestMapping("/getLoanTypeList")
    @ResponseBody
    public Page<SerLoanType> getLoanTypeList(int page, int pageSize,String title){
        Page p1 = serLoanService.getLoanTypeListWithCondition(page, pageSize, title);
        return p1;
    }

    @RequestMapping("/toLoanTypeAdd")
    public String toUserAdd(){
        return "/loan/loanTypeAdd";
    }

    @RequestMapping("/loanTypeIsExist")
    @ResponseBody
    public Map<String,Boolean> isUserExist(String key){
        SerLoanType loanType = serLoanService.getLoanType(key);
        Map<String,Boolean> rs = new HashMap<String,Boolean>();
        if(loanType!=null){
            rs.put("valid",false);
        }else{
            rs.put("valid",true);
        }
        return rs;
    }  
    @PostMapping("/loanTypeAdd")
    @ResponseBody
    public Map<String,String> loanTypeAdd(SerLoanType loanType){
        String key = loanType.getKey();
        SerLoanType have = serLoanService.getLoanType(key);
        Map<String,String> data = new HashMap<>();
        if(have != null && have.getTitle() != null){
            data.put("errorCode","1");
            data.put("msg","类型ID已经存在");
            return data;
        }
        try{
            serLoanService.addLoanType(loanType);
            data.put("success","1");
            data.put("msg","添加成功");
        }catch (Exception e){
            data.put("msg","添加失败："+e.getMessage());
        }

        return data;
    }

    @RequestMapping("/toLoanTypeEdit")
    public String toUserEdit(String id,Model model){
        SerLoanType loanType = serLoanService.getLoanType(id);
        model.addAttribute("loanType",loanType);
        return "/loan/loanTypeEdit";
    }

    @PostMapping("/loanTypeEdit")
    @ResponseBody
    public Map<String,String> loanTypeEdit(SerLoanType loanType){
        Map<String,String> data = new HashMap<>();
        try{
            serLoanService.editLoanType(loanType);
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
    @RequestMapping("/loanTypeDel")
    @ResponseBody
    public Map<String,String> laonTypeDel(String id){
        Map<String,String> data = new HashMap<>();
        try{
            serLoanService.delLoanType(id);
            data.put("success","1");
            data.put("msg","删除成功");
        }catch (Exception e){
            data.put("msg","删除失败："+e.getMessage());
        }
        return data;
    }

    @PostMapping(value = "/uploadLoanTypeImg", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map<String,String> uploadImg(@RequestParam("file") MultipartFile file){
        return UploadUtil.uploadImg(file,webUploadPath,loanTypeImgPath);
    }

    //贷款产品

    /**
     * 贷款产品列表
     * @return
     */
    @RequestMapping("/loanProductList")
    public String loanProductList(){
        return "/loan/loanProductList";
    }

    /**
     * 贷款产品新增页面
     * @return
     */
    @RequestMapping("/toLoanProductAdd")
    public String toLoanProductAdd(){
        return "/loan/loanProductAdd";
    }
    /**
     * 贷款产品编辑页面
     * @return
     */
    @RequestMapping("/toLoanProductEdit")
    public String toLoanProductEdit(){
        return "/loan/loanProductEdit";
    }

    @RequestMapping("/getLoanProductList")
    @ResponseBody
    public Page<SerLoanProduct> getLoanProductList(int page, int pageSize, String name){
        Page p1 = serLoanService.getLoanProductList(page, pageSize, name);
        return p1;
    }

    @RequestMapping("/loanProductDel")
    public Map<String,String> laonProductDel(Long id){
        Map<String,String> data = new HashMap<>();
        try{
            serLoanService.delLoanProduct(id);
            data.put("success","1");
            data.put("msg","删除成功");
        }catch (Exception e){
            data.put("msg","删除失败："+e.getMessage());
        }
        return data;
    }

    @RequestMapping("/loanProductAdd")
    public Map<String,String> laonProductAdd(SerLoanProduct product,String[] loanType,String[] channelUrl){
        Map<String,String> data = new HashMap<>();
        try{
            serLoanService.addLoanProduct(product,loanType,channelUrl);
            data.put("success","1");
            data.put("msg","添加成功");
        }catch (Exception e){
            data.put("msg","添加失败："+e.getMessage());
        }
        return data;
    }

    @RequestMapping("/loanProductEdit")
    public Map<String,String> loanProductEdit(SerLoanProduct product,String[] loanType,String[] channelUrl){
        Map<String,String> data = new HashMap<>();
        try{
            serLoanService.editLoanProduct(product, loanType, channelUrl);
            data.put("success","1");
            data.put("msg","编辑成功");
        }catch (Exception e){
            data.put("msg","编辑失败："+e.getMessage());
        }
        return data;
    }

    @PostMapping(value = "/uploadLoanProductImg", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map<String,String> uploadLoanProductImg(@RequestParam("file") MultipartFile file){
        return UploadUtil.uploadImg(file,webUploadPath,loanProdutImgPath);
    }

    //贷款广告
    /**
     * 列表
     * @return
     */
    @RequestMapping("/loanBannerList")
    public String loanBannerList(){
        return "/loan/loanBannerList";
    }

    /**
     * 新增页面
     * @return
     */
    @RequestMapping("/toLoanBannerAdd")
    public String toLoanBannerAdd(){
        return "/loan/loanBannerAdd";
    }
    /**
     * 编辑页面
     * @return
     */
    @RequestMapping("/toLoanBannerEdit")
    public String toLoanBannerEdit(){
        return "/loan/loanBannerEdit";
    }

    @RequestMapping("/getLoanBannerList")
    @ResponseBody
    public Page<SerLoanBanner> getLoanBannerList(int page, int pageSize, String title){
        Page p1 = serLoanService.getLoanBannerList(page, pageSize, title);
        return p1;
    }

    @RequestMapping("/loanBannerDel")
    public Map<String,String> loanBannerDel(Long id){
        Map<String,String> data = new HashMap<>();
        try{
            serLoanService.delLoanProduct(id);
            data.put("success","1");
            data.put("msg","删除成功");
        }catch (Exception e){
            data.put("msg","删除失败："+e.getMessage());
        }
        return data;
    }

    @RequestMapping("/loanBannerAdd")
    public Map<String,String> laonBannerAdd(SerLoanBanner banner){
        Map<String,String> data = new HashMap<>();
        try{
            serLoanService.addLoanBanner(banner);
            data.put("success","1");
            data.put("msg","添加成功");
        }catch (Exception e){
            data.put("msg","添加失败："+e.getMessage());
        }
        return data;
    }

    @RequestMapping("/loanBannerEdit")
    public Map<String,String> loanBannerEdit(SerLoanBanner banner){
        Map<String,String> data = new HashMap<>();
        try{
            serLoanService.editLoanBanner(banner);
            data.put("success","1");
            data.put("msg","编辑成功");
        }catch (Exception e){
            data.put("msg","编辑失败："+e.getMessage());
        }
        return data;
    }

    @PostMapping(value = "/uploadLoanBannerImg", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map<String,String> uploadLoanBannerImg(@RequestParam("file") MultipartFile file){
        return UploadUtil.uploadImg(file,webUploadPath,loanBannerImgPath);
    }

}