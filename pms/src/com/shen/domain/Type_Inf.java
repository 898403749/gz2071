package com.shen.domain;

import java.sql.Timestamp;

public class Type_Inf {

	private String id;
	private String name;
	private Timestamp create_date;
	private String state;
	private String user_id;
	private Timestamp modify_date;
	private String user;
	
	public Type_Inf(String id, String name, Timestamp create_date, String state, String user_id,
			Timestamp modify_date) {
		super();
		this.id = id;
		this.name = name;
		this.create_date = create_date;
		this.state = state;
		this.user_id = user_id;
		this.modify_date = modify_date;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Timestamp getCreate_date() {
		return create_date;
	}

	public void setCreate_date(Timestamp create_date) {
		this.create_date = create_date;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public Timestamp getModify_date() {
		return modify_date;
	}

	public void setModify_date(Timestamp modify_date) {
		this.modify_date = modify_date;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Type_Inf [id=" + id + ", name=" + name + ", create_date=" + create_date + ", state=" + state
				+ ", user_id=" + user_id + ", modify_date=" + modify_date + ", user=" + user
				+ "]";
	}
}
