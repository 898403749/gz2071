<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.shen.domain.Job_Inf"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <c:set var="ctxPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="${ctxPath}/bootstrap3.3.7/css/bootstrap.min.css" rel="stylesheet">
<script src="${ctxPath}/bootstrap3.3.7/js/jquery-3.1.1.min.js"></script>
<script src="${ctxPath}/bootstrap3.3.7/js/bootstrap.min.js"></script>
<script src="${ctxPath}/jQ/jquery-1.11.1.min.js"></script>
</head>
<body>
<form id="jobmodify" action="${ctxPath}/JobManageServlet/doJobModify" method="post" style="margin-left:300px;margin-right:300px;">
<div class="form-group" style="">
	<label for="exampleInputname">职位名称：</label>
	<input type="text" name="name"  class="form-control" style="" id="name" placeholder="name" disabled>
</div>
<div class="form-group" style="">
    <label for="exampleInputremark">详细信息：</label>
    <input type="text" name="remark"  class="form-control" style="" id="remark" placeholder="remark">
</div>
<div class="form-group" style="">
    <label for="exampleInputstate">state：</label>
    <input type="text" name="state"  class="form-control" style="" id="state" placeholder="state">
</div>
<div class="form-group" style="">
    <button id="modify" type="submit" class="btn btn-default btn-info">修改</button>
    <button id="Reset" type="button" class="btn btn-default btn-info">重置</button>
</div>
</form>
</body>
<script>
<% Job_Inf ji=(Job_Inf)request.getAttribute("modify_job_inf");
session.setAttribute("old_job_inf", ji);%>
function init() {
	var name = document.getElementById("name");
	var remark = document.getElementById("remark");
	var state = document.getElementById("state");
	name.value="<%=ji.getName()%>";
	remark.value="<%=ji.getRemark()%>";
	state.value="<%=ji.getState()%>";
}; 
init();
$("#Reset").click(function (){
	var remark = document.getElementById("remark");
	var state = document.getElementById("state");
	remark.value="";
	state.value="";
});
</script>
</html>