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
</head>
<body>
<form id="noticemodify" action="${ctxPath}/NoticeManageServlet/doNoticeModify" method="post" style="padding-top:20px;margin-left:300px;margin-right:300px;">
<div class="form-group">
	<label for="exampleInputname">公告名称：</label>
	<input type="text" name="name"  class="form-control" style="width:200px;display: inline-block;" id="name" placeholder="name" disabled>
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
    <button id="modify" type="submit" class="btn btn-default btn-info">修改</button>
    <button id="Reset" type="button" class="btn btn-default btn-info">重置</button>
</div>
</form>
</body>
<script>
function pageinit(){
	$.ajax({
        type: "POST",
        dataType: "json",
        url: "${ctxPath}/NoticeManageServlet/noticeQueryInit",
        success: function (data) {
        	for(let key in data.typenamemap){
        		$("#type").append("<option value='"+key+"'>"+data.typenamemap[key]+"</option>");
        	}
        	init();
    	}
    });
}
pageinit();
<% Notice_Inf ni=(Notice_Inf)request.getAttribute("modify_notice_inf");
session.setAttribute("old_notice_inf", ni);%>
function init() {
	var name = document.getElementById("name");
	var content = document.getElementById("content");
	var type="<%=ni.getType_id()%>";
	console.log("type:"+type);
	$("#type  option[value='"+type+"'] ").attr("selected",true);
	name.value="<%=ni.getName()%>";
	content.value="<%=ni.getContent()%>";
}
$("#Reset").click(function (){
	var content = document.getElementById("content");
	var type = document.getElementById("type");
	content.value="";
	type.value="";
});
</script>
</html>