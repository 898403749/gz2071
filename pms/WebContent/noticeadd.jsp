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
<form id="noticeadd" action="${ctxPath}/NoticeManageServlet/noticeAdd" method="post" style="padding-top:20px;margin-left:300px;margin-right:300px;">
<div class="form-group">
	<label for="exampleInputname">公告名称：</label>
	<input type="text" name="name"  class="form-control" style="width:200px;display: inline-block;" id="name" placeholder="name">
</div>
<div class="form-group" style="">
    <label for="exampleInputcontent">公告内容：</label>
    <textarea id="content" name="content" class="form-control" placeholder="content" rows="10" cols="20"></textarea>
</div>
<div class="form-group">
	<label for="exampleInputtype">公告类型：</label>
	<select id="type" name="type" class="form-control" style="display: inline-block;width: 200px;">
  		<option id="type_ts" value="" disabled="disabled" style="" selected>--请选择类型--</option>
	</select>
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
        url: "${ctxPath}/NoticeManageServlet/noticeQueryInit",
        success: function (data) {
        	for(let key in data.typenamemap){
        		$("#type").append("<option value='"+key+"'>"+data.typenamemap[key]+"</option>");
        	}
    	}
    });
}
init();
</script>
<script>
$("#Reset").click(function (){
	var name = document.getElementById("name");
	var content = document.getElementById("content");
	var type = document.getElementById("type");
	name.value="";
	content.value="";
	type.value="";
});
</script>
</html>