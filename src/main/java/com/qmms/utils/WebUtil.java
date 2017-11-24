package com.qmms.utils;

import com.qmms.entity.api.HeaderMsg;
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

    public static String refreshDomainName(HttpServletRequest request){
       domainName = null;
       return domainName;
    }

    public static HeaderMsg getHeadMsg(HttpServletRequest request){
        String pkgName = request.getHeader("pkgName");
        String pkgKey = request.getHeader("pkgKey");
        String source = request.getHeader("source");
        if(pkgName == null){
            pkgName = "";
        }
        if(pkgKey == null){
            pkgKey = "";
        }
        if(source == null){
            source = "";
        }
        HeaderMsg msg = new HeaderMsg();
        msg.setPkgKey(pkgKey);
        msg.setPkgName(pkgName);
        msg.setSource(source);
        return msg;
    }


}
