package com.qmms.web;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/forward")
public class ForwardController {
    private static Logger logger = LoggerFactory.getLogger(ForwardController.class);

    @RequestMapping("/loan")
    public void forward(Long cid,Long pid,String mid,@RequestParam("fallback")String fallback,HttpServletRequest request,HttpServletResponse response){
        String uv = getUv(request.getCookies());
        try{
            if(StringUtils.isBlank(uv)){
                uv = setUv(response);
            }
            fallback = fallback.trim();
            String ft = fallback.toLowerCase();
            if(!ft.startsWith("http://") || !ft.startsWith("https://")){
                fallback = "http://"+fallback;
            }
            response.sendRedirect(fallback);
            //add info
        }catch (Exception e){
            e.printStackTrace();
            logger.error(StringUtils.join("loan_fd_error",cid,pid,mid,fallback));
        }

        System.out.println(cid+","+pid+","+mid+","+uv);

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