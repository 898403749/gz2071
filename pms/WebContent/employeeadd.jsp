<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <c:set var="ctxPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="bootstrap3.3.7/css/bootstrap.min.css" rel="stylesheet">
<script src="bootstrap3.3.7/js/jquery-3.1.1.min.js"></script>
<script src="bootstrap3.3.7/js/bootstrap.min.js"></script>
<script src="jQ/jquery-1.11.1.min.js"></script>
</head>
<body>
<form id="employeeadd" action="${ctxPath}/EmployeeManageServlet/employeeAdd" method="post" style="background-color: #F5F7FA;border:1px solid #D1D4D9;">
<div style="border-bottom:1px dashed #D1D4D9;padding-left:50px;padding-top:30px;">
<div class="form-group" style="display: inline-block;">
	<label for="exampleInputname">姓名：</label>
	<input type="text" name="name"  class="form-control" style="width:200px;display: inline-block;" id="name" placeholder="name">
</div>
<div class="form-group" style="display: inline-block;">
    <label for="exampleInputcard_id">身份证号码：</label>
    <input type="text" name="card_id"  class="form-control" style="width:359px;display: inline-block;" id="card_id" placeholder="card_id">
</div>
<br/>
<div class="form-group" style="display: inline-block;">
	<label for="exampleInputsex">性别：</label>
	<select id="sex" name="sex" class="form-control" style="display: inline-block;width: 200px;">
  		<option id="sex_ts" value="" disabled="disabled" style="" selected>--请选择性别--</option>
  		<option value="1">男</option>
  		<option value="0">女</option>
	</select>
</div>
<div class="form-group" style="display: inline-block;">
	<label for="exampleInputjob">职位：</label>
	<select id="job" name="job" class="form-control" style="display: inline-block;width: 200px;">
  		<option id="job_ts" value="" disabled="disabled" style="" selected>--请选择职位--</option>
	</select>
</div>
<br/>
<div class="form-group" style="display: inline-block;">
    <label for="exampleInputeducation">学历：</label>
    <input type="text" name="education"  class="form-control" style="width:300px;display: inline-block;" id="education" placeholder="education">
</div>
<div class="form-group" style="display: inline-block;">
    <label for="exampleInputemail">邮箱：</label>
    <input type="text" name="email"  class="form-control" style="width:300px;display: inline-block;" id="email" placeholder="email">
</div>
<br/>
<div class="form-group" style="display: inline-block;">
    <label for="exampleInputphone">手机：</label>
    <input type="text" name="phone"  class="form-control" style="width:300px;display: inline-block;" id="phone" placeholder="phone">
</div>
<div class="form-group" style="display: inline-block;">
    <label for="exampleInputtel">电话：</label>
    <input type="text" name="tel"  class="form-control" style="width:300px;display: inline-block;" id="tel" placeholder="tel">
</div>
</div>
<div style="border-bottom:1px dashed #D1D4D9;padding-left:50px;padding-top:15px;">
<div class="form-group" style="display: inline-block;">
    <label for="exampleInputparty">政治面貌：</label>
    <input type="text" name="party"  class="form-control" style="width:200px;display: inline-block;" id="party" placeholder="party">
</div>
<div class="form-group" style="display: inline-block;">
    <label for="exampleInputqq_num">QQ号码：</label>
    <input type="text" name="qq_num"  class="form-control" style="width:350px;display: inline-block;" id="qq_num" placeholder="qq_num">
</div>
</div>
<div style="border-bottom:1px dashed #D1D4D9;padding-left:50px;padding-top:15px;">
<div class="form-group" style="display: inline-block;">
    <label for="exampleInputaddress">联系地址：</label>
    <input type="text" name="address"  class="form-control" style="width:350px;display: inline-block;" id="address" placeholder="address">
</div>
<div class="form-group" style="display: inline-block;">
    <label for="exampleInputpost_code">邮政编码：</label>
    <input type="text" name="post_code"  class="form-control" style="width:193px;display: inline-block;" id="post_code" placeholder="post_code">
