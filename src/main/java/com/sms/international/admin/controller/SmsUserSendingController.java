package com.sms.international.admin.controller;

import com.sms.international.admin.model.SmsUserSending;
import com.sms.international.admin.service.SmsUserSendingService;
import com.sms.international.admin.utils.GetRemoteIp;
import com.sms.international.admin.utils.GlobalParams;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.HtmlUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**队列管理
 * Created by 37985 on 2016/8/11.
 */
@Controller
@RequestMapping(value="/smsUserSending")
public class SmsUserSendingController extends ParentController {
    @Autowired
    private SmsUserSendingService sendingService;

	@RequestMapping("/initPage")
	public String initPage(HttpServletRequest request, @ModelAttribute SmsUserSending t, ModelMap model) {
	    model.put("channel", GlobalParams.CHANNEL_MAP.get(GlobalParams.CHANNEL_KEY));
		model.put("country", GlobalParams.COUNTRY_MAP.get(GlobalParams.COUNTRY_KEY));
		return "/smsUserSending/list";
	}

    @RequestMapping("/findAll")
    @ResponseBody
	public String findAll(HttpServletRequest request,@RequestBody SmsUserSending t, ModelMap model) {
		if(t.getHand_stat() == null){
			t.setHand_stat(0);
		}
		return sendingService.findByPage(t).toString();
	}

    @RequestMapping("/updateContent")
    @ResponseBody
    public Map<String, Object> updateContent(HttpServletRequest request,@RequestBody JSONObject obj){
    	String oldContent = obj.getString("oldContent");
		String changeContent = obj.getString("changeContent");
		long uid = obj.getLong("uid");

		oldContent = HtmlUtils.htmlUnescape(oldContent);
		changeContent = HtmlUtils.htmlUnescape(changeContent);

    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("oldContent", oldContent);
		map.put("uid",uid);
		map.put("changeContent",changeContent);

    	int result = sendingService.update(map);

    	super.adminLogs("修改队列中用户"+uid+"的短信内容:[" + changeContent + "]result:"+result, GetRemoteIp.getIp(request));

    	resultMap.put("stat", result > 0 ? 1 : 0);
    	resultMap.put("result", result > 0 ? "操作成功" : "操作失败");

    	return resultMap;
    }

    @RequestMapping("/switchChannel")
    @ResponseBody
    public Map<String, Object> switchChannel(HttpServletRequest request,@RequestBody JSONObject jo){
    	JSONArray paramIds = jo.getJSONArray("ids");
    	int channel = jo.getInt("channel");

    	if(channel <= 0){
    	    resultMap.put("stat", "0");
    		resultMap.put("result", "请选择通道");
    		return resultMap;
    	}
    	if(paramIds.size() == 0){
    	    resultMap.put("stat", "0");
    		resultMap.put("result", "请先选择要操作的记录");
    		return resultMap;
    	}
        String[] ids = new String[paramIds.size()];
    	for(int i = 0;i < paramIds.size(); i++){
            ids[i] = paramIds.getString(i);
        }
		SmsUserSending searchParams = new SmsUserSending();
		searchParams.setIds(ids);
		List<SmsUserSending> sendList = sendingService.findByIdsToPart(searchParams);

		Map<String,Object> updateMap = new HashMap<String,Object>();
		updateMap.put("channel",channel);
		sendingService.updateMongoSendingHistory(sendList,updateMap);

    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("ids", ids);
    	map.put("toChannel", channel);
		map.put("hand_stat",1);
		//更新数据库中短信处理状态
		int result = sendingService.batchUpdate(map);
    	super.adminLogs("切换短信到通道:[" + channel+"]"+ jo.toString() +",result:"+result, GetRemoteIp.getIp(request));
    	if(result == -1){
    	    resultMap.put("stat","0");
    		resultMap.put("result", "切换通道已暂停，请重新操作");
    		return resultMap;
    	}
        resultMap.put("stat","1");
        resultMap.put("result", "操作成功");
    	return resultMap;
    }

    /**
     * 勾选短信驳回
     * @param request
     * @return
     */
    @RequestMapping(value="/rejectQueueSms")
    @ResponseBody
    public Map<String, Object> rejectQueueSms(HttpServletRequest request,@RequestBody JSONObject obj) {
        JSONArray paramIds = obj.getJSONArray("ids");
        String[] ids = new String[paramIds.size()];
    	for(int i = 0;i < paramIds.size(); i++){
            ids[i] = paramIds.getString(i);
        }
        Map<String,Object> params=new HashMap<String, Object>();
        params.put("ids", ids);
		params.put("hand_stat",2);
        //更新数据库中短信处理状态
        int result = sendingService.batchUpdate(params);
        super.adminLogs("驳回短信:[" + obj.getString("ids")+"]result:"+result, GetRemoteIp.getIp(request));

        resultMap.put("stat", result > 0 ? 1 : 0);
        resultMap.put("result", result > 0 ? "操作成功" : "操作失败");
        return resultMap;
    }

