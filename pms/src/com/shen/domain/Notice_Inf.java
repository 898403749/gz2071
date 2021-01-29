package com.shen.domain;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class Notice_Inf {

	private String id;
	private String name;
	private Timestamp create_date;
	private String type_id;
	private String content;
	private String user_id;
	private Timestamp modify_date;
	private String type;
	private String user;
	
	public Notice_Inf(String id, String name, Timestamp create_date, String type_id, String content, String user_id,
			Timestamp modify_date) {
		super();
		this.id = id;
		this.name = name;
		this.create_date = create_date;
		this.type_id = type_id;
		this.content = content;
		this.user_id = user_id;
		this.modify_date = modify_date;
	}

	public Notice_Inf(String name, String type_id, String content, String user_id) {
		super();
		this.name = name;
		this.type_id = type_id;
		this.content = content;
		this.user_id = user_id;
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

	public String getType_id() {
		return type_id;
	}

	public void setType_id(String type_id) {
		this.type_id = type_id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Notice_Inf [id=" + id + ", name=" + name + ", create_date=" + create_date + ", type_id=" + type_id
				+ ", content=" + content + ", user_id=" + user_id + ", modify_date=" + modify_date + ", type=" + type
				+ ", user=" + user + "]";
	}
}
