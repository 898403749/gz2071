package com.shen.domain;

public class Dept_Inf {

	private String id;
	private String name;
	private String remark;
	private String state;
	
	public Dept_Inf(String id, String name, String remark, String state) {
		super();
		this.id = id;
		this.name = name;
		this.remark = remark;
		this.state = state;
	}
	
	public Dept_Inf(String name, String remark) {
		super();
		this.name = name;
		this.remark = remark;
		this.state = state;
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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return "Dept_Inf [id=" + id + ", name=" + name + ", remark=" + remark + ", state=" + state + "]";
	}
}
