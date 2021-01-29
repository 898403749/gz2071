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
<form id="uploaddocument" action="${ctxPath}/UploadFileServlet/uploadFile" method="post" enctype="multipart/form-data" style="background-color: #F5F7FA;border:1px solid #D1D4D9;">
<div class="form-group" style="padding:15px;margin-bottom:0px;">
	<label for="exampleInputfiletitle">文档标题：</label>
	<input type="text" name="filetitle"  class="form-control" style="display: inline-block;width: 400px;" id="filetitle" placeholder="filetitle">
</div>
<div class="form-group" style="border-top:1px dashed #D1D4D9;padding:15px;margin-bottom:0px;">
    <label for="exampleInputfiledescribe">文档描述：</label>
    <textarea id="filedescribe" name="filedescribe" class="form-control" placeholder="filedescribe" rows="10" cols="20"></textarea>
</div>
<div class="form-group" style="border-top:1px dashed #D1D4D9;padding:15px;margin-bottom:0px;">
    <label for="exampleInputfile">文档：</label>
    <input type="file" name="filename" id="filename" placeholder="filename">
    <p class="help-block">请选择需要上传的文件</p>
</div>
<div class="form-group" style="border-top:1px dashed #D1D4D9;padding:15px;margin-bottom:0px;">
    <button id="upload" type="submit" class="btn btn-default btn-info">上传</button>
    <button id="Reset" type="button" class="btn btn-default btn-info">重置</button>
</div>
</form>
</body>
<script>
$("#Reset").click(function (){
	var filetitle = document.getElementById("filetitle");
	var filedescribe = document.getElementById("filedescribe");
	var filename = document.getElementById("filename");
	filetitle.value="";
	filedescribe.value="";
	filename.value="";
});
</script>
</html>