</div>
</div>
<div style="border-bottom:1px dashed #D1D4D9;padding-left:50px;padding-top:15px;">
<div class="form-group" style="display: inline-block;">
    <label for="exampleInputbirthday">出生日期：</label>
    <input type="text" name="birthday"  class="form-control" style="width:200px;display: inline-block;" id="birthday" placeholder="birthday">
</div>
<div class="form-group" style="display: inline-block;">
    <label for="exampleInputrace">民族：</label>
    <input type="text" name="race"  class="form-control" style="width:200px;display: inline-block;" id="race" placeholder="race">
</div>
</div>
<div style="border-bottom:1px dashed #D1D4D9;padding-left:50px;padding-top:15px;">
<div class="form-group" style="display: inline-block;">
    <label for="exampleInputspeciality">所学专业：</label>
    <input type="text" name="speciality"  class="form-control" style="width:272px;display: inline-block;" id="speciality" placeholder="speciality">
</div>
<div class="form-group" style="display: inline-block;">
    <label for="exampleInputhobby">爱好：</label>
    <input type="text" name="hobby"  class="form-control" style="width:300px;display: inline-block;" id="hobby" placeholder="hobby">
</div>
</div>
<div style="border-bottom:1px dashed #D1D4D9;padding-left:50px;padding-top:15px;">
<div class="form-group" style="display: inline-block;">
    <label for="exampleInputremark">备注：</label>
    <input type="text" name="remark"  class="form-control" style="width:372px;display: inline-block;" id="remark" placeholder="remark">
</div>
<div class="form-group" style="display: inline-block;">
<label for="exampleInputdept">所属部门：</label>
<select id="dept" name="dept" class="form-control" style="display: inline-block;width: 200px;">
  <option id="dept_ts" value="" disabled="disabled" style="" selected>--部门选择--</option>
</select>
</div>
</div>
<div class="form-group" style="padding-left:50px;padding-top:15px;">
    <button id="add" type="submit" class="btn btn-default btn-info">添加</button>
    <button id="Reset" type="button" class="btn btn-default btn-info">重置</button>
</div>
</form>
</body>
<script>
function init(){
	$.ajax({
        type: "POST",
        dataType: "json",
        url: "${ctxPath}/EmployeeManageServlet/employeeQueryInit",
        success: function (data) {
        	var deptnamemap=data.deptnamemap;
        	for(let key in data.jobnamemap){
        		$("#job").append("<option value='"+key+"'>"+data.jobnamemap[key]+"</option>");
        	}
        	for(let key in data.deptnamemap){
      		    $("#dept").append("<option value='"+key+"'>"+data.deptnamemap[key]+"</option>");
      		}
    	}
    });
}
init();
</script>
<script>
$("#Reset").click(function (){
	var name = document.getElementById("name");
	var card_id = document.getElementById("card_id");
	var sex = document.getElementById("sex");
	var job = document.getElementById("job");
	var education = document.getElementById("education");
	var email = document.getElementById("email");
	var phone = document.getElementById("phone");
	var tel = document.getElementById("tel");
	var party = document.getElementById("party");
	var qq_num = document.getElementById("qq_num");
	var address = document.getElementById("address");
	var post_code = document.getElementById("post_code");
	var birthday = document.getElementById("birthday");
	var race = document.getElementById("race");
	var speciality = document.getElementById("speciality");
	var hobby = document.getElementById("hobby");
	var remark = document.getElementById("remark");
	var dept = document.getElementById("dept");
	name.value="";
	card_id.value="";
	sex.value="";
	job.value="";
	education.value="";
	email.value="";
	phone.value="";
	tel.value="";
	party.value="";
	qq_num.value="";
	address.value="";
	post_code.value="";
	birthday.value="";
	race.value="";
	speciality.value="";
	hobby.value="";
	remark.value="";
	dept.value="";
});
</script>
</html>