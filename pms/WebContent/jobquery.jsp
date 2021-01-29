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
<form id="jobquery" action="${ctxPath}/JobManageServlet/jobQuery" method="post" style="padding-left: 20px;padding-right: 150px;padding-top:20px;padding-bottom:20px;background-color: #F5F7FA;">
<span>职位名称：</span>
<input type="text" name="name"  class="form-control" style="display: inline-block;width: 200px;" id="name" placeholder="name">
<button id="query" type="button" class="btn btn-default btn-info" style="margin-left: 40px;margin-bottom: 3px;">查询</button>
</form>
</body>
<script>
var pageIndex=null;
var jobquery_inf=null;
var pageSize=null;
var totalPageSum=null;
var totalRecordSum=null;
$("#query").click(function() {
	jobquery_inf=$("#jobquery").serialize();
    $.ajax({
        type: "POST",
        dataType: "json",
        url: "${ctxPath}/JobManageServlet/jobQuery",
        data: $("#jobquery").serialize(),
        success: function (data) {
        	pageIndex=data.pagemodel.pageIndex;
            pageSize=data.pagemodel.pageSize;
        	totalPageSum=data.pagemodel.totalPageSum;
        	totalRecordSum=data.pagemodel.totalRecordSum;
        	var jobquery_table_start="<table id='jobquery_table' class='table table-striped' style='text-align:center;'><tr><td>职位名称</td><td>详细信息</td><td>操作</td></tr>";
            var jobquery_table_end="</table><div id='jobquery_table_k' style='padding-left:20px;text-align:center;'><span>总页数："+totalPageSum+"</span><span style='margin-left:20px;margin-right:20px;'>总记录条数："+totalRecordSum+"</span><button id='firstpage' type='button' class='btn btn-default btn-info' style='margin-right:20px;' onclick='firstpage()'>首页</button><button id='prepage' type='button' class='btn btn-default btn-info' style='margin-right:20px;' onclick='prepage()'>上页</button><button id='nextpage' type='button' class='btn btn-default btn-info' style='margin-right:20px;' onclick='nextpage()'>下页</button><button id='lastpage' type='button' class='btn btn-default btn-info' style='margin-right:20px;' onclick='lastpage()'>尾页</button><span style='margin-right:5px;'>跳转到</span><input type='text' name='page_jump' class='form-control' id='page_jump' style='width:100px;display:inline-block;'><button id='do_page_jump' type='button' class='btn btn-default btn-info' style='margin-left:5px;' onclick='topage()'>确定</button></div>";
            var jobquery_table_body="";
            for(var i=0;i<data.jobs_inf.length;i++){
            	jobquery_table_body=jobquery_table_body+"<tr><td>"+data.jobs_inf[i].name+"</td><td>"+data.jobs_inf[i].remark+"</td><td><a href='${ctxPath}/JobManageServlet/jobModify?id="+data.jobs_inf[i].id+"'>修改</a>&nbsp|&nbsp<a href='javascript:void(0);' onclick='jobDelete("+data.jobs_inf[i].id+")'>删除</a></td></tr>";
            }
            $("#jobquery_table").remove();
            $("#jobquery_table_k").remove();
            $("#jobquery").after(jobquery_table_start+jobquery_table_body+jobquery_table_end);
	}
    });
});
function firstpage() {
	pageIndex=1;
	putTable(pageIndex);
}
function prepage(){
	if(pageIndex-1!=0){
		pageIndex=pageIndex-1;
	}
	putTable(pageIndex);
}
function nextpage(){
	if(pageIndex!=totalPageSum){
		pageIndex=pageIndex+1;
	}
	putTable(pageIndex);
}
function lastpage(){
	pageIndex=totalPageSum;
	putTable(pageIndex);
}
function topage(){
	var toindex=$("#page_jump").val();
	if(toindex!=""&&toindex!=null&&toindex<=totalPageSum&&toindex>=1){
		pageIndex=$("#page_jump").val();
	}
	putTable(pageIndex);
}
function putTable(pageIndex){
	$.ajax({
        type: "POST",
        dataType: "json",
        url: "${ctxPath}/JobManageServlet/jobQuery_toPage",
        data: jobquery_inf+"&pageIndex="+pageIndex+"&pageSize="+pageSize,
        success: function (data) {
        	console.log(data);
        	pageIndex=data.pagemodel.pageIndex;
            pageSize=data.pagemodel.pageSize;
        	totalPageSum=data.pagemodel.totalPageSum;
        	totalRecordSum=data.pagemodel.totalRecordSum;
        	var jobquery_table_start="<table id='jobquery_table' class='table table-striped' style='text-align:center;'><tr><td>职位名称</td><td>详细信息</td><td>操作</td></tr>";
            var jobquery_table_end="</table><div id='jobquery_table_k' style='padding-left:20px;text-align:center;'><span>总页数："+totalPageSum+"</span><span style='margin-left:20px;margin-right:20px;'>总记录条数："+totalRecordSum+"</span><button id='firstpage' type='button' class='btn btn-default btn-info' style='margin-right:20px;' onclick='firstpage()'>首页</button><button id='prepage' type='button' class='btn btn-default btn-info' style='margin-right:20px;' onclick='prepage()'>上页</button><button id='nextpage' type='button' class='btn btn-default btn-info' style='margin-right:20px;' onclick='nextpage()'>下页</button><button id='lastpage' type='button' class='btn btn-default btn-info' style='margin-right:20px;' onclick='lastpage()'>尾页</button><span style='margin-right:5px;'>跳转到</span><input type='text' name='page_jump' class='form-control' id='page_jump' style='width:100px;display:inline-block;'><button id='do_page_jump' type='button' class='btn btn-default btn-info' style='margin-left:5px;' onclick='topage()'>确定</button></div>";
            var jobquery_table_body="";
            for(var i=0;i<data.jobs_inf.length;i++){
            	jobquery_table_body=jobquery_table_body+"<tr><td>"+data.jobs_inf[i].name+"</td><td>"+data.jobs_inf[i].remark+"</td><td><a href='${ctxPath}/JobManageServlet/jobModify?id="+data.jobs_inf[i].id+"'>修改</a>&nbsp|&nbsp<a href='javascript:void(0);' onclick='jobDelete("+data.jobs_inf[i].id+")'>删除</a></td></tr>";
            }
            $("#jobquery_table").remove();
            $("#jobquery_table_k").remove();
            $("#jobquery").after(jobquery_table_start+jobquery_table_body+jobquery_table_end);
	}
    });
}
function jobDelete(id){
	$.ajax({
        type: "POST",
        dataType: "json",
        url: "${ctxPath}/JobManageServlet/jobDelete",
        data: {"id":id},
        success: function (data) {
        	if(data.jobdelete_result==true){
        		if(Math.ceil(totalRecordSum/pageSize)==pageIndex&&totalRecordSum%pageSize==1){
        			if(pageIndex!=1){
        				totalPageSum=totalPageSum-1;
        				pageIndex=pageIndex-1;
        			}
        		}
        		putTable(pageIndex);
        	}
    }
    });
}
</script>
</html>