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
        String uv = getUv(request.getCookies());
        try{
            if(StringUtils.isBlank(uv)){
                uv = setUv(response);
            }
            fallback = fallback.trim();
//            String ft = fallback.toLowerCase();
//            if(!ft.startsWith("http://") && !ft.startsWith("https://")){
//                fallback = "http://"+fallback;
//            }
            response.sendRedirect(fallback);
            //add info
            //String pkgKey,String source,String type,String pid,String fallback,String deviceId
            StatLoanUv loanUv = new StatLoanUv(pkgKey,source,type,pid,fallback,uv);
            statLoanUvDao.save(loanUv);

        }catch (Exception e){
            e.printStackTrace();
            logger.error(StringUtils.join("loan_fd_error",pkgKey,source,type,pid,fallback));
        }


    }


    @RequestMapping("/credit")
    public void forwardCredit(String pkgKey,String source,String type,String cardId,String bankId,@RequestParam("fallback")String fallback,HttpServletRequest request,HttpServletResponse response){
        //http://www.qmms.com/forward/loan?umeng=umeng123&mrid=xiaomi&type=product&pid=3&fallback=www.mi.com"}
        String uv = getUv(request.getCookies());
        try{
            if(StringUtils.isBlank(uv)){
                uv = setUv(response);
            }
            fallback = fallback.trim();
//            String ft = fallback.toLowerCase();
//            if(!ft.startsWith("http://") && !ft.startsWith("https://")){
//                fallback = "http://"+fallback;
//            }
            response.sendRedirect(fallback);
            //add info
            //String pkgKey,String source,String type,String pid,String fallback,String deviceId
            StatCreditUv creditUv = new StatCreditUv(pkgKey,source,type,cardId,bankId,fallback,uv);
            statCreditUvDao.save(creditUv);

        }catch (Exception e){
            e.printStackTrace();
            logger.error(StringUtils.join("credit_fd_error",pkgKey,source,type,cardId,bankId,fallback));
        }


    }


    public  String getUv(Cookie[] cookies){
        for(Cookie cookie:cookies){
            if(cookie.getName().equalsIgnoreCase("qmmsuv")){
                return cookie.getValue();
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