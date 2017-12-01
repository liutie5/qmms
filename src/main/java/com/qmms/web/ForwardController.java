package com.qmms.web;

import com.qmms.dao.StatCreditUvDao;
import com.qmms.dao.StatLoanUvDao;
import com.qmms.entity.StatCreditUv;
import com.qmms.entity.StatLoanUv;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/forward")
public class ForwardController {
    private static Logger logger = LoggerFactory.getLogger(ForwardController.class);

    @Resource
    private StatLoanUvDao statLoanUvDao;
    @Resource
    private StatCreditUvDao statCreditUvDao;

    @RequestMapping("/loan")
    public void forwardLoan(String pkgKey,String source,String type,String pid,@RequestParam("fallback")String fallback,HttpServletRequest request,HttpServletResponse response){
        //http://www.qmms.com/forward/loan?umeng=umeng123&mrid=xiaomi&type=product&pid=3&fallback=www.mi.com"}
        try{
            String uv = getUv(request.getCookies());
            if(StringUtils.isBlank(uv)){
                uv = setUv(response);
            }
            fallback = fallback.trim();
            String ft = fallback.toLowerCase();
            if(!ft.startsWith("http://") && !ft.startsWith("https://")){
                fallback = "http://"+fallback;
            }
            response.sendRedirect(fallback);
            //add info
            //String pkgKey,String source,String type,String pid,String fallback,String deviceId
            long pidLong = -1;
            if(StringUtils.isNotBlank(pid) && StringUtils.isNumeric(pid) && pid.length() <= 15){
                pidLong = Long.parseLong(pid);
            }
            String channelName = statLoanUvDao.findChannelName(pidLong,pkgKey,source);
            if(StringUtils.isBlank(channelName)){
                channelName ="默认渠道";
            }
            StatLoanUv loanUv = new StatLoanUv(channelName,pkgKey,source,type,pidLong,fallback,uv);
            statLoanUvDao.save(loanUv);

        }catch (Exception e){
            e.printStackTrace();
            logger.error(StringUtils.join("loan_fd_error",pkgKey,source,type,pid,fallback));
        }


    }


    @RequestMapping("/credit")
    public void forwardCredit(String pkgKey,String source,String type,String cardId,String bankId,@RequestParam("fallback")String fallback,HttpServletRequest request,HttpServletResponse response){
        //http://www.qmms.com/forward/loan?umeng=umeng123&mrid=xiaomi&type=product&pid=3&fallback=www.mi.com"}
        try{
            String uv = getUv(request.getCookies());
            if(StringUtils.isBlank(uv)){
                uv = setUv(response);
            }
            fallback = fallback.trim();
            String ft = fallback.toLowerCase();
            if(!ft.startsWith("http://") && !ft.startsWith("https://")){
                fallback = "http://"+fallback;
            }
            response.sendRedirect(fallback);
            //add info
            //String pkgKey,String source,String type,String pid,String fallback,String deviceId
            long cardIdLong = -1;
            if(StringUtils.isNotBlank(cardId) && StringUtils.isNumeric(cardId) && cardId.length() <= 15){
                cardIdLong = Long.parseLong(cardId);
            }
            String channelName = statCreditUvDao.findChannelName(cardIdLong,pkgKey,source);
            if(StringUtils.isBlank(channelName)){
                channelName ="默认渠道";
            }
            StatCreditUv creditUv = new StatCreditUv(channelName,pkgKey,source,type,cardId,bankId,fallback,uv);
            statCreditUvDao.save(creditUv);

        }catch (Exception e){
            e.printStackTrace();
            logger.error(StringUtils.join("credit_fd_error",pkgKey,source,type,cardId,bankId,fallback));
        }


    }


    public  String getUv(Cookie[] cookies){
        if(cookies != null){
            for(Cookie cookie:cookies){
                if(cookie.getName().equalsIgnoreCase("qmmsuv")){
                    return cookie.getValue();
                }
            }
        }
        return "";
    }

    public String setUv(HttpServletResponse response){
        String uvKey = String.valueOf(System.currentTimeMillis());
        Cookie cookie = new Cookie("qmmsuv",uvKey);
        cookie.setMaxAge(31536000);//365天
        cookie.setPath("/");
        response.addCookie(cookie);
        return uvKey;
    }

}