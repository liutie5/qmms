package com.qmms.web;

import com.qmms.entity.SerLoanProduct;
import com.qmms.entity.SerLoanType;
import com.qmms.sevice.SerLoanService;
import org.apache.commons.lang3.StringUtils;
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
import java.io.File;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/loan")
public class LoanController {
    @Value("${web.upload-path}")
    private String webUploadPath;
    @Value("${loanType.img.path}")
    private String loanTypeImgPath;

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
        Map<String,String> data = new HashMap<>();
        if (!file.isEmpty()) {
            if (file.getContentType().contains("image")) {
                try {
                    // 获取图片的文件名
                    String fileName = file.getOriginalFilename();
                    // 获取图片的扩展名
                    String extensionName = StringUtils.substringAfter(fileName, ".");
                    // 新的图片文件名 = 获取时间戳+"."图片扩展名
                    String newFileName = String.valueOf(System.currentTimeMillis()) + "." + extensionName;
                    // 数据库保存的目录
                    String dataPath = loanTypeImgPath.concat(newFileName);
                    // 文件路径
                    String filePath = webUploadPath.concat(loanTypeImgPath);
                    File dest = new File(filePath, newFileName);
                    if (!dest.getParentFile().exists()) {
                        dest.getParentFile().mkdirs();
                    }
                    // 上传到指定目录
                    file.transferTo(dest);
                    data.put("success","1");
                    data.put("imgPath",dataPath);
                }catch (Exception e){
                    data.put("msg","上传失败:"+e.getMessage());
                }
            }
        }
        return data;
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

    @RequestMapping("/getLoanProductList")
    @ResponseBody
    public Page<SerLoanProduct> getLoanProductList(int page, int pageSize, String name){
        try{
            Page p1 = serLoanService.getLoanProductList(page, pageSize, name);
            return p1;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;

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


}