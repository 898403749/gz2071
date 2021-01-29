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
<form id="employeequery" action="${ctxPath}/EmployeeManageServlet/employeeQuery" method="post" style="padding-left: 20px;padding-right: 150px;padding-top:20px;padding-bottom:20px;background-color: #F5F7FA;">
<span>职位名称：</span>
<select id="job" name="job" class="form-control" style="display: inline-block;width: 200px;">
  <option id="job_ts" value="" style="" selected>--请选择职位--</option>
</select>
<span>姓名：</span>
<input type="text" name="name"  class="form-control" style="display: inline-block;width: 200px;" id="name" placeholder="name">
<span>身份证号码：</span>
<input type="text" name="card_id"  class="form-control" style="display: inline-block;width: 200px;" id="card_id" placeholder="card_id"><br/><br/>
<span>性别：</span>
<select id="sex" name="sex" class="form-control" style="display: inline-block;width: 200px;">
  <option id="sex_ts" value="" style="" selected>--请选择性别--</option>
  <option value="1">男</option>
  <option value="0">女</option>
</select>
<span>手机：</span>
<input type="text" name="phone"  class="form-control" style="display: inline-block;width: 200px;" id="phone" placeholder="phone">
<span>所属部门：</span>
<select id="dept" name="dept" class="form-control" style="display: inline-block;width: 200px;">
  <option id="dept_ts" value="" style="" selected>--部门选择--</option>
