package com.shen.domain;

public class NoticePageModel extends PageModel{

	private String name;
	private String type_id;
	
	public NoticePageModel(Integer pageSize, Integer pageIndex, Integer totalPageSum, Integer totalRecordSum,
			Integer nextPage, Integer prePage, String name, String type_id) {
		super(pageSize, pageIndex, totalPageSum, totalRecordSum, nextPage, prePage);
		this.name = name;
		this.type_id = type_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType_id() {
		return type_id;
	}

	public void setType_id(String type_id) {
		this.type_id = type_id;
	}

	@Override
	public String toString() {
		return "NoticePageModel [name=" + name + ", type_id=" + type_id + "]";
	}
}
