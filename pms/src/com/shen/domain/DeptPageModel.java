package com.shen.domain;

public class DeptPageModel extends PageModel{

	private String name;

	public DeptPageModel(Integer pageSize, Integer pageIndex, Integer totalPageSum, Integer totalRecordSum,
			Integer nextPage, Integer prePage, String name) {
		super(pageSize, pageIndex, totalPageSum, totalRecordSum, nextPage, prePage);
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "DeptPageModel [name=" + name + "]";
	}
	
	
}
