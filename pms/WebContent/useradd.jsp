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
<form id="useradd" action="${ctxPath}/ManageServlet/userAdd" method="post" style="padding-top:20px;margin-left:300px;margin-right:300px;">
<div class="form-group" style="">
	<label for="exampleInputloginname">loginname：</label>
	<input type="text" name="loginname"  class="form-control" style="" id="loginname" placeholder="loginname">
</div>
<div class="form-group" style="">
    <label for="exampleInputpassword">password：</label>
    <input type="text" name="password"  class="form-control" style="" id="password" placeholder="password">
</div>
<div class="form-group" style="">
    <label for="exampleInputstatus">status：</label>
    <input type="text" name="status"  class="form-control" style="" id="status" placeholder="status">
</div>
<div class="form-group" style="">
    <label for="exampleInputusername">username：</label>
    <input type="text" name="username"  class="form-control" style="" id="username" placeholder="username">
</div>
<div class="form-group" style="">
    <button id="add" type="submit" class="btn btn-default btn-info">注册</button>
    <button id="Reset" type="button" class="btn btn-default btn-info">重置</button>
</div>
</form>
</body>
<script>
$("#Reset").click(function (){
	var loginname = document.getElementById("loginname");
	var password = document.getElementById("password");
	var status = document.getElementById("status");
	var username = document.getElementById("username");
	loginname.value="";
	password.value="";
	status.value="";
	username.value="";
});
</script>
</html>