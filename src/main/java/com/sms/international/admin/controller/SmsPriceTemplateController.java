package com.sms.international.admin.controller;

import com.sms.international.admin.model.SmsPriceTemplate;
import com.sms.international.admin.model.SmsPriceTemplateDetail;
import com.sms.international.admin.service.SmsPriceTemplateService;
import com.sms.international.admin.utils.DateUtil;
import com.sms.international.admin.utils.GetRemoteIp;
import com.sms.international.admin.utils.GlobalParams;
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
 * Date 2018/1/18
 * Description
 */
@Controller
@Scope("prototype")
@RequestMapping("/priceTemplate")
public class SmsPriceTemplateController extends ParentController {

    @Autowired
    private SmsPriceTemplateService smsPriceTemplateService;

    @RequestMapping("/init")
    public String init(){
        return "priceTemplate/list";
    }


    /**
     * 查询所有的模板数据
     * @param template
     * @return
     */
    @RequestMapping("/findAll")
    @ResponseBody
    public String findAll(@RequestBody SmsPriceTemplate template){
       return smsPriceTemplateService.findByPage(template).toString();
    }

    /**
     * 初始化编辑页面
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/initEdit/{id}")
    public String initEdit(@PathVariable Integer id, ModelMap model){
        if(id >0){
            model.put("obj",smsPriceTemplateService.getOne(id));
        }
        return "priceTemplate/edit";
    }

    /**
     * 新增或者修改模板
     * @param template
     * @return
     */
    @RequestMapping("/doEdit")
    @ResponseBody
    public int doEdit(HttpServletRequest request,@RequestBody SmsPriceTemplate template){
        int result ;
        try {
            if(template.getId() != null){
                result = smsPriceTemplateService.updateTemplate(template);
            }else{
                template.setAddTime(DateUtil.getCurrentDateTime());
                result = smsPriceTemplateService.addTemplate(template);
            }
        } catch (Exception e) {
            e.printStackTrace();
            result = 0;
        }
        super.adminLogs("新增／修改 价目模板"+ (result > 0 ? "成功,":"失败,") + template.toString(), GetRemoteIp.getIp(request));
        return result;
    }

    /**
     * 删除指定模板
     * @param request
     * @param id
     * @return
     */
    @RequestMapping("/remove/{id}")
    @ResponseBody
    public int removeTemplate(HttpServletRequest request, @PathVariable Integer id){
        int result;
        try {
            result = smsPriceTemplateService.deleteByTempId(id);
        } catch (Exception e) {
            e.printStackTrace();
            result = 0;
        }
        super.adminLogs("删除价目模板Id:" + id +(result > 0 ? ",成功":",失败"), GetRemoteIp.getIp(request));
        return result;
    }

    /**
     * 保存价格信息
     * @param request
     * @param temp_id
     * @param infos
     * @return
     */
    @RequestMapping("/savePriceInfos/{temp_id}")
    @ResponseBody
    public int savePriceInfos(HttpServletRequest request,@PathVariable Integer temp_id ,@RequestBody List<SmsPriceTemplateDetail> infos){
        int result;
        try {
            result = smsPriceTemplateService.addPriceInfos(temp_id,infos);
        } catch (Exception e) {
            e.printStackTrace();
            result = 0;
        }
        super.adminLogs("修改模板价格"+(result > 0 ? ",成功":",失败")+"ID:"+temp_id, GetRemoteIp.getIp(request));
        return result;
    }


    /**
     * 初始化资源配置页面
     * @return
     */
    @RequestMapping("/initResources/{id}")
    public String initResources(@PathVariable Integer id,ModelMap modelMap){
        modelMap.put("details",smsPriceTemplateService.findDetailByTempId(id));
        modelMap.put("country", GlobalParams.COUNTRY_MAP.get(GlobalParams.COUNTRY_KEY));
        modelMap.put("temp_id",id);
        return "priceTemplate/resources";
    }
}
