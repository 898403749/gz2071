package com.shen.domain;

public class User_Inf {
	private String id;
	private String loginname;
	private String password;
	private String status;
	private String createdate;
	private String username;
	
	public User_Inf(String id, String loginname, String password, String status, String createdate, String username) {
		super();
		this.id = id;
		this.loginname = loginname;
		this.password = password;
		this.status = status;
		this.createdate = createdate;
		this.username = username;
	}
	
	public User_Inf(String loginname, String password, String status, String username) {
		super();
		this.loginname = loginname;
		this.password = password;
		this.status = status;
		this.username = username;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLoginname() {
		return loginname;
	}

	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCreatedate() {
		return createdate;
	}

	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return "User_Inf [id=" + id + ", loginname=" + loginname + ", password=" + password + ", status=" + status
				+ ", createdate=" + createdate + ", username=" + username + "]";
	}

}
