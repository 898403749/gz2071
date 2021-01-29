<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.shen.domain.User_Inf"%>
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
<style type="text/css">
#box li ul:nth-child(2) li{
	cursor:pointer;
	background-color: #4B5763;
}
#box li div:nth-child(1){
	cursor:pointer;
	font-size: 16px;
    background-color: black;
}
#box li{
    line-height: 50px;
}
.n{
    display: none;
}
</style>
</head>
<body>
<div style="height: 80px;background-color:black;color:white;font-size: 35px;padding-top: 10px;padding-left: 50px;padding-right: 10px;"><span>Personnel Management System</span><span style="font-size: 15px;margin-left: 10px;">人事管理系统</span><a style="cursor:pointer;float: right;font-size: 15px;padding-top: 19px;margin-left: 15px;" href="${ctxPath}/OutLoginServlet/outLogin">[退出登录]</a><span id="date" style="float: right;font-size: 15px;padding-top: 20px;"></span><span id="yh" style="float: right;font-size: 15px;padding-top: 20px;margin-right: 20px;"></span></div>
<div style="height: 500px;background-color:black;">
<ul id="box" class="list-unstyled" style="text-align: center;width:200px;height:280px;color:#FFFFFF;float: left;">
    <li>
        <div>用户管理</div>
        <ul class="list-unstyled">
            <li id="user_query">用户查询</li>
            <li id="user_add">添加用户</li>
        </ul>
    </li>
    <li>
        <div>部门管理</div>
        <ul class="list-unstyled">
            <li id="dept_query">部门查询</li>
            <li id="dept_add">添加部门</li>
        </ul>
    </li>
    <li>
        <div>职位管理</div>
        <ul class="list-unstyled">
            <li id="job_query">职位查询</li>
            <li id="job_add">添加职位</li>
        </ul>
    </li>
    <li>
        <div>员工管理</div>
        <ul class="list-unstyled">
            <li id="employee_query">员工查询</li>
            <li id="employee_add">添加员工</li>
        </ul>
    </li>
    <li>
        <div>公告管理</div>
        <ul class="list-unstyled">
            <li id="notice_query">公告查询</li>
            <li id="notice_add">添加公告</li>
        </ul>
    </li>
    <li>
        <div>下载中心</div>
        <ul class="list-unstyled">
            <li id="file_query">文档查询</li>
            <li id="upload_document">上传文档</li>
        </ul>
    </li>
</ul>
<div>
	<div id="show_title" style="right:10px;float: right;width: 1063px;background-color: white;height: 60px;font-size: 20px;padding-top: 16px;padding-left: 20px;"></div>
	<div class="embed-responsive" style="width: 1063px;height: 430px;float: right;background-color: white;">
		<iframe id ="show" width="1063px" height="430px" class="embed-responsive-item" src="${ctxPath}/cs.jsp" onload="load()"></iframe>
	</div>
