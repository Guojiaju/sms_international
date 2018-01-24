package com.sms.international.admin.model;

import java.io.Serializable;

/**
 * 基类、包含分页和ID
 * 
 * @author CQL 331737188@qq.com
 * @date : 2015年9月23日 下午3:58:51
 *
 */
public class BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected Integer id;

	protected Integer limit = 15; // 每页大小

	protected Integer page = 1;

	protected Integer pageSize = 15;

	protected Integer currentPageIndex ;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getPageSize() {
		return this.limit;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getCurrentPageIndex() {
		return (this.page - 1) * this.limit < 0 ? 0 : (this.page - 1) * this.limit;
	}

	public void setCurrentPageIndex(Integer currentPageIndex) {
		this.currentPageIndex = currentPageIndex;
	}

}
