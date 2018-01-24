package com.sms.international.admin.controller;

import com.sms.international.admin.model.SmsChannel;
import com.sms.international.admin.model.SmsChannelPriceMenu;
import com.sms.international.admin.service.SmsChannelPriceMenuService;
import com.sms.international.admin.service.SmsChannelService;
import com.sms.international.admin.servlet.ServiceClient;
import com.sms.international.admin.utils.GetRemoteIp;
import com.sms.international.admin.utils.GlobalParams;
import com.sms.international.admin.utils.PubMethod;
import com.sms.international.admin.utils.PubType;
import net.sf.json.JSONObject;
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
import java.util.List;

/**
 * Author guojiaju
 * Date 2017/11/29
 * Description 通道信息控制类
 */
@RequestMapping("/channel")
@Controller
@Scope("prototype")
public class SmsChannelController extends ParentController{

    public static final Logger logger = LoggerFactory.getLogger(SmsUserController.class);

    @Autowired
    private SmsChannelService smsChannelService;

    @Autowired
    private SmsChannelPriceMenuService smsChannelPriceMenuService;

    @Autowired
    private ServiceClient serviceClient;

    /**
     * 初始化通道列表页面
     * @return
     */
    @RequestMapping("/init")
    public String init(){
        return "channel/list";
    }

    /**
     * 查询所有的通道信息
     * @param smsChannel
     * @return
     */
    @RequestMapping("/findAll")
    @ResponseBody
    public String findAll(@RequestBody SmsChannel smsChannel){
        try {
            return smsChannelService.findAll(smsChannel).toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 查看通道详情
     * @param id
     * @param modelMap
     * @return
     */
    @RequestMapping("/detail/{id}")
    public String detail(@PathVariable Integer id ,ModelMap modelMap){
        try {
            modelMap.put("channel",smsChannelService.findOne(id));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "channel/detail";
    }

    /**
     * 初始化编辑页面
     * @param channelId
     * @return
     */
    @RequestMapping("/toAddOrEdit/{channelId}")
    public String toAddOrEdit(@PathVariable Integer channelId, ModelMap modelMap){
        try {
            if(channelId !=null && channelId >0){
                modelMap.put("channel",smsChannelService.findOne(channelId));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "/channel/edit";
    }

    /**
     * 新增或者修改通道信息
     * @param request
     * @param smsChannel
     * @return
     */
    @RequestMapping("/doAddOrEdit")
    @ResponseBody
    public Integer doAddOrEdit(HttpServletRequest request ,@RequestBody SmsChannel smsChannel){
        int result;
        try {
            if(smsChannel.getId() != null && smsChannel.getId()>0){
                result = smsChannelService.update(smsChannel);
            }else{
                result = smsChannelService.save(smsChannel);
            }
            //重新加载系统通道缓存
            GlobalParams.CHANNEL_MAP.put(GlobalParams.CHANNEL_KEY,smsChannelService.getAll());

            serviceClient.excuteClient(PubType.CHANNEL, PubMethod.UPDATE, "{\"channelId\":" + smsChannel.getId() + "}");
        } catch (Exception e) {
            e.printStackTrace();
            result = 0;
        }
        super.adminLogs("编辑通道信息"+(result > 0 ? "成功.":"失败.")+ JSONObject.fromObject(smsChannel),GetRemoteIp.getIp(request));
        return result;
    }

    /**
     * 修改通道状态
     * @param request
     * @param smsChannel
     * @return
     */
    @RequestMapping("/enableOrDisable")
    @ResponseBody
    public Integer enableOrDisable(HttpServletRequest request,@RequestBody SmsChannel smsChannel){
        int result;
        try {
            result = smsChannelService.update(smsChannel);
            serviceClient.excuteClient(PubType.CHANNEL, PubMethod.UPDATE, "{\"channelId\":" + smsChannel.getId() + "}");
        } catch (Exception e) {
            e.printStackTrace();
            result = 0;
        }
        super.adminLogs((smsChannel.getStatus() == 0 ? "启用通道":"停止通道") + (result > 0 ? "成功,":"失败,")+"ID:"+smsChannel.getId(),GetRemoteIp.getIp(request));
        return result;
    }

    /**
     * 初始化资源配置页面
     * @return
     */
    @RequestMapping("/initResources/{channelId}")
    public String initResources(@PathVariable Integer channelId,ModelMap modelMap){
        try {
            modelMap.put("channelPriceMenu",smsChannelPriceMenuService.findByChannelId(channelId));
        } catch (Exception e) {
            e.printStackTrace();
        }
        modelMap.put("country", GlobalParams.COUNTRY_MAP.get(GlobalParams.COUNTRY_KEY));
        modelMap.put("channelId",channelId);
        return "channel/resources";
    }

    /**
     * 保存通道价目信息
     * @param request
     * @param id
     * @param menus
     * @return
     */
    @RequestMapping("/saveResources/{id}")
    @ResponseBody
    public Integer saveResources(HttpServletRequest request,@PathVariable Integer id , @RequestBody List<SmsChannelPriceMenu> menus){
        logger.info("修改通道Id:"+id+"的价目表");
        //先删除该uid下所有的价目信息，再保存一次
        int result ;
        try {
            result = smsChannelPriceMenuService.deleteByChannelId(id);
            if(menus == null || menus.size()<=0){
                return result;
            }else{
                result = smsChannelPriceMenuService.batchSave(menus);
            }
            serviceClient.excuteClient(PubType.CHANNEL_PRICE, PubMethod.RELOAD, "{\"channelId\":" + id + "}");
        } catch (Exception e) {
            e.printStackTrace();
            result = -1;
        }
        super.adminLogs("修改通道Id:"+id+"的价目表。"+(result >0 ? "成功":"失败"), GetRemoteIp.getIp(request));
        return result;
    }
}
