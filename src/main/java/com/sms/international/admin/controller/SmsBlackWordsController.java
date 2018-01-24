package com.sms.international.admin.controller;

import com.sms.international.admin.model.SmsBlackWords;
import com.sms.international.admin.service.SmsBlackWordsService;
import com.sms.international.admin.servlet.ServiceClient;
import com.sms.international.admin.utils.*;
import org.slf4j.Logger;
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
 * Date 2017/12/9
 * Description 系统屏蔽词控制层
 */
@Controller
@RequestMapping("/blackWords")
@Scope("prototype")
public class SmsBlackWordsController extends ParentController {
    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(SmsBlackWordsController.class);

    @Autowired
    private SmsBlackWordsService smsBlackWordsService;

    @Autowired
    private ServiceClient serviceClient;

    /**
     * 初始化审核屏蔽词
     * @param modelMap
     * @return
     */
    @RequestMapping("/initReleaseWords")
    public String initReleaseWords(ModelMap modelMap){
        modelMap.put("group_id", 5);
        modelMap.put("groupType",ConstantSys.RELEASE_BLACK_WORDS);
        modelMap.put("screenType",GlobalParams.BLACK_WORDS);
        return "sysStrategy/blackWords/sys_release_wordList";
    }

    /**
     * 查询屏蔽词
     * @param smsBlackWords
     * @return
     */
    @RequestMapping("/findData")
    @ResponseBody
    public String findData(@RequestBody SmsBlackWords smsBlackWords){
        return smsBlackWordsService.findByPage(smsBlackWords).toString();
    }


    /**
     * 批量添加屏蔽词
     * @return
     */
    @RequestMapping("/addBlackWords")
    @ResponseBody
    public Map<String,String> addBlackMobile(HttpServletRequest request, @RequestBody SmsBlackWords words){
        Map<String,String> resultMap = new HashMap<String,String>();
        List<String> list;
        //已存在的
        List<String> existList;

        String result = "";
        if(words.getWordsStr() != null && words.getWordsStr() != ""){
            StringBuffer stb = new StringBuffer("添加[");
            stb.append("系统屏蔽词");
            stb.append("]");
            String [] wordss = words.getWordsStr().split("\n");
            list = new ArrayList<String>();

            for (String temp : wordss) {
                if (temp != null && temp.length() > 0) {
                    temp = temp.replace("\r", "");
                    if (!list.contains(temp.trim())) {
                        list.add(temp.trim());
                    }
                }
            }
            try {
                //查询已存在的黑名单
                existList = smsBlackWordsService.getExistWords(words.getGroup_id(), list);
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
                List<SmsBlackWords> saveList = new ArrayList<SmsBlackWords>();
                //保存
                if (list != null && list.size() > 0) {
                    int k = 0;
                    for (String o : list) {
                        if (o.toString().trim().length() > 0) {
                            String cacheWords = o.toString().trim();
                            SmsBlackWords word = new SmsBlackWords();
                            word.setWords(o);
                            word.setRemark(words.getRemark());
                            word.setGroup_id(words.getGroup_id());
                            word.setAddtime(DateUtil.getCurrentDateTime());
                            word.setScreenType(words.getScreenType());

                            //调用接口缓存
                            String update = serviceClient.excuteClient(PubType.STRATEGY_GROUP, PubMethod.UPDATE, "{\"screenType\":" + words.getScreenType() + ",\"level\":" + GlobalParams.BLACK_WORDS.get(words.getScreenType()).getLevel() + ",\"groupType\":" + words.getGroupType() + ",\"groupId\":" + words.getGroup_id() + ",\"content\":\"" + URLEncoder.encode(cacheWords, "UTF-8") + "\"}");
                            if (update != null && Integer.parseInt(update) > 0) {
                                k++;
                                success++;
                                saveList.add(word);
                                sbsucc.append(cacheWords + ",");
                            }else{
                                failed++;
                                sbfail.append(cacheWords + ",");
                            }
                        }
                        if (k % 50 == 0) {
                            smsBlackWordsService.batchSave(saveList);
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
                        smsBlackWordsService.batchSave(saveList);
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
    @RequestMapping("/initEdit/{id}/{groupType}")
    public String initEdit(@PathVariable Integer id, @PathVariable Integer groupType, ModelMap modelMap){
        if(id > 0) {
            try {
                modelMap.put("words", smsBlackWordsService.findOne(id));
                modelMap.put("screenType",GlobalParams.BLACK_WORDS);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        modelMap.put("groupType",groupType);
        return "sysStrategy/blackWords/edit";
    }

    /**
     * 执行更新
     * @return
     */
    @RequestMapping("/doUpdate")
    @ResponseBody
    public Integer doUpdate(HttpServletRequest request, @RequestBody SmsBlackWords words){
        logger.info("修改id:{id}屏蔽词信息",words.getId());
        int result ;
        try {
            SmsBlackWords old = smsBlackWordsService.findOne(words.getId());
            String delete = serviceClient.excuteClient(PubType.STRATEGY_GROUP, PubMethod.DELETE, "{\"screenType\":" + old.getScreenType() + ",\"level\":" + GlobalParams.BLACK_WORDS.get(old.getScreenType()).getLevel() + ",\"groupType\":" + words.getGroupType() + ",\"groupId\":" + old.getGroup_id() + ",\"content\":\"" + URLEncoder.encode(old.getWords(), "UTF-8") + "\"}");
            String update = serviceClient.excuteClient(PubType.STRATEGY_GROUP, PubMethod.UPDATE, "{\"screenType\":" + words.getScreenType() + ",\"level\":" + GlobalParams.BLACK_WORDS.get(words.getScreenType()).getLevel() + ",\"groupType\":" + words.getGroupType() + ",\"groupId\":" + words.getGroup_id() + ",\"content\":\"" + URLEncoder.encode(words.getWords(), "UTF-8") + "\"}");
            if (Integer.parseInt(delete) == 1 && Integer.parseInt(update) == 1) {
                result = smsBlackWordsService.update(words);
            }else{
                result = 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
            result = 0;
        }
        super.adminLogs("修改屏蔽词"+(result > 0 ? "成功,":"失败,")+ words.toString(),GetRemoteIp.getIp(request));
        return result;
    }


    /**
     * 删除黑名单
     * @param id
     * @param request
     * @return
     */
    @RequestMapping("/deleteById/{id}/{groupType}")
    @ResponseBody
    public Integer deleteById(@PathVariable Integer id ,@PathVariable Integer groupType,HttpServletRequest request){
        logger.info("删除:{id}屏蔽词信息",id);
        int result;
        try {
            SmsBlackWords words = smsBlackWordsService.findOne(id);
            String temp = serviceClient.excuteClient(PubType.STRATEGY_GROUP, PubMethod.DELETE, "{\"groupType\":" + groupType + ",\"groupId\":" + words.getGroup_id() + ",\"content\":\"" + URLEncoder.encode(words.getWords(), "UTF-8") + "\"}");
            if(temp != null && Integer.parseInt(temp) > 0){
                result = smsBlackWordsService.removeById(id);
            }else{
                result =0;
            }
        } catch (Exception e) {
            e.printStackTrace();
            result = 0;
        }
        super.adminLogs("删除屏蔽词"+(result > 0 ? "成功,":"失败," + "id:"+id),GetRemoteIp.getIp(request));
        return result;
    }
}
