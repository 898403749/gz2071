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
<form id="deptquery" action="${ctxPath}/DeptManageServlet/deptQuery" method="post" style="padding-left: 20px;padding-right: 150px;padding-top:20px;padding-bottom:20px;background-color: #F5F7FA;">
<span>部门名称：</span>
<input type="text" name="name"  class="form-control" style="display: inline-block;width: 200px;" id="name" placeholder="name">
<button id="query" type="button" class="btn btn-default btn-info" style="margin-left: 40px;margin-bottom: 3px;">查询</button>
</form>
</body>
<script>
var pageIndex=null;
var deptquery_inf=null;
var pageSize=null;
var totalPageSum=null;
var totalRecordSum=null;
$("#query").click(function() {
	deptquery_inf=$("#deptquery").serialize();
    $.ajax({
        type: "POST",
        dataType: "json",
        url: "${ctxPath}/DeptManageServlet/deptQuery",
        data: $("#deptquery").serialize(),
        success: function (data) {
        	pageIndex=data.pagemodel.pageIndex;
            pageSize=data.pagemodel.pageSize;
        	totalPageSum=data.pagemodel.totalPageSum;
        	totalRecordSum=data.pagemodel.totalRecordSum;
        	var deptquery_table_start="<table id='deptquery_table' class='table table-striped' style='text-align:center;'><tr><td>部门名称</td><td>详细信息</td><td>操作</td></tr>";
            var deptquery_table_end="</table><div id='deptquery_table_k' style='padding-left:20px;text-align:center;'><span>总页数："+totalPageSum+"</span><span style='margin-left:20px;margin-right:20px;'>总记录条数："+totalRecordSum+"</span><button id='firstpage' type='button' class='btn btn-default btn-info' style='margin-right:20px;' onclick='firstpage()'>首页</button><button id='prepage' type='button' class='btn btn-default btn-info' style='margin-right:20px;' onclick='prepage()'>上页</button><button id='nextpage' type='button' class='btn btn-default btn-info' style='margin-right:20px;' onclick='nextpage()'>下页</button><button id='lastpage' type='button' class='btn btn-default btn-info' style='margin-right:20px;' onclick='lastpage()'>尾页</button><span style='margin-right:5px;'>跳转到</span><input type='text' name='page_jump' class='form-control' id='page_jump' style='width:100px;display:inline-block;'><button id='do_page_jump' type='button' class='btn btn-default btn-info' style='margin-left:5px;' onclick='topage()'>确定</button></div>";
            var deptquery_table_body="";
            for(var i=0;i<data.depts_inf.length;i++){
            	deptquery_table_body=deptquery_table_body+"<tr><td>"+data.depts_inf[i].name+"</td><td>"+data.depts_inf[i].remark+"</td><td><a href='${ctxPath}/DeptManageServlet/deptModify?id="+data.depts_inf[i].id+"'>修改</a>&nbsp|&nbsp<a href='javascript:void(0);' onclick='deptDelete("+data.depts_inf[i].id+")'>删除</a></td></tr>";
            }
            $("#deptquery_table").remove();
            $("#deptquery_table_k").remove();
            $("#deptquery").after(deptquery_table_start+deptquery_table_body+deptquery_table_end);
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
        url: "${ctxPath}/DeptManageServlet/deptQuery_toPage",
        data: deptquery_inf+"&pageIndex="+pageIndex+"&pageSize="+pageSize,
        success: function (data) {
        	console.log(data);
        	pageIndex=data.pagemodel.pageIndex;
            pageSize=data.pagemodel.pageSize;
        	totalPageSum=data.pagemodel.totalPageSum;
        	totalRecordSum=data.pagemodel.totalRecordSum;
        	var deptquery_table_start="<table id='deptquery_table' class='table table-striped' style='text-align:center;'><tr><td>部门名称</td><td>详细信息</td><td>操作</td></tr>";
            var deptquery_table_end="</table><div id='deptquery_table_k' style='padding-left:20px;text-align:center;'><span>总页数："+totalPageSum+"</span><span style='margin-left:20px;margin-right:20px;'>总记录条数："+totalRecordSum+"</span><button id='firstpage' type='button' class='btn btn-default btn-info' style='margin-right:20px;' onclick='firstpage()'>首页</button><button id='prepage' type='button' class='btn btn-default btn-info' style='margin-right:20px;' onclick='prepage()'>上页</button><button id='nextpage' type='button' class='btn btn-default btn-info' style='margin-right:20px;' onclick='nextpage()'>下页</button><button id='lastpage' type='button' class='btn btn-default btn-info' style='margin-right:20px;' onclick='lastpage()'>尾页</button><span style='margin-right:5px;'>跳转到</span><input type='text' name='page_jump' class='form-control' id='page_jump' style='width:100px;display:inline-block;'><button id='do_page_jump' type='button' class='btn btn-default btn-info' style='margin-left:5px;' onclick='topage()'>确定</button></div>";
            var deptquery_table_body="";
            for(var i=0;i<data.depts_inf.length;i++){
            	deptquery_table_body=deptquery_table_body+"<tr><td>"+data.depts_inf[i].name+"</td><td>"+data.depts_inf[i].remark+"</td><td><a href='${ctxPath}/DeptManageServlet/deptModify?id="+data.depts_inf[i].id+"'>修改</a>&nbsp|&nbsp<a href='javascript:void(0);' onclick='deptDelete("+data.depts_inf[i].id+")'>删除</a></td></tr>";
            }
            $("#deptquery_table").remove();
            $("#deptquery_table_k").remove();
            $("#deptquery").after(deptquery_table_start+deptquery_table_body+deptquery_table_end);
	}
    });
}
function deptDelete(id){
	$.ajax({
        type: "POST",
        dataType: "json",
        url: "${ctxPath}/DeptManageServlet/deptDelete",
        data: {"id":id},
        success: function (data) {
        	if(data.deptdelete_result==true){
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