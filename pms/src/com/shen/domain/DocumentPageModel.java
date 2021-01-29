package com.shen.domain;

public class DocumentPageModel extends PageModel{

	private String title;

	public DocumentPageModel(Integer pageSize, Integer pageIndex, Integer totalPageSum, Integer totalRecordSum,
			Integer nextPage, Integer prePage, String title) {
		super(pageSize, pageIndex, totalPageSum, totalRecordSum, nextPage, prePage);
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return "DocumentPageModel [title=" + title + "]";
	}	
	
}
