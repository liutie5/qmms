package com.qmms.web;

import com.qmms.entity.api.*;
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
        HeaderMsg headerMsg = WebUtil.getHeadMsg(request);
        LoanMain loanMain = null;
        try{
            loanMain =  apiService.loanMain(domainName, headerMsg.getPkgName(), headerMsg.getPkgKey(), headerMsg.getSource(),group,hotType);
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

    @RequestMapping("loan/loanPlist.go")
    @ResponseBody
    public LoanPlist loanPlist(HttpServletRequest request, String type, String balance,String term) {
        String domainName = WebUtil.getDomainName(request);
        HeaderMsg headerMsg = WebUtil.getHeadMsg(request);
        LoanPlist loanPlist = null;
        try{
            loanPlist =  apiService.loanPlist(domainName, headerMsg.getPkgName(), headerMsg.getPkgKey(), headerMsg.getSource(),type,balance,term);
        }catch (Exception e){
            logger.error("loanMain Error:",e);
            loanPlist = new LoanPlist();
            loanPlist.setCode(1);
            loanPlist.setDesc(e.getMessage());
        }
        return  loanPlist;
    }

    @RequestMapping("loan/loanPdetail.go")
    @ResponseBody
    public LoanPdetail loanPdetail(HttpServletRequest request, String pid) {
        String domainName = WebUtil.getDomainName(request);
        HeaderMsg headerMsg = WebUtil.getHeadMsg(request);
        LoanPdetail loanPdetail = null;
        try{
            loanPdetail =  apiService.loanPdetail(domainName, headerMsg.getPkgName(), headerMsg.getPkgKey(), headerMsg.getSource(), pid);
        }catch (Exception e){
            logger.error("loanMain Error:",e);
            loanPdetail = new LoanPdetail();
            loanPdetail.setCode(1);
            loanPdetail.setDesc(e.getMessage());
        }
        return  loanPdetail;
    }

    @RequestMapping("loan/loanTypes.go")
    @ResponseBody
    public LoanTypes loanTypes(HttpServletRequest request, String group) {
        String domainName = WebUtil.getDomainName(request);
        HeaderMsg headerMsg = WebUtil.getHeadMsg(request);
        LoanTypes loanTypes = null;
        try{
            loanTypes =  apiService.loanTypes(domainName, headerMsg.getPkgName(), headerMsg.getPkgKey(), headerMsg.getSource(), group);
        }catch (Exception e){
            logger.error("loanMain Error:",e);
            loanTypes = new LoanTypes();
            loanTypes.setCode(1);
            loanTypes.setDesc(e.getMessage());
        }
        return  loanTypes;
    }

    @RequestMapping("domainRefresh")
    @ResponseBody
    public String domainRefresh(HttpServletRequest request) {
        return WebUtil.refreshDomainName(request);
    }



}