package com.qmms.utils;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;

public class WebUtil {
    static String domainName;
    public static String getDomainName(HttpServletRequest request){
        if(StringUtils.isNotBlank(domainName)){
            return domainName;
        }

        String serverName = request.getScheme()+"://"+request.getServerName();
        int port = request.getServerPort();
        if(port == 80){
            domainName = serverName;
        }else{
            domainName = serverName+":"+port;
        }
        return domainName;
    }



}
