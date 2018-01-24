package com.sms.international.admin.controller;

import com.sms.international.admin.model.SmsBlackMobile;
import com.sms.international.admin.service.SmsBlackMobileService;
import com.sms.international.admin.service.SmsBlackMobileTypeService;
import com.sms.international.admin.servlet.ServiceClient;
import com.sms.international.admin.utils.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author guojiaju
 * Date 2017/12/8
 * Description
 */
@Controller
@RequestMapping("/smsBlackMobile")
@Scope("prototype")
public class SmsBlackMobileController extends ParentController{

    private static final Logger logger = LoggerFactory.getLogger(SmsBlackMobileController.class);

    @Autowired
    private SmsBlackMobileService smsBlackMobileService;

    @Autowired
    private ServiceClient serviceClient;

    @Autowired
    private SmsBlackMobileTypeService smsBlackMobileTypeService;

    /**
     * 初始化系统黑名单
     * @param modelMap
     * @return
     */
    @RequestMapping("/init")
    public String init(ModelMap modelMap){
        try {
            modelMap.put("country",GlobalParams.COUNTRY_MAP.get(GlobalParams.COUNTRY_KEY));
            modelMap.put("mobileType",smsBlackMobileTypeService.getAll());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "sysStrategy/blackMobiles/list";
    }

    /**
     * 分页查询系统黑名单
     * @param mobile
     * @return
     */
    @RequestMapping("/findData")
    @ResponseBody
    public String findData(@RequestBody SmsBlackMobile mobile){
        try{
            return smsBlackMobileService.findByPage(mobile).toString();
        }catch (Exception e){
            e.printStackTrace();
            return "[]";
        }
    }

    /**
     * 批量添加黑名单
     * @return
     */
    @RequestMapping("/addBlackMobile")
    @ResponseBody
    public Map<String,String> addBlackMobile(HttpServletRequest request, @RequestBody SmsBlackMobile mobile){
        Map<String,String> resultMap = new HashMap<String,String>();
        List<Long> list;
        //已存在的
        List<Long> existList;

        String result = "";
        if(mobile.getMobileStr() != null && mobile.getMobileStr() != ""){
            StringBuffer stb = new StringBuffer("添加[");
            stb.append("系统黑名单");
            stb.append("]");
            String [] mobiles = mobile.getMobileStr().split("\n");
            list = new ArrayList<Long>();

            for (String temp : mobiles) {
                if (temp != null && temp.length() > 0) {
                    temp = temp.replace("\r", "");
                    if (!list.contains(Long.parseLong(temp.trim()))) {
                        list.add(Long.parseLong(temp.trim()));
                    }
                }
            }
            try {
                //查询已存在的黑名单
                existList = smsBlackMobileService.getExistMobiles(mobile.getGroup_id(), list);
                //去重
                if (existList != null && existList.size() > 0) {
                    stb.append(" ,已存在[");
                    for (Object o : existList) {
                        stb.append(o.toString()).append(",");
                    }
                    stb.append("],");
                    if (existList != null && existList.size() > 0) {
                        list.removeAll(existList);
                    }
                }

                int success = 0;
                int failed = 0;
                StringBuilder sbsucc = new StringBuilder("成功[");
                StringBuilder sbfail = new StringBuilder("失败[");
                List<SmsBlackMobile> saveList = new ArrayList<SmsBlackMobile>();
                //保存
                if (list != null && list.size() > 0) {
                    int k = 0;
                    for (Long o : list) {
                        if (o.toString().trim().length() > 0) {
                            String cacheMobile = o.toString().trim();
                            SmsBlackMobile blackMobile = new SmsBlackMobile();
                            blackMobile.setMobile(o);
                            blackMobile.setRemark(mobile.getRemark());
                            blackMobile.setGroup_id(mobile.getGroup_id());
                            blackMobile.setMobileType(mobile.getMobileType());
                            blackMobile.setRelation("");
                            blackMobile.setLevel(0);
                            blackMobile.setAddtime(DateUtil.getCurrentDateTime());
                            blackMobile.setCountry("");

                            //调用接口缓存
                            String  update = serviceClient.excuteClient(PubType.STRATEGY_GROUP, PubMethod.UPDATE, "{\"groupType\":" + ConstantSys.BLACK_MOBILE + ",\"groupId\":" + mobile.getGroup_id() + ",\"content\":\"" + URLEncoder.encode(cacheMobile, "UTF-8") + "\"}");
                            if (update != null && Integer.parseInt(update) > 0) {
                                k++;
                                success++;
                                saveList.add(blackMobile);
                                sbsucc.append(cacheMobile + ",");
                            }else{
                                failed++;
                                sbfail.append(cacheMobile + ",");
                            }
                        }
                        if (k % 50 == 0) {
                            smsBlackMobileService.batchSave(saveList);
                            saveList.clear();
                            adminLogs(sbsucc.toString() + "]," + sbfail.toString() + "]", GetRemoteIp.getIp(request));
                            sbsucc.setLength(0);
                            sbfail.setLength(0);
                            sbsucc.append("成功[");
                            sbfail.append("失败[");
                            result = "操作成功,成功(" + success + "),失败(" + failed + "),已存在(" + existList.size() + ")";
                        }
                    }
                    sbsucc.append("]");
                    sbfail.append("]");
                    if (sbsucc.toString().length() > 5 || sbfail.toString().length() > 5) {
                        smsBlackMobileService.batchSave(saveList);
                        super.adminLogs(sbsucc.toString() + "," + sbfail.toString() + "", GetRemoteIp.getIp(request));
                        result = "操作成功,成功(" + success + "),失败(" + failed + "),已存在(" + existList.size() + ")";
                    }
                }else {
                    if (existList.size() > 0) {
                        result = "操作失败，已存在(" + existList.size() + ")";
                        //记录日志
                        super.adminLogs(stb.toString(), GetRemoteIp.getIp(request));
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        resultMap.put("msg",result);
        return resultMap;
    }

    /**
     * 初始化编辑页面
     * @param id
     * @param modelMap
     * @return
     */
    @RequestMapping("/initEdit/{id}")
    public String initEdit(@PathVariable Integer id, ModelMap modelMap){
        if(id > 0) {
            try {
                modelMap.put("mobile", smsBlackMobileService.findOne(id));
                modelMap.put("mobileType",smsBlackMobileTypeService.getAll());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "sysStrategy/blackMobiles/edit";
    }

    /**
     * 执行更新
     * @return
     */
    @RequestMapping("/doUpdate")
    @ResponseBody
    public Integer doUpdate(HttpServletRequest request, @RequestBody SmsBlackMobile mobile){
        logger.info("修改id:{id}黑名单信息",mobile.getId());
        int result ;
        try {
            result = smsBlackMobileService.update(mobile);
        } catch (Exception e) {
            e.printStackTrace();
            result = 0;
        }
        super.adminLogs("修改黑名单信息"+(result > 0 ? "成功,":"失败,")+ mobile.toString(),GetRemoteIp.getIp(request));
        return result;
    }


    /**
     * 删除黑名单
     * @param id
     * @param request
     * @return
     */
    @RequestMapping("/deleteById/{id}")
    @ResponseBody
    public Integer deleteById(@PathVariable Integer id ,HttpServletRequest request){
        logger.info("删除:{id}黑名单信息",id);
        int result;
        try {
            SmsBlackMobile mobile = smsBlackMobileService.findOne(id);
            String temp = serviceClient.excuteClient(PubType.STRATEGY_GROUP, PubMethod.DELETE, "{\"groupType\":" + ConstantSys.BLACK_MOBILE + ",\"groupId\":" + mobile.getGroup_id() + ",\"content\":\"" + URLEncoder.encode(mobile.getMobile()+"", "UTF-8") + "\"}");
            if(temp != null && Integer.parseInt(temp) > 0){
                result = smsBlackMobileService.deleteOne(id);
            }else{
                result =0;
            }
        } catch (Exception e) {
            e.printStackTrace();
            result = 0;
        }
        super.adminLogs("删除黑名单"+(result > 0 ? "成功,":"失败," + "id:"+id),GetRemoteIp.getIp(request));
        return result;
    }
}
