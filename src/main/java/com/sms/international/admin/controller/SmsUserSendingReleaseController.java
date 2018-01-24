package com.sms.international.admin.controller;

import com.sms.international.admin.model.SmsUserSendingRelease;
import com.sms.international.admin.service.SmsUserSendingReleaseService;
import com.sms.international.admin.utils.GetRemoteIp;
import com.sms.international.admin.utils.GlobalParams;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Author guojiaju
 * Date 2017/12/14
 * Description
 */
@Controller
@RequestMapping("/releaseSending")
@Scope("prototype")
public class SmsUserSendingReleaseController extends ParentController{

    private static final Logger logger = LoggerFactory.getLogger(SmsUserSendingReleaseController.class);


    @Autowired
    private SmsUserSendingReleaseService releaseService;

    /**
     * 初始化审核队列
     * @return
     */
    @RequestMapping("/init")
    public String init(ModelMap modelMap){
        modelMap.put("countries", GlobalParams.COUNTRY_MAP.get(GlobalParams.COUNTRY_KEY));
        modelMap.put("channels", GlobalParams.CHANNEL_MAP.get(GlobalParams.CHANNEL_KEY));
        return "releaseSending/list";
    }



    @RequestMapping("/findAll")
    @ResponseBody
    public String findAll(@RequestBody SmsUserSendingRelease release){
        return releaseService.findByPage(release).toString();
    }

    /**
     * 审核 通过/驳回
     * @param request
     * @param obj
     * @return
     */
    @RequestMapping(value="/releaseMessage")
    @ResponseBody
    public Map<String, Object> pass(HttpServletRequest request, @RequestBody JSONObject obj){
        Map<String,Integer> result = new HashMap<String, Integer>();
        String md5Vals = obj.getString("mdstr");
        int stat = obj.getInt("stat");
        int count = obj.getInt("count");
        SmsUserSendingRelease r = new SmsUserSendingRelease();
        r.setMdstr(md5Vals);
        r.setCount(count);
        if(stat == 1){//通过
            if(count > 0){
                result = releaseService.passToCount(r);
            }else{
                result = releaseService.pass(r);
            }
        }else if(stat==0){
            SmsUserSendingRelease release = new SmsUserSendingRelease();
            release.setMdStrs(r.getMdstr().split(","));
            result.put("result",releaseService.reject(r));
        }
        resultMap.put("result", result.get("result")>0?"操作成功":"操作失败");
        adminLogs("审核"+(stat == 1 ? "通过":"驳回")+ obj.toString() + (result.get("result")>=0 ? "成功," : "失败,")+ "影响条数:"+(stat ==1 ? result.get("resultCount") : obj.getString("mdstr").split(",").length ), GetRemoteIp.getIp(request));
        return resultMap;
    }


    /**
     * 审核短信到新通道
     * @param request
     * @param obj
     * @return
     */
    @RequestMapping(value="/releaseMessage_to")
    @ResponseBody
    public Map<String, Object> pass_to(HttpServletRequest request,@RequestBody JSONObject obj){
        String md5Vals = obj.getString("mdstr");
        int count = obj.getInt("count");
        int channel = obj.getInt("channel_to");
        SmsUserSendingRelease r = new SmsUserSendingRelease();
        r.setMdstr(md5Vals);
        r.setCount(count);
        r.setChannel(channel);
        Map<String,Integer> result = releaseService.passToCount(r);
        resultMap.put("result", result.get("result") >0 ?"操作成功":"操作失败");
        adminLogs("审核通过操作" + obj.toString() + (result.get("result")>=0 ? "成功," : "失败,")+ "影响条数:"+ result.get("resultCount"), GetRemoteIp.getIp(request));
        return resultMap;
    }

    /**
     * 勾选审核短信
     * @param request
     * @param obj
     * @return
     */
    @RequestMapping(value="/BatchReleaseMessage")
    @ResponseBody
    public Map<String, Object> BatchReleaseMessage(HttpServletRequest request,@RequestBody JSONObject obj){
        Map<String,Integer> result = new HashMap<String, Integer>();
        String md5Vals = obj.getString("mdstr");
        int stat = obj.getInt("stat");
        logger.info(obj.toString());
        SmsUserSendingRelease r = new SmsUserSendingRelease();
        r.setMdstr(md5Vals);
        if(stat == 1){//通过
            int channel = obj.getInt("channel");
            if(channel!=0){
                r.setChannel(channel);
            }
            result = releaseService.pass(r);
        }else if(stat==0){
            SmsUserSendingRelease release = new SmsUserSendingRelease();
            release.setMdStrs(md5Vals.split(","));
            result.put("result",releaseService.reject(r));
        }
        resultMap.put("result", result.get("result")>=0?"操作成功":"操作失败");
        adminLogs("勾选"+(stat ==1 ? "通过":"驳回") + obj.toString() + (result.get("result")>=0 ? "成功," : "失败,")+ "影响条数:"+ ( stat == 1 ? result.get("resultCount") :obj.getString("mdstr").split(",").length),GetRemoteIp.getIp(request));
        return resultMap;
    }

    /**
     * 修改审核队列内容
     * @param obj
     * @return
     */
    @RequestMapping(value="/update")
    @ResponseBody
    public Map<String, Object> update(HttpServletRequest request,@RequestBody JSONObject obj){
        int result = 0;
        String mdstr = obj.getString("mdstr");
        String content = obj.getString("content");
        Map<String, Object> map=new HashMap<String, Object>();
        map.put("mdstr", mdstr);
        map.put("content", content);
        releaseService.updateReleaseContent(map);
        resultMap.put("result", result>=0?"修改成功":"修改失败");
        adminLogs("修改审核队列内容："+obj.getString("content")+"，MDStr："+obj.getString("mdstr")+" ,"+resultMap.get("result"),GetRemoteIp.getIp(request));
        return resultMap;
    }

    /**
     * 搜索审核短信
     * @param request
     * @param obj
     * @return
     */
    @RequestMapping(value="/searchMessage")
    @ResponseBody
    public Map<String, Object> searchMessage(HttpServletRequest request,@RequestBody JSONObject obj){
        Map<String,Integer> result = new HashMap<String, Integer>();
        logger.info(obj.toString());
        int stat = obj.getInt("stat");
        int uid = obj.getInt("uid");
        String content = obj.getString("content");
        int mtype = obj.getInt("mtype");
        int channel = obj.getInt("channel");

        SmsUserSendingRelease r = new SmsUserSendingRelease();
        r.setUid(uid);
        r.setContent(content);
        r.setMtype(mtype);
        r.setChannel(channel);
        if(stat == 1){//通过
            result = releaseService.searchPass(r, obj.getInt("tochannel"));
        }else if(stat==0){
            result = releaseService.searchReject(r);
        }
        adminLogs("搜索["+(stat==1?"通过":"驳回")+"]审核信息, 搜索条件[用户ID:"+r.getUid()+", 内容:"+r.getContent()+", 运营商类型:"+r.getMtype()+", 通道ID:"+r.getChannel()+"], "+(result.get("result")>0?"操作成功,":"操作失败,")+"影响条数:"+result.get("resultCount"),GetRemoteIp.getIp(request));
        resultMap.put("result", result.get("result")>0?"操作成功":"操作失败");
        return resultMap;
    }

    /**
     * 查看号码详情
     * @param release
     * @return
     */
    @RequestMapping("/lookDetail")
    @ResponseBody
    public String lookDetail(@RequestBody SmsUserSendingRelease release){
        return releaseService.lookDetail(release).toString();
    }
}
