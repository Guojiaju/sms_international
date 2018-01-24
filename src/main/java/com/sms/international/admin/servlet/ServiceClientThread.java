package com.sms.international.admin.servlet;

import java.util.Map;

/**
 * Author guojiaju
 * Date 2017/11/13
 * Description 同步接口同步数据到8092端口
 */
public class ServiceClientThread implements Runnable {

    private ServiceClient serviceClient;

    private String url ;

    private Map<String, Object> paraMap;

    public ServiceClientThread(ServiceClient serviceClient, String url, Map<String, Object> paraMap) {
        this.serviceClient = serviceClient;
        this.url = url;
        this.paraMap = paraMap;
    }

    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    public void run() {
        try{
            serviceClient.postString(url,paraMap);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
