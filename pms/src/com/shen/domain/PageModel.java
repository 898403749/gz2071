package com.shen.domain;

public class PageModel {
	
	private Integer pageSize;        //每页条数
	private Integer pageIndex;       //当前页
	private Integer totalPageSum;    //总页数
	private Integer totalRecordSum;  //总条数
	private Integer nextPage;        //下一页
	private Integer prePage;         //上一页
	
	public PageModel(Integer pageSize, Integer pageIndex, Integer totalPageSum, Integer totalRecordSum,
			Integer nextPage, Integer prePage) {
		super();
		this.pageSize = pageSize;
		this.pageIndex = pageIndex;
		this.totalPageSum = totalPageSum;
		this.totalRecordSum = totalRecordSum;
		this.nextPage = nextPage;
		this.prePage = prePage;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}

	public Integer getTotalPageSum() {
		return totalPageSum;
	}

	public void setTotalPageSum(Integer totalPageSum) {
		this.totalPageSum = totalPageSum;
	}

	public Integer getTotalRecordSum() {
		return totalRecordSum;
	}

	public void setTotalRecordSum(Integer totalRecordSum) {
		this.totalRecordSum = totalRecordSum;
	}

	public Integer getNextPage() {
		return nextPage;
	}

	public void setNextPage(Integer nextPage) {
		this.nextPage = nextPage;
	}

	public Integer getPrePage() {
		return prePage;
	}

	public void setPrePage(Integer prePage) {
		this.prePage = prePage;
	}

	@Override
	public String toString() {
		return "PageModel [pageSize=" + pageSize + ", pageIndex=" + pageIndex + ", totalPageSum=" + totalPageSum
				+ ", totalRecordSum=" + totalRecordSum + ", nextPage=" + nextPage + ", prePage=" + prePage + "]";
	}
}
