package com.qmms.web;

import com.qmms.entity.SerLoanType;
import com.qmms.sevice.SerLoanService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/loan")
public class LoanController {

    @Resource
    private SerLoanService serLoanService;

    /**
     * 贷款类型
     * @return
     */
    @RequestMapping("/loanTypeTList")
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

    @RequestMapping("/loanIsExist")
    @ResponseBody
    public Map<String,Boolean> isUserExist(String title){
        SerLoanType loanType = serLoanService.getLoanType(title);
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

    @RequestMapping("/toLoanEdit")
    public String toUserEdit(String key,Model model){
        SerLoanType loanType = serLoanService.getLoanType(key);
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
    public Map<String,String> laonTypeDel(String key){
        Map<String,String> data = new HashMap<>();
        try{
            serLoanService.delLoanType(key);
            data.put("success","1");
            data.put("msg","删除成功");
        }catch (Exception e){
            data.put("msg","删除失败："+e.getMessage());
        }
        return data;
    }




}