<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.shen.domain.Document_Inf"%>
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
</head>
<body>
<form id="userquery" action="${ctxPath}/UploadFileServlet/doFileModify" method="post" enctype="multipart/form-data" style="background-color: #F5F7FA;border:1px solid #D1D4D9;">
<div class="form-group" style="padding:15px;margin-bottom:0px;">
	<label for="exampleInputfiletitle">文档标题：</label>
	<input type="text" name="filetitle"  class="form-control" style="display: inline-block;width: 400px;" id="filetitle" placeholder="filetitle" disabled>
</div>
<div class="form-group" style="border-top:1px dashed #D1D4D9;padding:15px;margin-bottom:0px;">
    <label for="exampleInputfiledescribe">文档描述：</label>
    <textarea id="filedescribe" name="filedescribe" class="form-control" placeholder="filedescribe" rows="10" cols="20"></textarea>
</div>
<div class="form-group" style="border-top:1px dashed #D1D4D9;padding:15px;margin-bottom:0px;">
    <label for="exampleInputfile">文档：</label>
    <p id="file" class="help-block"></p>
</div>
<div class="form-group" style="border-top:1px dashed #D1D4D9;padding:15px;margin-bottom:0px;">
    <button id="modify" type="submit" class="btn btn-default btn-info">修改</button>
    <button id="Reset" type="button" class="btn btn-default btn-info">重置</button>
</div>
</form>
</body>
<script>
<% Document_Inf di=(Document_Inf)request.getAttribute("modify_document_inf");
session.setAttribute("old_Document_Inf", di);%>
function init() {
	var filetitle = document.getElementById("filetitle");
	var filedescribe = document.getElementById("filedescribe");
	var file = document.getElementById("file");
	filetitle.value="<%=di.getTitle()%>";
	filedescribe.value="<%=di.getRemark()%>";
	file.innerHTML="<%=di.getFilename()%>";
}; 
init();
$("#Reset").click(function (){
	var filedescribe = document.getElementById("filedescribe");
	filedescribe.value="";
});
</script>
</html>