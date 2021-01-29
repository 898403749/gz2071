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
<form id="noticequery" action="${ctxPath}/EmployeeManageServlet/employeeQuery" method="post" style="padding-left: 20px;padding-right: 150px;padding-top:20px;padding-bottom:20px;background-color: #F5F7FA;">
<div class="form-group" style="display: inline-block;">
	<label for="exampleInputname">公告名称：</label>
	<input type="text" name="name"  class="form-control" style="width:200px;display: inline-block;" id="name" placeholder="name">
</div>
<div class="form-group" style="display: inline-block;">
	<label for="exampleInputtype">公告类型：</label>
	<select id="type" name="type" class="form-control" style="display: inline-block;width: 200px;">
  		<option id="type_ts" value="" style="" selected>--请选择类型--</option>
	</select>
</div>
<button id="query" type="button" class="btn btn-default btn-info" style="margin-left: 40px;margin-bottom: 3px;">查询</button>
</form>
</body>
<script>
var pageIndex=null;
var noticequery_inf=null;
var pageSize=null;
var totalPageSum=null;
var totalRecordSum=null;
$("#query").click(function() {
	noticequery_inf=$("#noticequery").serialize();
    $.ajax({
        type: "POST",
        dataType: "json",
        url: "${ctxPath}/NoticeManageServlet/noticeQuery",
        data: $("#noticequery").serialize(),
        success: function (data) {
        	pageIndex=data.pagemodel.pageIndex;
            pageSize=data.pagemodel.pageSize;
        	totalPageSum=data.pagemodel.totalPageSum;
        	totalRecordSum=data.pagemodel.totalRecordSum;
        	var noticequery_table_start="<table id='noticequery_table' class='table table-striped' style='text-align:center;'><tr><td>公告名</td><td>公告类型</td><td>发布人</td><td>发布时间</td><td>更改时间</td><td>操作</td><td></td></tr>";
            var noticequery_table_end="</table><div id='noticequery_table_k' style='padding-left:20px;text-align:center;'><span>总页数："+totalPageSum+"</span><span style='margin-left:20px;margin-right:20px;'>总记录条数："+totalRecordSum+"</span><button id='firstpage' type='button' class='btn btn-default btn-info' style='margin-right:20px;' onclick='firstpage()'>首页</button><button id='prepage' type='button' class='btn btn-default btn-info' style='margin-right:20px;' onclick='prepage()'>上页</button><button id='nextpage' type='button' class='btn btn-default btn-info' style='margin-right:20px;' onclick='nextpage()'>下页</button><button id='lastpage' type='button' class='btn btn-default btn-info' style='margin-right:20px;' onclick='lastpage()'>尾页</button><span style='margin-right:5px;'>跳转到</span><input type='text' name='page_jump' class='form-control' id='page_jump' style='width:100px;display:inline-block;'><button id='do_page_jump' type='button' class='btn btn-default btn-info' style='margin-left:5px;margin-bottom:3px;' onclick='topage()'>确定</button></div>";
            var noticequery_table_body="";
            for(var i=0;i<data.notices_inf.length;i++){
            	var ct=data.notices_inf[i].create_date;
            	if(ct!=null){
            		ct=timestampToTime(ct);
            	}
            	var mt=data.notices_inf[i].modify_date;
            	if(mt!=null){
            		mt=timestampToTime(mt);
            	}
            	noticequery_table_body=noticequery_table_body+"<tr><td>"+data.notices_inf[i].name+"</td><td>"+data.notices_inf[i].type+"</td><td>"+data.notices_inf[i].user+"</td><td>"+ct+"</td><td>"+mt+"</td><td><a href='${ctxPath}/NoticeManageServlet/noticeModify?id="+data.notices_inf[i].id+"'>修改</a>&nbsp|&nbsp<a href='javascript:void(0);' onclick='noticeDelete("+data.notices_inf[i].id+")'>删除</a></td><td><a href='${ctxPath}/NoticeManageServlet/noticeWatch?id="+data.notices_inf[i].id+"'>查看</a></td></tr>";
            }
            $("#noticequery_table").remove();
            $("#noticequery_table_k").remove();
            $("#noticequery").after(noticequery_table_start+noticequery_table_body+noticequery_table_end);
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
        url: "${ctxPath}/NoticeManageServlet/noticeQuery_toPage",
        data: noticequery_inf+"&pageIndex="+pageIndex+"&pageSize="+pageSize,
        success: function (data) {
        	console.log(data);
        	pageIndex=data.pagemodel.pageIndex;
            pageSize=data.pagemodel.pageSize;
        	totalPageSum=data.pagemodel.totalPageSum;
        	totalRecordSum=data.pagemodel.totalRecordSum;
        	var noticequery_table_start="<table id='noticequery_table' class='table table-striped' style='text-align:center;'><tr><td>公告名</td><td>公告类型</td><td>发布人</td><td>发布时间</td><td>更改时间</td><td>操作</td><td></td></tr>";
            var noticequery_table_end="</table><div id='noticequery_table_k' style='padding-left:20px;text-align:center;'><span>总页数："+totalPageSum+"</span><span style='margin-left:20px;margin-right:20px;'>总记录条数："+totalRecordSum+"</span><button id='firstpage' type='button' class='btn btn-default btn-info' style='margin-right:20px;' onclick='firstpage()'>首页</button><button id='prepage' type='button' class='btn btn-default btn-info' style='margin-right:20px;' onclick='prepage()'>上页</button><button id='nextpage' type='button' class='btn btn-default btn-info' style='margin-right:20px;' onclick='nextpage()'>下页</button><button id='lastpage' type='button' class='btn btn-default btn-info' style='margin-right:20px;' onclick='lastpage()'>尾页</button><span style='margin-right:5px;'>跳转到</span><input type='text' name='page_jump' class='form-control' id='page_jump' style='width:100px;display:inline-block;'><button id='do_page_jump' type='button' class='btn btn-default btn-info' style='margin-left:5px;margin-bottom:3px;' onclick='topage()'>确定</button></div>";
            var noticequery_table_body="";
            for(var i=0;i<data.notices_inf.length;i++){
            	var ct=data.notices_inf[i].create_date;
            	if(ct!=null){
            		ct=timestampToTime(ct);
            	}
            	var mt=data.notices_inf[i].modify_date;
            	if(mt!=null){
            		mt=timestampToTime(mt);
            	}
            	noticequery_table_body=noticequery_table_body+"<tr><td>"+data.notices_inf[i].name+"</td><td>"+data.notices_inf[i].type+"</td><td>"+data.notices_inf[i].user+"</td><td>"+ct+"</td><td>"+mt+"</td><td><a href='${ctxPath}/NoticeManageServlet/noticeModify?id="+data.notices_inf[i].id+"'>修改</a>&nbsp|&nbsp<a href='javascript:void(0);' onclick='noticeDelete("+data.notices_inf[i].id+")'>删除</a></td><td><a href='${ctxPath}/NoticeManageServlet/noticeWatch?id="+data.notices_inf[i].id+"'>查看</a></td></tr>";
            }
            $("#noticequery_table").remove();
            $("#noticequery_table_k").remove();
            $("#noticequery").after(noticequery_table_start+noticequery_table_body+noticequery_table_end);
	}
    });
}
function noticeDelete(id){
	$.ajax({
        type: "POST",
        dataType: "json",
        url: "${ctxPath}/NoticeManageServlet/noticeDelete",
        data: {"id":id},
        success: function (data) {
        	console.log(data);
        	if(data.noticedelete_result==true){
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
        url: "${ctxPath}/NoticeManageServlet/noticeQueryInit",
        success: function (data) {
        	console.log(data);
        	for(let key in data.typenamemap){
        		$("#type").append("<option value='"+key+"'>"+data.typenamemap[key]+"</option>");
        	}
    	}
    });
}
init();
function timestampToTime(timestamp){
	var date=new Date(parseInt(timestamp,10));
	var nian=date.getFullYear();
	var yue=(date.getMonth()+1<10?'0'+(date.getMonth()+1):date.getMonth()+1);
	var ri=date.getDate();
	var xiaoshi=date.getHours()<10?'0'+date.getHours():date.getHours();
	var fenzhong=date.getMinutes()<10?'0'+date.getMinutes():date.getMinutes();
	var miaozhong=date.getSeconds()<10?'0'+date.getSeconds():date.getSeconds();
	return nian+'-'+yue+'-'+ri+' '+xiaoshi+':'+fenzhong+':'+miaozhong;
}
</script>
</html>