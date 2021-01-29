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
<form id="filequery" action="${ctxPath}/UploadFileServlet/fileQuery" method="post" style="padding-left: 20px;padding-right: 150px;padding-top:20px;padding-bottom:20px;background-color: #F5F7FA;">
<span>标题：</span>
<input type="text" name="title"  class="form-control" style="display: inline-block;width: 200px;" id="title" placeholder="title">
<button id="query" type="button" class="btn btn-default btn-info" style="margin-left: 40px;margin-bottom: 3px;">查询</button>
</form>
</body>
<script>
var pageIndex=null;
var filequery_inf=null;
var pageSize=null;
var totalPageSum=null;
var totalRecordSum=null;
$("#query").click(function() {
	filequery_inf=$("#filequery").serialize();
    $.ajax({
        type: "POST",
        dataType: "json",
        url: "${ctxPath}/UploadFileServlet/fileQuery",
        data: $("#filequery").serialize(),
        success: function (data) {
        	pageIndex=data.pagemodel.pageIndex;
            pageSize=data.pagemodel.pageSize;
        	totalPageSum=data.pagemodel.totalPageSum;
        	totalRecordSum=data.pagemodel.totalRecordSum;
        	var filequery_table_start="<table id='filequery_table' class='table table-striped' style='text-align:center;'><tr><td>标题</td><td>创建时间</td><td>创建人</td><td>描述</td><td>操作</td><td></td></tr>";
            var filequery_table_end="</table><div id='filequery_table_k' style='padding-left:20px;text-align:center;'><span>总页数："+totalPageSum+"</span><span style='margin-left:20px;margin-right:20px;'>总记录条数："+totalRecordSum+"</span><button id='firstpage' type='button' class='btn btn-default btn-info' style='margin-right:20px;' onclick='firstpage()'>首页</button><button id='prepage' type='button' class='btn btn-default btn-info' style='margin-right:20px;' onclick='prepage()'>上页</button><button id='nextpage' type='button' class='btn btn-default btn-info' style='margin-right:20px;' onclick='nextpage()'>下页</button><button id='lastpage' type='button' class='btn btn-default btn-info' style='margin-right:20px;' onclick='lastpage()'>尾页</button><span style='margin-right:5px;'>跳转到</span><input type='text' name='page_jump' class='form-control' id='page_jump' style='width:100px;display:inline-block;'><button id='do_page_jump' type='button' class='btn btn-default btn-info' style='margin-left:5px;' onclick='topage()'>确定</button></div>";
            var filequery_table_body="";
            for(var i=0;i<data.documents_inf.length;i++){
            	filequery_table_body=filequery_table_body+"<tr><td>"+data.documents_inf[i].title+"</td><td>"+data.documents_inf[i].create_date+"</td><td>"+data.documents_inf[i].user_id+"</td><td>"+data.documents_inf[i].remark+"</td><td><a href='${ctxPath}/UploadFileServlet/fileModify?id="+data.documents_inf[i].id+"'>修改</a>&nbsp|&nbsp<a href='javascript:void(0);' onclick='fileDelete("+data.documents_inf[i].id+")'>删除</a></td><td><a href='${ctxPath}/DownloadFileServlet?filename="+data.documents_inf[i].filename+"'>下载</a></td></tr>";
            }
            $("#filequery_table").remove();
            $("#filequery_table_k").remove();
            $("#filequery").after(filequery_table_start+filequery_table_body+filequery_table_end);
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
        url: "${ctxPath}/UploadFileServlet/fileQuery_toPage",
        data: filequery_inf+"&pageIndex="+pageIndex+"&pageSize="+pageSize,
        success: function (data) {
        	pageIndex=data.pagemodel.pageIndex;
            pageSize=data.pagemodel.pageSize;
        	totalPageSum=data.pagemodel.totalPageSum;
        	totalRecordSum=data.pagemodel.totalRecordSum;
        	var filequery_table_start="<table id='filequery_table' class='table table-striped' style='text-align:center;'><tr><td>标题</td><td>创建时间</td><td>创建人</td><td>描述</td><td>操作</td><td></td></tr>";
            var filequery_table_end="</table><div id='filequery_table_k' style='padding-left:20px;text-align:center;'><span>总页数："+totalPageSum+"</span><span style='margin-left:20px;margin-right:20px;'>总记录条数："+totalRecordSum+"</span><button id='firstpage' type='button' class='btn btn-default btn-info' style='margin-right:20px;' onclick='firstpage()'>首页</button><button id='prepage' type='button' class='btn btn-default btn-info' style='margin-right:20px;' onclick='prepage()'>上页</button><button id='nextpage' type='button' class='btn btn-default btn-info' style='margin-right:20px;' onclick='nextpage()'>下页</button><button id='lastpage' type='button' class='btn btn-default btn-info' style='margin-right:20px;' onclick='lastpage()'>尾页</button><span style='margin-right:5px;'>跳转到</span><input type='text' name='page_jump' class='form-control' id='page_jump' style='width:100px;display:inline-block;'><button id='do_page_jump' type='button' class='btn btn-default btn-info' style='margin-left:5px;' onclick='topage()'>确定</button></div>";
            var filequery_table_body="";
            for(var i=0;i<data.documents_inf.length;i++){
            	filequery_table_body=filequery_table_body+"<tr><td>"+data.documents_inf[i].title+"</td><td>"+data.documents_inf[i].create_date+"</td><td>"+data.documents_inf[i].user_id+"</td><td>"+data.documents_inf[i].remark+"</td><td><a href='${ctxPath}/UploadFileServlet/fileModify?id="+data.documents_inf[i].id+"'>修改</a>&nbsp|&nbsp<a href='javascript:void(0);' onclick='fileDelete("+data.documents_inf[i].id+")'>删除</a></td><td><a href='${ctxPath}/DownloadFileServlet?filename="+data.documents_inf[i].filename+"'>下载</a></td></tr>";
            }
            $("#filequery_table").remove();
            $("#filequery_table_k").remove();
            $("#filequery").after(filequery_table_start+filequery_table_body+filequery_table_end);
	}
    });
}
function fileDelete(id){
	$.ajax({
        type: "POST",
        dataType: "json",
        url: "${ctxPath}/UploadFileServlet/fileDelete",
        data: {"id":id},
        success: function (data) {
        	if(data.filedelete_result==true){
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