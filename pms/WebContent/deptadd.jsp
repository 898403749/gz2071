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
<form id="deptadd" action="${ctxPath}/DeptManageServlet/deptAdd" method="post" style="padding-top:20px;margin-left:300px;margin-right:300px;">
<div class="form-group" style="">
	<label for="exampleInputname">部门名称：</label>
	<input type="text" name="name"  class="form-control" style="" id="name" placeholder="name">
</div>
<div class="form-group" style="">
    <label for="exampleInputremark">详细信息：</label>
    <input type="text" name="remark"  class="form-control" style="" id="remark" placeholder="remark">
</div>
<div class="form-group" style="">
    <button id="add" type="submit" class="btn btn-default btn-info">添加</button>
    <button id="Reset" type="button" class="btn btn-default btn-info">重置</button>
</div>
</form>
</body>
<script>
$("#Reset").click(function (){
	var name = document.getElementById("name");
	var remark = document.getElementById("remark");
	name.value="";
	remark.value="";
});
</script>
</html>