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


    @RequestMapping("credit/creditMain.go")
    @ResponseBody
    public CreditMain creditMain(HttpServletRequest request,String group,String hotType) {
        String domainName = WebUtil.getDomainName(request);
        HeaderMsg headerMsg = WebUtil.getHeadMsg(request);
        CreditMain creditMain = null;
        try{
            creditMain =  apiService.creditMain(domainName, headerMsg.getPkgName(), headerMsg.getPkgKey(), headerMsg.getSource(),group,hotType);
            creditMain.setDesc("success");
            return creditMain;
        }catch (Exception e){
            logger.error("loanMain Error:",e);
            creditMain = new CreditMain();
            creditMain.setCode(1);
            creditMain.setDesc(e.getMessage());
        }
        return  creditMain;
    }

    @RequestMapping("credit/creditList.go")
    @ResponseBody
    public CreditList creditList(HttpServletRequest request,String bankId,String type) {
        String domainName = WebUtil.getDomainName(request);
        HeaderMsg headerMsg = WebUtil.getHeadMsg(request);
        CreditList creditList = null;
        try{
            creditList =  apiService.creditList(domainName, headerMsg.getPkgName(), headerMsg.getPkgKey(), headerMsg.getSource(),bankId,type);
            creditList.setDesc("success");
            return creditList;
        }catch (Exception e){
            logger.error("loanMain Error:",e);
            creditList = new CreditList();
            creditList.setCode(1);
            creditList.setDesc(e.getMessage());
        }
        return  creditList;
    }

    @RequestMapping("credit/creditDetail.go")
    @ResponseBody
    public CreditDetail creditDetail(HttpServletRequest request, String cardId) {
        String domainName = WebUtil.getDomainName(request);
        HeaderMsg headerMsg = WebUtil.getHeadMsg(request);
        CreditDetail creditDetail = null;
        try{
            creditDetail =  apiService.creditDetail(domainName, headerMsg.getPkgName(), headerMsg.getPkgKey(), headerMsg.getSource(), cardId);
        }catch (Exception e){
            logger.error("loanMain Error:",e);
            creditDetail = new CreditDetail();
            creditDetail.setCode(1);
            creditDetail.setDesc(e.getMessage());
        }
        return  creditDetail;
    }

    @RequestMapping("credit/creditTypes.go")
    @ResponseBody
    public CreditTypes creditTypes(HttpServletRequest request, String group) {
        String domainName = WebUtil.getDomainName(request);
        HeaderMsg headerMsg = WebUtil.getHeadMsg(request);
        CreditTypes creditTypes = null;
        try{
            creditTypes =  apiService.creditTypes(domainName, headerMsg.getPkgName(), headerMsg.getPkgKey(), headerMsg.getSource(), group);
        }catch (Exception e){
            logger.error("loanMain Error:",e);
            creditTypes = new CreditTypes();
            creditTypes.setCode(1);
            creditTypes.setDesc(e.getMessage());
        }
        return  creditTypes;
    }


    @RequestMapping("credit/creditBanks.go")
    @ResponseBody
    public CreditBanks creditBanks(HttpServletRequest request) {
        String domainName = WebUtil.getDomainName(request);
        HeaderMsg headerMsg = WebUtil.getHeadMsg(request);
        CreditBanks creditBanks = null;
        try{
            creditBanks =  apiService.creditBanks(domainName, headerMsg.getPkgName(), headerMsg.getPkgKey(), headerMsg.getSource());
        }catch (Exception e){
            logger.error("loanMain Error:",e);
            creditBanks = new CreditBanks();
            creditBanks.setCode(1);
            creditBanks.setDesc(e.getMessage());
        }
        return  creditBanks;
    }

    @RequestMapping("credit/creditProgress.go")
    @ResponseBody
    public CreditProgress creditProgess(HttpServletRequest request) {
        String domainName = WebUtil.getDomainName(request);
        HeaderMsg headerMsg = WebUtil.getHeadMsg(request);
        CreditProgress creditProgress = null;
        try{
            creditProgress =  apiService.creditProgress(domainName, headerMsg.getPkgName(), headerMsg.getPkgKey(), headerMsg.getSource());
        }catch (Exception e){
            logger.error("loanMain Error:",e);
            creditProgress = new CreditProgress();
            creditProgress.setCode(1);
            creditProgress.setDesc(e.getMessage());
        }
        return  creditProgress;
    }

    @RequestMapping("credit/creditPhone.go")
    @ResponseBody
    public CreditPhone creditPhone(HttpServletRequest request) {
        String domainName = WebUtil.getDomainName(request);
        HeaderMsg headerMsg = WebUtil.getHeadMsg(request);
        CreditPhone creditPhone = null;
        try{
            creditPhone =  apiService.creditPhone(domainName, headerMsg.getPkgName(), headerMsg.getPkgKey(), headerMsg.getSource());
        }catch (Exception e){
            logger.error("loanMain Error:",e);
            creditPhone = new CreditPhone();
            creditPhone.setCode(1);
            creditPhone.setDesc(e.getMessage());
        }
        return  creditPhone;
    }

    @RequestMapping("common/changeShow.go")
    @ResponseBody
    public ChangeShow changeShow(HttpServletRequest request, String group) {
        String domainName = WebUtil.getDomainName(request);
        HeaderMsg headerMsg = WebUtil.getHeadMsg(request);
        ChangeShow changeShow = null;
        try{
            changeShow =  apiService.changeShow(domainName, headerMsg.getPkgName(), headerMsg.getPkgKey(), headerMsg.getSource());
            changeShow.setCode(0);
        }catch (Exception e){
            logger.error("changeShow Error:",e);
            changeShow = new ChangeShow();
            changeShow.setCode(1);
            changeShow.setDesc(e.getMessage());
        }
        return  changeShow;
    }

    @RequestMapping("common/rnUpdate.go")
    @ResponseBody
    public RnUpdate rnUpdate(HttpServletRequest request, String version) {
        String domainName = WebUtil.getDomainName(request);
        HeaderMsg headerMsg = WebUtil.getHeadMsg(request);
        RnUpdate rnUpdate = null;
        try{
            rnUpdate =  apiService.rnUpdate(domainName, headerMsg.getPkgName(), headerMsg.getPkgKey(), headerMsg.getSource(),version);
        }catch (Exception e){
            logger.error("rnUpdate Error:",e);
            rnUpdate = new RnUpdate();
            rnUpdate.setCode(1);
            rnUpdate.setDesc(e.getMessage());
        }
        return  rnUpdate;
    }


}