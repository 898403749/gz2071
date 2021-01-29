<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.shen.domain.Notice_Inf"%>
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
<style type="text/css">
p{
text-indent:2em;
font-size: 20px;
line-height: 45px;
}
</style>
</head>
<body>
<div id="name" style="text-align: center;font-size: 40px;font-weight: 500;margin-top: 20px;"></div>
<div id="infor" style="text-align: center;font-size: 15px;margin-top: 10px;"></div>
<div id="content" style="padding-left: 170px;padding-right: 170px;margin-top: 15px;"></div>
</body>
<script>
<% Notice_Inf ni=(Notice_Inf)request.getAttribute("watch_notice_inf");%>
function init() {
	var name = document.getElementById("name");
	var content = document.getElementById("content");
	var infor = document.getElementById("infor");
	console.log("<%=ni.toString()%>");
	name.innerHTML="<%=ni.getName()%>";
	infor.innerHTML="发布人：<%=ni.getUser()%>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;类型：<%=ni.getType()%>";
	content.innerHTML="<%=ni.getContent()%>";
}
init();
</script>
</html>