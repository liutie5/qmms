package com.qmms.web;

import com.qmms.entity.api.LoanMain;
import com.qmms.sevice.ApiService;
import com.qmms.utils.WebUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/api")
public class ApiController {
    private static Logger logger = LoggerFactory.getLogger(ApiController.class);
    @Resource
    private ApiService apiService;

    @RequestMapping("loan/loanMain.go")
    @ResponseBody
    public LoanMain loanMain(HttpServletRequest request,String group,String hotType) {
        String domainName = WebUtil.getDomainName(request);
        String pkgName = request.getHeader("pkgName");
        String pkgKey = request.getHeader("pkgKey");
        String source = request.getHeader("source");
        LoanMain loanMain = null;
        try{
            loanMain =  apiService.loanMain(domainName,pkgName,pkgKey,source,group,hotType);
            loanMain.setDesc("success");
            return loanMain;
        }catch (Exception e){
            logger.error("loanMain Error:",e);
            loanMain = new LoanMain();
            loanMain.setCode(1);
            loanMain.setDesc(e.getMessage());
        }
        return  loanMain;
    }


}