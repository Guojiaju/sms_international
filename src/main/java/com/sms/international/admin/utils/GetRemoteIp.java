package com.sms.international.admin.utils;

import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * Author guojiaju
 * Date 2017/7/14
 * Description 获取客户端ip
 */
public class GetRemoteIp {
    public static String getIp(HttpServletRequest request) {
        try{
            String ip = request.getHeader("X-Forwarded-For");
            if(StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)){
                //多次反向代理后会有多个ip值，第一个ip才是真实ip
                int index = ip.indexOf(",");
                if(index != -1){
                    return ip.substring(0,index);
                }else{
                    return ip;
                }
            }
            ip = request.getHeader("X-Real-IP");
            if(StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)){
                return ip;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return request.getRemoteAddr();
    }
}