</div>
</div>
</body>
<script>
setInterval(gettime, 1000);
function gettime(){
	var myDate = new Date();
	var M=(myDate.getMonth()+1)<10?"0"+(myDate.getMonth()+1):(myDate.getMonth()+1);
	var d=myDate.getDate()<10?"0"+myDate.getDate():myDate.getDate();
	var h=myDate.getHours()<10?"0"+myDate.getHours():myDate.getHours();
	var m=myDate.getMinutes()<10?"0"+myDate.getMinutes():myDate.getMinutes();
	document.getElementById('date').innerHTML="时间："+myDate.getFullYear()+"-"+M+"-"+d+" "+h+":"+m;
}
gettime();
<% User_Inf ui=(User_Inf)session.getAttribute("user");%>
document.getElementById('yh').innerHTML="用户："+"<%=ui.getLoginname()%>";
</script>
<script>
	function left_init(){
    	for(var i=0;i<$("#box li div").length;i++){
    		$($("#box li div")[i]).next().addClass("n");
    	}
    }
    left_init();
    $("#box li ul li").mouseenter(
            function () {
                $(this).css("background","#687480");
            }
    );
    $("#box li ul li").mouseleave(
            function () {
                $(this).css("background","#4B5763");
            }
    );
    var old_num=null;
    old_status="up";
	$("#box li div").click(function (){
    	var num=$("#box li div").index(this);
        if(num!=old_num){
        	roseChange(num,"down");
        	roseChange(old_num,"up");
        	old_status="down";
        }else{
        	if(old_status=="down"){
        		roseChange(num,"up");
        		old_status="up";
        	}else{
        		roseChange(num,"down");
        		old_status="down";
        	}
        }
    	old_num=num;
    });
	function roseChange(num,status){
		if(status=="down"){
			$($("#box li div")[num]).next().slideDown();
			$($("#box li div")[num]).css("background-color","#FFA500");
        	$($("#box li div")[num]).css("color","black");
		}else{
			$($("#box li div")[old_num]).next().slideUp();
        	$($("#box li div")[old_num]).css("background-color","black");
        	$($("#box li div")[old_num]).css("color","white");
		}
	}
	function load(){
		if(parent.document.getElementById("show").contentWindow.location.href=="${ctxPath}/login.jsp"){
			window.location.href="${ctxPath}/login.jsp";
		}
	}
	$("#user_query").click(function (){
		document.getElementById('show_title').innerHTML="用户管理&nbsp;&nbsp;>&nbsp;&nbsp;用户查询";
		$("#show_title").css("background-color","#E7EAED");
		$("#show").attr("src","${ctxPath}/userquery.jsp");
	});
	$("#user_add").click(function (){
		document.getElementById('show_title').innerHTML="用户管理&nbsp;&nbsp;>&nbsp;&nbsp;添加用户";
		$("#show_title").css("background-color","#E7EAED");
		$("#show").attr("src","${ctxPath}/useradd.jsp");
	});
	$("#dept_query").click(function (){
		document.getElementById('show_title').innerHTML="部门管理&nbsp;&nbsp;>&nbsp;&nbsp;部门查询";
		$("#show_title").css("background-color","#E7EAED");
		$("#show").attr("src","${ctxPath}/deptquery.jsp");
	});
	$("#dept_add").click(function (){
		document.getElementById('show_title').innerHTML="部门管理&nbsp;&nbsp;>&nbsp;&nbsp;添加部门";
		$("#show_title").css("background-color","#E7EAED");
		$("#show").attr("src","${ctxPath}/deptadd.jsp");
	});
	$("#job_query").click(function (){
		document.getElementById('show_title').innerHTML="职位管理&nbsp;&nbsp;>&nbsp;&nbsp;职位查询";
		$("#show_title").css("background-color","#E7EAED");
		$("#show").attr("src","${ctxPath}/jobquery.jsp");
	});
	$("#job_add").click(function (){
		document.getElementById('show_title').innerHTML="职位管理&nbsp;&nbsp;>&nbsp;&nbsp;添加职位";
		$("#show_title").css("background-color","#E7EAED");
		$("#show").attr("src","${ctxPath}/jobadd.jsp");
	});
	$("#employee_query").click(function (){
		document.getElementById('show_title').innerHTML="员工管理&nbsp;&nbsp;>&nbsp;&nbsp;员工查询";
		$("#show_title").css("background-color","#E7EAED");
		$("#show").attr("src","${ctxPath}/employeequery.jsp");
	});
	$("#employee_add").click(function (){
		document.getElementById('show_title').innerHTML="员工管理&nbsp;&nbsp;>&nbsp;&nbsp;添加员工";
		$("#show_title").css("background-color","#E7EAED");
		$("#show").attr("src","${ctxPath}/employeeadd.jsp");
	});
	$("#notice_query").click(function (){
		document.getElementById('show_title').innerHTML="公告管理&nbsp;&nbsp;>&nbsp;&nbsp;公告查询";
		$("#show_title").css("background-color","#E7EAED");
		$("#show").attr("src","${ctxPath}/noticequery.jsp");
	});
	$("#notice_add").click(function (){
		document.getElementById('show_title').innerHTML="公告管理&nbsp;&nbsp;>&nbsp;&nbsp;添加公告";
		$("#show_title").css("background-color","#E7EAED");
		$("#show").attr("src","${ctxPath}/noticeadd.jsp");
	});
	$("#file_query").click(function (){
		document.getElementById('show_title').innerHTML="下载中心&nbsp;&nbsp;>&nbsp;&nbsp;文档查询";
		$("#show_title").css("background-color","#E7EAED");
		$("#show").attr("src","${ctxPath}/filequery.jsp");
	});
	$("#upload_document").click(function (){
		document.getElementById('show_title').innerHTML="下载中心&nbsp;&nbsp;>&nbsp;&nbsp;上传文档";
		$("#show_title").css("background-color","#E7EAED");
		$("#show").attr("src","${ctxPath}/uploaddocument.jsp");
	});
</script>
</html>