    /**
     * 搜索驳回
     * @param request
     * @return
     */
    @RequestMapping(value="/searchReject")
    @ResponseBody
    public Map<String, Object> searchReject(HttpServletRequest request,@RequestBody JSONObject obj) {
        Map<String,Object> params = new HashMap<String,Object>();

        if(StringUtils.isNotBlank(obj.getString("uid"))){
            params.put("uid",obj.getInt("uid"));
        }

        if(StringUtils.isNotBlank(obj.getString("mobile"))){
            params.put("mobile",Long.valueOf( obj.getString("mobile")));
        }

        if(StringUtils.isNotBlank(obj.getString("content"))){
            params.put("content",obj.getString("content"));
        }

        if(StringUtils.isNotBlank(obj.getString("channel"))){
            params.put("channel",obj.getInt("channel"));
        }

        if(StringUtils.isNotBlank(obj.getString("location"))){
            params.put("location",obj.getInt("location"));
        }
		params.put("hand_stat",2);
        String logs = "搜索驳回短信(用户ID:"+ params.get("uid")+", 手机号码:"+ params.get("mobile") +", " +
                "内容:" + params.get("content") + ", 通道号:" + params.get("channel") + "): ";
		int result = sendingService.batchUpdate(params);
		super.adminLogs(logs + (result > 0 ? "操作成功" : "操作失败"),  GetRemoteIp.getIp(request));

		resultMap.put("stat", result > 0 ? 1 : 0);
        resultMap.put("result", result > 0 ? "操作成功" : "操作失败");
        return resultMap;
    }

    /**
     * 按照搜索条件切换通道(切部分短信)
     * @param request
     * @param obj
     * @return
     */
    @RequestMapping(value="/searchChangeChannel")
    @ResponseBody
    public Map<String, Object> searchChangeChannel_to5(HttpServletRequest request,@RequestBody JSONObject obj) {
        Map<String,Object> params = new HashMap<String,Object>();
        if(StringUtils.isNotBlank(obj.getString("uid")))
            params.put("uid",obj.getInt("uid"));
        if(StringUtils.isNotBlank(obj.getString("mobile")))
            params.put("mobile",Long.valueOf( obj.getString("mobile")));
        if(StringUtils.isNotBlank(obj.getString("content")))
            params.put("content",obj.getString("content"));
        if(StringUtils.isNotBlank(obj.getString("channel")))
            params.put("channel",obj.getInt("channel"));
        if(StringUtils.isNotBlank(obj.getString("location")))
            params.put("location",obj.getInt("location"));
        if(StringUtils.isNotBlank(obj.getString("changeChannel")))
            params.put("changeChannel",obj.getInt("changeChannel"));
		int num = obj.getInt("num") * 10;//num=5为切5万短信,num=10为切10万短信,每次处理1千
		if(params.get("changeChannel") == null){
		    resultMap.put("stat", "0");
			resultMap.put("result", "请选择切换通道!");
			return resultMap;
		}
        int count = 0;
		SmsUserSending searchParams = new SmsUserSending();
		searchParams.setUid((int)params.get("uid"));
		searchParams.setContent((String)params.get("content"));
		searchParams.setChannel((int)params.get("channel"));
		searchParams.setMobile((long)params.get("mobile"));
		searchParams.setLocation((String)params.get("location"));

		Map<String,Object> updateMap = new HashMap<String,Object>();
		updateMap.put("channel",params.get("changeChannel"));

		for (int i = 1; i <= num; i++) {
			List<SmsUserSending> sendlist = sendingService.findByIdsToPart(searchParams);
			if(sendlist.size() == 0){
			    break;
			}
            count += sendlist.size();
			List<Long> ids = new ArrayList<Long>();
			sendingService.updateMongoSendingHistory(sendlist,updateMap);
			params.put("toChannel",updateMap.get("changeChannel"));
			params.put("hand_stat",1);
			params.put("ids", ids);
			//更新短信列表数据状态
			sendingService.batchUpdate(params);
		}
		super.adminLogs("搜索切换"+ num * 1000+"条到通道:[用户:" + obj.getString("uid") + ",原通道:" + obj.getString("channel") +
                ",号码:" + obj.getString("mobile") + ",切换后通道:" + obj.getString("changeChannel") + ",内容:" + obj.getString("content") +
                "]result:"+count, GetRemoteIp.getIp(request));

		resultMap.put("stat","1");
		resultMap.put("result","操作完成,总共" + count + "条");

        return resultMap;
    }
}
