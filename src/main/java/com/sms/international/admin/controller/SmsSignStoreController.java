package com.sms.international.admin.controller;

import com.sms.international.admin.model.SmsSignStore;
import com.sms.international.admin.service.SmsSignStoreService;
import com.sms.international.admin.servlet.ServiceClient;
import com.sms.international.admin.utils.DateUtil;
import com.sms.international.admin.utils.GetRemoteIp;
import com.sms.international.admin.utils.PubMethod;
import com.sms.international.admin.utils.PubType;
import net.sf.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author guojiaju
 * Date 2017/12/13
 * Description 签名控制类
 */
@Controller
@RequestMapping("/sign")
@Scope("prototype")
public class SmsSignStoreController extends ParentController{
    private static final Logger log = LoggerFactory.getLogger(SmsSignStoreController.class);

    @Autowired
    private SmsSignStoreService smsSignStoreService;

    @Autowired
    private ServiceClient serviceClient;
    /**
     *
     * @return
     */
    @RequestMapping("/init")
    public String init(){
       return "smsSignStore/list";
    }

    /**
     * 查询所有签名信息
     * @param smsSignStore
     * @return
     */
    @RequestMapping("/findAll")
    @ResponseBody
    public String findAll(@RequestBody SmsSignStore smsSignStore){
        return smsSignStoreService.findByPage(smsSignStore).toString() ;
    }

