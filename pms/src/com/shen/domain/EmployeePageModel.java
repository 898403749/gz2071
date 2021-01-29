package com.shen.domain;

public class EmployeePageModel extends PageModel{

	private String name;
	private String card_id;
	private String phone;
	private String sex;
	private String dept_id;
	private String job_id;
	
	public EmployeePageModel(Integer pageSize, Integer pageIndex, Integer totalPageSum, Integer totalRecordSum,
			Integer nextPage, Integer prePage, String name, String card_id, String phone, String sex, String dept_id,
			String job_id) {
		super(pageSize, pageIndex, totalPageSum, totalRecordSum, nextPage, prePage);
		this.name = name;
		this.card_id = card_id;
		this.phone = phone;
		this.sex = sex;
		this.dept_id = dept_id;
		this.job_id = job_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCard_id() {
		return card_id;
	}

	public void setCard_id(String card_id) {
		this.card_id = card_id;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getDept_id() {
		return dept_id;
	}

	public void setDept_id(String dept_id) {
		this.dept_id = dept_id;
	}

	public String getJob_id() {
		return job_id;
	}

	public void setJob_id(String job_id) {
		this.job_id = job_id;
	}

	@Override
	public String toString() {
		return "EmployeePageModel [name=" + name + ", card_id=" + card_id + ", phone=" + phone + ", sex=" + sex
				+ ", dept_id=" + dept_id + ", job_id=" + job_id + "]";
	}
}
