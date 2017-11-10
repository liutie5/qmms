package com.qmms.config;

import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserFormAuthenticationFilter extends FormAuthenticationFilter {
  
    private static final Logger log = LoggerFactory.getLogger(UserFormAuthenticationFilter.class);
      
    @Override  
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
         if (isLoginRequest(request, response)) {  
                if (isLoginSubmission(request, response)) {  
                    if (log.isTraceEnabled()) {  
                        log.trace("Login submission detected.  Attempting to execute login.");  
                    }  
                    return executeLogin(request, response);  
                } else {  
                    if (log.isTraceEnabled()) {  
                        log.trace("Login page view.");  
                    }  
                    //allow them to see the login page ;)  
                    return true;  
                }  
            } else {  
                if (log.isTraceEnabled()) {  
                    log.trace("Attempting to access a path which requires authentication.  Forwarding to the " +  
                            "Authentication url [" + getLoginUrl() + "]");  
                }  
                if(isAjax(request)){
                    ((HttpServletResponse)response).setHeader("sessionstatus","timeout");
                    response.flushBuffer();
                }else{  
                    this.saveRequestAndRedirectToLogin(request, response);  
                }  
                return false;  
            }  
    }  
      
    private static boolean isAjax(ServletRequest request){  
        String header = ((HttpServletRequest) request).getHeader("X-Requested-With");
        if("XMLHttpRequest".equalsIgnoreCase(header)){  
            return Boolean.TRUE;  
        }  
        return Boolean.FALSE;  
    }  
      
}  