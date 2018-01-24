package com.sms.international.admin.utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Page<T> implements Serializable {

	private static final long serialVersionUID = -4133717165046854344L;

	private List<T> result = new ArrayList<T>();

	private String url;// 链接地址

	private String orderBy = "";
	private String sort = "asc";

	private int homePage = 1;

	private int fristPage = 1;

	private int currentPage = 1;

	@SuppressWarnings("unused")
	private int currentIndex = 0;

	@SuppressWarnings("unused")
	private int prePage = 1;

	@SuppressWarnings("unused")
	private int nextPage;

	@SuppressWarnings("unused")
	private int lastPage;

	private int pageSize = 15;

	private int rows;

	@SuppressWarnings("unused")
	private int allPages;

	public Page(int pageSize) {
		this.pageSize = pageSize;
	}

	public Page() {
	}

	public int getCurrentPage() {
		return this.currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getCurrentIndex() {
		return (currentPage - 1) * pageSize;
	}

	public int getCurrentPageIndex() {
		return (getCurrentPage() - 1) * getPageSize() < 0 ? 0 : (getCurrentPage() - 1) * getPageSize();
	}

	public int getPageSize() {
		return this.pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getHomePage() {
		return this.homePage;
	}

	public void setHomePage(int homePage) {
		this.homePage = homePage;
	}

	public int getFristPage() {
		return this.fristPage;
	}

	public void setFristPage(int fristPage) {
		this.fristPage = fristPage;
	}

	public int getLastPage() {
		return getAllPages();
	}

	public void setLastPage(int lastPage) {
		this.lastPage = lastPage;
	}

	public int getRows() {
		return this.rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
		if (rows <= this.pageSize)
			this.currentPage = 1;
	}

	public int getAllPages() {
		int mod = this.rows % this.pageSize;
		if (mod == 0) {
			return this.rows / this.pageSize;
		}
		return this.rows / this.pageSize + 1;
	}

	public void setAllPages(int allPages) {
		this.allPages = allPages;
	}

	public int getPrePage() {
		if (this.currentPage > 1)
			return this.currentPage - 1;
		return this.fristPage;
	}

	public void setPrePage(int prePage) {
		this.prePage = prePage;
	}

	public int getNextPage() {
		if (this.currentPage >= getLastPage())
			return getLastPage();
		return this.currentPage + 1;
	}

	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}

	public String getOrderBy() {
		return this.orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public String getSort() {
		return this.sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public List<T> getResult() {
		return this.result;
	}

	public void setResult(List<T> result) {
		this.result = result;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Page<T> init(List<T> result, int rows) {
		this.result = result;
		this.rows = rows;
		this.allPages = (rows + pageSize - 1) / pageSize;
		return this;
	}
}