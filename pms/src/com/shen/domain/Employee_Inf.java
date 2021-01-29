package com.shen.domain;

import java.sql.Timestamp;

public class Employee_Inf {

	private String id;
	private String name;
	private String card_id;
	private String address;
	private String post_code;
	private String tel;
	private String phone;
	private String qq_num;
	private String email;
	private String sex;
	private String party;
	private Timestamp birthday;
	private String race;
	private String education;
	private String speciality;
	private String hobby;
	private String remark;
	private String create_date;
	private String state;
	private String dept_id;
	private String job_id;
	
	public Employee_Inf(String name, String card_id, String phone, String sex, String dept_id, String job_id) {
		super();
		this.name = name;
		this.card_id = card_id;
		this.phone = phone;
		this.sex = sex;
		this.dept_id = dept_id;
		this.job_id = job_id;
	}

	public Employee_Inf(String id, String name, String card_id, String address, String post_code, String tel,
			String phone, String qq_num, String email, String sex, String party, Timestamp birthday, String race,
			String education, String speciality, String hobby, String remark, String create_date, String state,
			String dept_id, String job_id) {
		super();
		this.id = id;
		this.name = name;
		this.card_id = card_id;
		this.address = address;
		this.post_code = post_code;
		this.tel = tel;
		this.phone = phone;
		this.qq_num = qq_num;
		this.email = email;
		this.sex = sex;
		this.party = party;
		this.birthday = birthday;
		this.race = race;
		this.education = education;
		this.speciality = speciality;
		this.hobby = hobby;
		this.remark = remark;
		this.create_date = create_date;
		this.state = state;
		this.dept_id = dept_id;
		this.job_id = job_id;
	}
	
	public Employee_Inf(String name, String card_id, String address, String post_code, String tel,
			String phone, String qq_num, String email, String sex, String party, Timestamp birthday, String race,
			String education, String speciality, String hobby, String remark, String dept_id, String job_id) {
		super();
		this.name = name;
		this.card_id = card_id;
		this.address = address;
		this.post_code = post_code;
		this.tel = tel;
		this.phone = phone;
		this.qq_num = qq_num;
		this.email = email;
		this.sex = sex;
		this.party = party;
		this.birthday = birthday;
		this.race = race;
		this.education = education;
		this.speciality = speciality;
		this.hobby = hobby;
		this.remark = remark;
		this.dept_id = dept_id;
		this.job_id = job_id;
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

	public String getCard_id() {
		return card_id;
	}

	public void setCard_id(String card_id) {
		this.card_id = card_id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPost_code() {
		return post_code;
	}

	public void setPost_code(String post_code) {
		this.post_code = post_code;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getQq_num() {
		return qq_num;
	}

	public void setQq_num(String qq_num) {
		this.qq_num = qq_num;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getParty() {
		return party;
	}

	public void setParty(String party) {
		this.party = party;
	}

	public Timestamp getBirthday() {
		return birthday;
	}

	public void setBirthday(Timestamp birthday) {
		this.birthday = birthday;
	}

	public String getRace() {
		return race;
	}

	public void setRace(String race) {
		this.race = race;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getSpeciality() {
		return speciality;
	}

	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}

	public String getHobby() {
		return hobby;
	}

	public void setHobby(String hobby) {
		this.hobby = hobby;
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

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
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
		return "Employee_Inf [id=" + id + ", name=" + name + ", card_id=" + card_id + ", address=" + address
				+ ", post_code=" + post_code + ", tel=" + tel + ", phone=" + phone + ", qq_num=" + qq_num + ", email="
				+ email + ", sex=" + sex + ", party=" + party + ", birthday=" + birthday + ", race=" + race
				+ ", education=" + education + ", speciality=" + speciality + ", hobby=" + hobby + ", remark=" + remark
				+ ", create_date=" + create_date + ", state=" + state + ", dept_id=" + dept_id + ", job_id=" + job_id
				+ "]";
	}
}
