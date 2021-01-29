package com.shen.domain;

public class UserPageModel extends PageModel{

	private String loginname;
	private String username;
	private String status;
	
	public UserPageModel(Integer pageSize, Integer pageIndex, Integer totalPageSum, Integer totalRecordSum,
			Integer nextPage, Integer prePage, String loginname, String username, String status) {
		super(pageSize, pageIndex, totalPageSum, totalRecordSum, nextPage, prePage);
		this.loginname = loginname;
		this.username = username;
		this.status = status;
	}

	public String getLoginname() {
		return loginname;
	}

	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "UserPageModel [loginname=" + loginname + ", username=" + username + ", status=" + status + "]";
	}
	
}
