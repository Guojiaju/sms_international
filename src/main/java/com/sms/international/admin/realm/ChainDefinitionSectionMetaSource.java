package com.sms.international.admin.realm;

import com.sms.international.admin.model.Menu;
import com.sms.international.admin.service.MenuService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.config.Ini;
import org.apache.shiro.config.Ini.Section;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.FactoryBean;

import java.text.MessageFormat;
import java.util.List;

/**
 * 菜单权限工具
 * 
 * @author CQL  331737188@qq.com
 * @date : 2015年10月13日 下午2:34:00
 *
 */
public class ChainDefinitionSectionMetaSource implements FactoryBean<Section>{

	private MenuService menuService;

    private String filterChainDefinitions;


    public static String findPermission(String url){
        String permission = "";
        if(StringUtils.isNotEmpty(url)){
            permission = url.substring(url.indexOf("/") + 1,url.lastIndexOf("/")) + ":" + url.substring(url.lastIndexOf("/") + 1,url.length());
        }
        return permission;
    }

    /**
     * 默认premission字符串
     */
    public static final String PREMISSION_STRING="perms[\"{0}\"]";

    public Section getObject() throws BeansException {
        List<Menu> menulist = menuService.findAll(new Menu());

        Ini ini = new Ini();
        //加载默认的url
        ini.load(filterChainDefinitions);
        Section section = ini.getSection(Ini.DEFAULT_SECTION_NAME);
        if(menulist != null && menulist.size() > 0){
	        for (int i = 0;i<menulist.size();i++) {
	            Menu menu = menulist.get(i);
	            if(menu.getGrade() > 1){
	            	String url = menu.getUrl();
	                String permission = findPermission(url);
	                //如果不为空值添加到section中
	                if(StringUtils.isNotEmpty(url) && StringUtils.isNotEmpty(permission)) {
	                	section.put(menu.getGrade().intValue() == 3?url+"/**":url,  MessageFormat.format(PREMISSION_STRING,permission));
	                }
	            }
	        }
        }
        
        return section;
    }
    /**
     * 通过filterChainDefinitions对默认的url过滤定义
     * 
     * @param filterChainDefinitions 默认的url过滤定义
     */
    public void setFilterChainDefinitions(String filterChainDefinitions) {
        this.filterChainDefinitions = filterChainDefinitions;
    }
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public Class getObjectType() {
        return this.getClass();
    }
    
    public boolean isSingleton() {
        return false;
    }

	public void setMenuService(MenuService menuService) {
		this.menuService = menuService;
	}
}
