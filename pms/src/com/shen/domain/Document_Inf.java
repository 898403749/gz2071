package com.shen.domain;

import java.util.Arrays;

public class Document_Inf {
	
	private String id;
	private String title;
	private String filename;
	private String filetype;
	private byte[] filebytes;
	private String remark;
	private String create_date;
	private String user_id;
	
	public Document_Inf(String id, String title, String filename, String filetype, byte[] filebytes, String remark,
			String create_date, String user_id) {
		super();
		this.id = id;
		this.title = title;
		this.filename = filename;
		this.filetype = filetype;
		this.filebytes = filebytes;
		this.remark = remark;
		this.create_date = create_date;
		this.user_id = user_id;
	}
	
	public Document_Inf(String id, String title, String filename, String filetype, String remark,
			String create_date, String user_id) {
		super();
		this.id = id;
		this.title = title;
		this.filename = filename;
		this.filetype = filetype;
		this.remark = remark;
		this.create_date = create_date;
		this.user_id = user_id;
	}
	
	public Document_Inf(String title, String filename, String filetype, String remark,
			String user_id) {
		super();
		this.title = title;
		this.filename = filename;
		this.filetype = filetype;
		this.remark = remark;
		this.user_id = user_id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getFiletype() {
		return filetype;
	}

	public void setFiletype(String filetype) {
		this.filetype = filetype;
	}

	public byte[] getFilebytes() {
		return filebytes;
	}

	public void setFilebytes(byte[] filebytes) {
		this.filebytes = filebytes;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCreate_date() {
		return create_date;
	}

	public void setCreate_date(String create_date) {
		this.create_date = create_date;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	@Override
	public String toString() {
		return "Document_Inf [id=" + id + ", title=" + title + ", filename=" + filename + ", filetype=" + filetype
				+ ", filebytes=" + Arrays.toString(filebytes) + ", remark=" + remark + ", create_date=" + create_date
				+ ", user_id=" + user_id + "]";
	}

}