    /**
     * 添加签名
     * @param request
     * @param signStores
     * @return
     */
    @RequestMapping(value = "/doAdd")
    @ResponseBody
    public Map<String,String> doAdd(HttpServletRequest request, @RequestParam(value = "signStores") String signStores) {
        log.info(super.getAdmin().getUsername()+"添加签名");
        Map<String,String> resultMap = new HashMap<String,String>();
        if (signStores != null && !"".equals(signStores)) {
            String[] stores = signStores.split("\n");
            Map<String, String> repeatMap = new HashMap<String, String>();
            int success = 0;
            int faild = 0;
            int exists = 0;
            List<Integer> uids = new ArrayList<Integer>();
            StringBuffer strBuf = new StringBuffer("添加平台签名:[");
            for (String str : stores) {
                str = str.replace("\r", "");
                try {
                    String[] s = str.split(",");
                    String uid = s[0].trim();
                    String expend = s[1];
                    String sign = s[2].trim().replace("'", "''");

                    SmsSignStore signStore = new SmsSignStore();
                    signStore.setUid(Integer.valueOf(uid));
                    signStore.setExpend(expend);
                    signStore.setUserexpend(expend);
                    signStore.setChannel(0);
                    signStore.setType(2);
                    //查找签名是否存在
                    SmsSignStore result = smsSignStoreService.findOneByT(signStore);
                    if (result == null){// 如果该条记录没有添加过，则将其插入
                        if (!repeatMap.containsKey(uid + expend)){// 防止添加重复的记录
                            if(!sign.startsWith("【")){
                                sign="【"+sign;
                            }
                            if(!sign.endsWith("】")){
                                sign=sign+"】";
                            }
                            signStore.setStore(sign);// 签名
                            signStore.setStatus(0);// 报备状态
                            signStore.setUserstat((byte) 1);// 用户账号的状态，是否有效
                            signStore.setAddtime(DateUtil.getCurrentDateTime());
                            Integer num = smsSignStoreService.add(signStore);
                            //如果用户id已经存在则不再add
                            if (num > 0 && !uids.contains(signStore.getUid())) {
                                uids.add(signStore.getUid());
                            }
                            if(num>0){
                                strBuf.append(signStore.getUid()).append(":").append(signStore.getStore()).append("(").append(signStore.getExpend()).append("), ");
                            }
                            success ++;
                            repeatMap.put(uid + expend, "");
                        } else
                            faild ++;
                            continue;
                    }else{
                        exists ++;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            strBuf.append("]");
            for(Integer id : uids){
                serviceClient.excuteClient(PubType.USER_SIGN, PubMethod.UPDATE, "{\"uid\":" + id + "}");
            }
            adminLogs(strBuf.toString(), GetRemoteIp.getIp(request));
            resultMap.put("msg","操作成功,成功("+success+"),失败（"+faild + "),已存在("+exists+")");
        }else{
            resultMap.put("msg","请输入签名");
        }
        return resultMap;
    }


    @RequestMapping("/initUpdate/{id}")
    public String initUpdate(@PathVariable Integer id , ModelMap modelMap){
        modelMap.put("obj",smsSignStoreService.findOne(id));
        return "/smsSignStore/edit";
    }

    /**
     * 更新签名
     * @param request
     * @param smsSignStore
     * @return
     */
    @RequestMapping("/doUpdate")
    @ResponseBody
    public Integer doUpdate(HttpServletRequest request , @RequestBody SmsSignStore smsSignStore){
        log.info(super.getAdmin().getUsername()+"修改签名:" +smsSignStore.toString());
        int result ;
        String sign = smsSignStore.getStore();
        if(sign != null){
            if(!sign.startsWith("【")){
                sign="【"+sign;
            }
            if(!sign.endsWith("】")){
                sign=sign+"】";
            }
            smsSignStore.setStore(sign);
        }
        try {
            serviceClient.excuteClient(PubType.USER_SIGN, PubMethod.UPDATE, "{\"uid\":" + smsSignStore.getUid() + "}");
            result = smsSignStoreService.update(smsSignStore);
        } catch (Exception e) {
            e.printStackTrace();
            result =0 ;
        }
        if(smsSignStore.getExpend()!=null && smsSignStore.getExpend().length()>0){
            adminLogs("修改平台签名, 用户ID:"+smsSignStore.getUid()+", 拓展号:"+smsSignStore.getExpend()+", 用户推送拓展号:"+smsSignStore.getUserexpend()+", 签名:"+smsSignStore.getStore()+", 操作"+(result > 0?"成功":"失败"), GetRemoteIp.getIp(request));
        }
        return result;
    }

    /**
     * 勾选删除签名
     * @param request
     * @param ids
     * @return
     */
    @RequestMapping("/batchRemove")
    @ResponseBody
    public int batchRemove(HttpServletRequest request ,@RequestParam("ids") String [] ids){
        log.info(super.getAdmin().getUsername()+"勾选删除签名,ids:" +ids);
        int result;
        try {
            result = smsSignStoreService.batchRemove(ids);
            if (result > 0) {
                // 修改缓存
                serviceClient.excuteClient(PubType.USER_SIGN, PubMethod.RELOAD, "{\"uid\": 0}");
            }
        } catch (Exception e) {
            e.printStackTrace();
            result = 0;
        }
        adminLogs("批量删除平台签名:"+ JSONArray.fromObject(ids) + "影响条数:" + result,GetRemoteIp.getIp(request));

        return result;
    }
    /**
     * 删除用户签名
     * @param request
     * @param id
     * @return
     */
    @RequestMapping("/deleteById/{id}")
    @ResponseBody
    public Integer deleteById(HttpServletRequest request,@PathVariable Integer id){
        log.info(super.getAdmin().getUsername()+"删除签名,id:" +id);
        SmsSignStore smsSignStore = smsSignStoreService.findOne(id);
        boolean result = false;
        if (smsSignStore != null) {
            try {
                result = smsSignStoreService.deleteById(id);
                serviceClient.excuteClient(PubType.USER_SIGN, PubMethod.DELETE, "{\"uid\":" + smsSignStore.getUid()  + ",\"expend\":\"" + smsSignStore.getExpend() + "\",\"type\":" + smsSignStore.getType() + "}");
            } catch (Exception e) {
                e.printStackTrace();
            }
            super.adminLogs("删除平台签名, 用户ID:"+smsSignStore.getUid()+", 签名:"+smsSignStore.getStore()+", 操作"+(result?"成功":"失败"), GetRemoteIp.getIp(request));
        }
        return result == true ? 1 : 0 ;
    }

    /**
     *
     * @param id
     * @param expend
     * @param type
     * @return
     */
    @RequestMapping("/checkExpend/{id}/{expend}/{type}")
    @ResponseBody
    public int checkExpend(@PathVariable Integer id ,@PathVariable String expend,@PathVariable Integer type){
        SmsSignStore smsSignStore = new SmsSignStore();
        smsSignStore.setExpend(expend);
        smsSignStore.setType(type);
        SmsSignStore tmp = smsSignStoreService.findOneByT(smsSignStore);
        int result =0;
        if(null != tmp && !tmp.getId().equals(id)){
            result = 1;
        }
        return result ;
    }

    /**
     * 搜索删除签名
     * @param request
     * @param smsSignStore
     * @return
     */
    @RequestMapping("/removeBySearch")
    @ResponseBody
    public Integer removeBySearch(HttpServletRequest request,@RequestBody SmsSignStore smsSignStore){
        log.info(super.getAdmin().getUsername()+"按照搜索条件删除签名。" + smsSignStore.toString());
        int result ;
        try {
            result = smsSignStoreService.removeByT(smsSignStore);
            serviceClient.excuteClient(PubType.USER_SIGN, PubMethod.RELOAD, "{\"uid\": 0}");
        } catch (Exception e) {
            e.printStackTrace();
            result = 0;
        }
        super.adminLogs("搜索删除签名"+(result > 0 ? "成功.":"失败.") + smsSignStore.toString(), GetRemoteIp.getIp(request));
        return result;
    }

    /**
     * 搜索报备签名
     * @param request
     * @param smsSignStore
     * @return
     */
    @RequestMapping("/registerBySearch")
    @ResponseBody
    public Integer registerBySearch(HttpServletRequest request,@RequestBody SmsSignStore smsSignStore){
        log.info(super.getAdmin().getUsername()+"搜索报备签名:" +smsSignStore.toString());
        int result ;
        try {
            result = smsSignStoreService.registerBySearch(smsSignStore);
            serviceClient.excuteClient(PubType.USER_SIGN, PubMethod.RELOAD, "{\"uid\": 0}");
        } catch (Exception e) {
            e.printStackTrace();
            result =0 ;
        }
        adminLogs("搜索报备签名"+(result > 0?"成功.":"失败.")+ smsSignStore.toString(), GetRemoteIp.getIp(request));
        return result;
    }
}
