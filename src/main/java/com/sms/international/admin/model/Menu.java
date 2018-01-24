package com.sms.international.admin.model;


/**
 * 菜单
 * 
 * @author CQL  331737188@qq.com
 * @date : 2015年10月12日 上午9:07:25
 *
 */
public class Menu extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1247076204267159459L;
	
	private String name;//菜单名称
	private String url;//URL
	private Integer pid;//父ID
	private Integer sort;//排序
	/**  
	 * styleName  
	 * @return styleName styleName  
	 */
	public String getStyleName() {
		return styleName;
	}
	/**  
	 * styleName  
	 * @return styleName styleName  
	 */
	public void setStyleName(String styleName) {
		this.styleName = styleName;
	}
	private Integer grade;//层级
	private String checked;//是否选中  数据库无此字段
	private String styleName;//样式名称

	private String pname;//父类菜单名称
	
	private String createDate;
	
	public Menu(){
		
	}
	public Menu(Integer id){
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public String getChecked() {
		return checked;
	}
	public void setChecked(String checked) {
		this.checked = checked;
	}
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	public Integer getGrade() {
		return grade;
	}
	public void setGrade(Integer grade) {
		this.grade = grade;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public String getCreateDate() {
		if(createDate != null && createDate.length() > 19){
			return createDate.substring(0, 19);
		}
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	
}
