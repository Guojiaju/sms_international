package com.sms.international.admin.controller;

import com.sms.international.admin.model.SmsChannel;
import com.sms.international.admin.model.SmsReport;
import com.sms.international.admin.model.SmsSendHistory;
import com.sms.international.admin.service.SmsChannelService;
import com.sms.international.admin.service.SmsReportService;
import com.sms.international.admin.service.SmsSendHistoryService;
import com.sms.international.admin.utils.DateUtil;
import com.sms.international.admin.utils.GetRemoteIp;
import com.sms.international.admin.utils.GlobalParams;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;

/**
 * Author guojiaju
 * Date 2017/11/27
 * Description 发送记录控制类
 */
@Controller
@RequestMapping("/smsSendHistory")
@Scope("prototype")
public class SmsSendHistoryController extends ParentController{

    public static final Logger logger = LoggerFactory.getLogger(SmsSendHistoryController.class);

    @Autowired
    private SmsSendHistoryService smsSendHistoryService;

    @Autowired
    private SmsReportService smsReportService;

    @Autowired
    private SmsChannelService smsChannelService;
    /**
     * 初始化历史记录页面
     * @return
     */
    @RequestMapping("/init")
    public String initHistory(ModelMap modelMap){
        modelMap.put("channel", GlobalParams.CHANNEL_MAP.get(GlobalParams.CHANNEL_KEY));
        modelMap.put("country", GlobalParams.COUNTRY_MAP.get(GlobalParams.COUNTRY_KEY));
        return "sendHistory/list";
    }

    /**
     * 查询发送记录
     * @param smsSendHistory
     * @return
     */
    @RequestMapping("/findData")
    @ResponseBody
    public String findData(@RequestBody SmsSendHistory smsSendHistory){
        //初始化默认查询今天
        if(smsSendHistory.getStartTime() == null){
            smsSendHistory.setStartTime(Long.parseLong(DateUtil.getFormatDateTime(new Date(),"yyyyMMdd000000")));
        }
        //初始化默认查询3天内的数据源
        if(smsSendHistory.getRecords() == null){
            smsSendHistory.setRecords(1);
        }
        //3天内查询mongo，3天前查询mysql
        if(smsSendHistory.getRecords() == 1){
            return smsSendHistoryService.findByPageIn3(smsSendHistory).toString();
        }else{
            return smsSendHistoryService.findByPage(smsSendHistory).toString();
        }
    }

    /**
     * 初始化详情页面
     * @return
     */
    @RequestMapping("/initDetail")
    public String initDetail(ModelMap modelMap, @ModelAttribute SmsSendHistory smsSendHistory){
        modelMap.put("obj",smsSendHistory);
        modelMap.put("channel",GlobalParams.CHANNEL_MAP.get(GlobalParams.CHANNEL_KEY));
        return "/sendHistory/detail";
    }

    /**
     * 查询详情
     * @param smsSendHistory
     * @return
     */
    @RequestMapping("/findDetail")
    @ResponseBody
    public String findDetail(@RequestBody SmsSendHistory smsSendHistory){
        SmsReport report = new SmsReport();
        report.setUid(smsSendHistory.getUid());
        report.setHisId(smsSendHistory.getHisId());
        report.setExpid(smsSendHistory.getExpid());
        report.setSenddate(smsSendHistory.getSenddate());

        //初始化默认查询3天内的数据源
        if(smsSendHistory.getRecords() == null){
            smsSendHistory.setRecords(1);
        }
        //3天内查询mongo，3天前查询mysql
        if(smsSendHistory.getRecords() == 1){
            return smsSendHistoryService.findDetailIn3(smsSendHistory).toString();
        }else{
            return smsReportService.findByPage(report).toString();
        }
    }

    /**
     * 初始化详情页面
     * @return
     */
    @RequestMapping("/initResend")
    public String initResend(ModelMap modelMap, @ModelAttribute SmsSendHistory t){
        if (t.getStartTime() == null) {
			t.setStartTime(Long.valueOf(DateUtil.getFormatCurrentTime("yyyyMMdd") + "000000"));
		}
        modelMap.put("obj",t);
        modelMap.put("channel",GlobalParams.CHANNEL_MAP.get(GlobalParams.CHANNEL_KEY));
        return "/sendHistory/resendList";
    }



    /***
	 * 短信管理-失败补发页面
	 * @param request
	 * @param t
	 * @param model
	 * @return
	 */
	@RequestMapping("/findResend")
    @ResponseBody
	public String findResend(HttpServletRequest request, @RequestBody SmsSendHistory t, ModelMap model) {
		// 默认查找状态为失败的短信
		if (t.getStat() == null) {
			t.setStat(-1);
		}
		if (t.getStartTime() == null) {
			t.setStartTime(Long.valueOf(DateUtil.getFormatCurrentTime("yyyyMMdd") + "000000"));
		}
		return smsSendHistoryService.findResendByPage(t).toString();
	}

	/***
	 * 补发短信
	 * @param request
     * @param obj
	 * @return
	 */
	@RequestMapping("/resend")
    @ResponseBody
	public Map<String, Object> resend(HttpServletRequest request, @RequestBody JSONObject obj) {
	    try{
            JSONArray idJar = obj.getJSONArray("ids");
            String channelStr = obj.getString("channel");
            if(idJar.size() == 0){
                resultMap.put("stat", 0);
                resultMap.put("result", "请选择补发的信息！");
                return resultMap;
            }
            Integer channel = null;
            if(StringUtils.isNotBlank(channelStr))
                channel = Integer.parseInt(channelStr);
            String[] ids = new String[idJar.size()];
            for(int i = 0; i < idJar.size(); i++){
                ids[i] = idJar.getString(i);
            }
            SmsSendHistory resend = new SmsSendHistory();
            resend.setIds(ids);
            SmsChannel smsChannel = null;
            if(channel != null){
                resend.setChannel(channel);
                smsChannel = smsChannelService.findOne(channel);
            }
            Integer result = smsSendHistoryService.resend(resend,smsChannel);

            resultMap.put("stat", result > 0 ? 1 : 0);
            resultMap.put("result", result > 0 ? "补发成功" : "补发失败");
            adminLogs("失败补发短信,"+JSONObject.fromObject(resend) + (result > 0 ? "成功," : "失败,") + "影响条数:" + ids.length  , GetRemoteIp.getIp(request));
            return resultMap;
        }catch(Exception e){
            logger.info(e.getMessage(),e);
        }
        resultMap.put("stat", 0);
        resultMap.put("result", "程序错误！");
        return resultMap;
	}

	/***
	 * 按搜索补发短信
	 *
	 * @param request
	 * @param obj
	 * @return
	 */
	@RequestMapping("/resendByQuery")
    @ResponseBody
	public Map<String, Object> resendByQuery(HttpServletRequest request, @RequestBody JSONObject obj) {
		if (StringUtils.isBlank(obj.getString("stat"))) {
			obj.put("stat",-1);
		}
		Map<String,Integer> map = smsSendHistoryService.resendByQuery(obj);
		adminLogs("按搜索条件重发短信,"+obj.toString()+";新通道:"+obj.getString("changeChannel")+"影响条数:" + resultMap.get("resultCount") ,GetRemoteIp.getIp(request));
		resultMap.put("stat",map.get("count"));
        resultMap.put("result", map.get("result") == 1 ? "操作成功" : "操作失败");
    	return resultMap;
	}
}