</select>
<button id="query" type="button" class="btn btn-default btn-info" style="margin-left: 40px;margin-bottom: 3px;">查询</button>
</form>
</body>
<script>
var pageIndex=null;
var employeequery_inf=null;
var pageSize=null;
var totalPageSum=null;
var totalRecordSum=null;
$("#query").click(function() {
	employeequery_inf=$("#employeequery").serialize();
    $.ajax({
        type: "POST",
        dataType: "json",
        url: "${ctxPath}/EmployeeManageServlet/employeeQuery",
        data: $("#employeequery").serialize(),
        success: function (data) {
        	pageIndex=data.pagemodel.pageIndex;
            pageSize=data.pagemodel.pageSize;
        	totalPageSum=data.pagemodel.totalPageSum;
        	totalRecordSum=data.pagemodel.totalRecordSum;
        	var employeequery_table_start="<table id='employeequery_table' class='table table-striped' style='text-align:center;'><tr><td>姓名</td><td>性别</td><td>手机号码</td><td>邮箱</td><td>职位</td><td>学历</td><td>身份证号码</td><td>部门</td><td>联系地址</td><td>建档日期</td><td>操作</td></tr>";
            var employeequery_table_end="</table><div id='employeequery_table_k' style='padding-left:20px;text-align:center;'><span>总页数："+totalPageSum+"</span><span style='margin-left:20px;margin-right:20px;'>总记录条数："+totalRecordSum+"</span><button id='firstpage' type='button' class='btn btn-default btn-info' style='margin-right:20px;' onclick='firstpage()'>首页</button><button id='prepage' type='button' class='btn btn-default btn-info' style='margin-right:20px;' onclick='prepage()'>上页</button><button id='nextpage' type='button' class='btn btn-default btn-info' style='margin-right:20px;' onclick='nextpage()'>下页</button><button id='lastpage' type='button' class='btn btn-default btn-info' style='margin-right:20px;' onclick='lastpage()'>尾页</button><span style='margin-right:5px;'>跳转到</span><input type='text' name='page_jump' class='form-control' id='page_jump' style='width:100px;display:inline-block;'><button id='do_page_jump' type='button' class='btn btn-default btn-info' style='margin-left:5px;margin-bottom:3px;' onclick='topage()'>确定</button></div>";
            var employeequery_table_body="";
            for(var i=0;i<data.employees_inf.length;i++){
            	employeequery_table_body=employeequery_table_body+"<tr><td>"+data.employees_inf[i].name+"</td><td>"+data.employees_inf[i].sex+"</td><td>"+data.employees_inf[i].phone+"</td><td>"+data.employees_inf[i].email+"</td><td>"+data.employees_inf[i].job_id+"</td><td>"+data.employees_inf[i].education+"</td><td>"+data.employees_inf[i].card_id+"</td><td>"+data.employees_inf[i].dept_id+"</td><td>"+data.employees_inf[i].address+"</td><td>"+data.employees_inf[i].create_date+"</td><td><a href='${ctxPath}/EmployeeManageServlet/employeeModify?id="+data.employees_inf[i].id+"'>修改</a>&nbsp|&nbsp<a href='javascript:void(0);' onclick='employeeDelete("+data.employees_inf[i].id+")'>删除</a></td></tr>";
            }
            $("#employeequery_table").remove();
            $("#employeequery_table_k").remove();
            $("#employeequery").after(employeequery_table_start+employeequery_table_body+employeequery_table_end);
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
        url: "${ctxPath}/EmployeeManageServlet/employeeQuery_toPage",
        data: employeequery_inf+"&pageIndex="+pageIndex+"&pageSize="+pageSize,
        success: function (data) {
        	console.log(data);
        	pageIndex=data.pagemodel.pageIndex;
            pageSize=data.pagemodel.pageSize;
        	totalPageSum=data.pagemodel.totalPageSum;
        	totalRecordSum=data.pagemodel.totalRecordSum;
        	var employeequery_table_start="<table id='employeequery_table' class='table table-striped' style='text-align:center;'><tr><td>姓名</td><td>性别</td><td>手机号码</td><td>邮箱</td><td>职位</td><td>学历</td><td>身份证号码</td><td>部门</td><td>联系地址</td><td>建档日期</td><td>操作</td></tr>";
            var employeequery_table_end="</table><div id='employeequery_table_k' style='padding-left:20px;text-align:center;'><span>总页数："+totalPageSum+"</span><span style='margin-left:20px;margin-right:20px;'>总记录条数："+totalRecordSum+"</span><button id='firstpage' type='button' class='btn btn-default btn-info' style='margin-right:20px;' onclick='firstpage()'>首页</button><button id='prepage' type='button' class='btn btn-default btn-info' style='margin-right:20px;' onclick='prepage()'>上页</button><button id='nextpage' type='button' class='btn btn-default btn-info' style='margin-right:20px;' onclick='nextpage()'>下页</button><button id='lastpage' type='button' class='btn btn-default btn-info' style='margin-right:20px;' onclick='lastpage()'>尾页</button><span style='margin-right:5px;'>跳转到</span><input type='text' name='page_jump' class='form-control' id='page_jump' style='width:100px;display:inline-block;'><button id='do_page_jump' type='button' class='btn btn-default btn-info' style='margin-left:5px;margin-bottom:3px;' onclick='topage()'>确定</button></div>";
            var employeequery_table_body="";
            for(var i=0;i<data.employees_inf.length;i++){
            	employeequery_table_body=employeequery_table_body+"<tr><td>"+data.employees_inf[i].name+"</td><td>"+data.employees_inf[i].sex+"</td><td>"+data.employees_inf[i].phone+"</td><td>"+data.employees_inf[i].email+"</td><td>"+data.employees_inf[i].job_id+"</td><td>"+data.employees_inf[i].education+"</td><td>"+data.employees_inf[i].card_id+"</td><td>"+data.employees_inf[i].dept_id+"</td><td>"+data.employees_inf[i].address+"</td><td>"+data.employees_inf[i].create_date+"</td><td><a href='${ctxPath}/EmployeeManageServlet/employeeModify?id="+data.employees_inf[i].id+"'>修改</a>&nbsp|&nbsp<a href='javascript:void(0);' onclick='employeeDelete("+data.employees_inf[i].id+")'>删除</a></td></tr>";
            }
            $("#employeequery_table").remove();
            $("#employeequery_table_k").remove();
            $("#employeequery").after(employeequery_table_start+employeequery_table_body+employeequery_table_end);
	}
    });
}
function employeeDelete(id){
	$.ajax({
        type: "POST",
        dataType: "json",
        url: "${ctxPath}/EmployeeManageServlet/employeeDelete",
        data: {"id":id},
        success: function (data) {
        	if(data.employeedelete_result==true){
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
function init(){
	$.ajax({
        type: "POST",
        dataType: "json",
        url: "${ctxPath}/EmployeeManageServlet/employeeQueryInit",
        success: function (data) {
        	var deptnamemap=data.deptnamemap;
        	for(let key in data.jobnamemap){
        		$("#job").append("<option value='"+key+"'>"+data.jobnamemap[key]+"</option>");
        	}
        	for(let key in data.deptnamemap){
      		    $("#dept").append("<option value='"+key+"'>"+data.deptnamemap[key]+"</option>");
      		}
    	}
    });
}
init();
</script>
</html>