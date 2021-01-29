<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.shen.domain.User_Inf"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <c:set var="ctxPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta content="text/html;charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="css/login_web.css">
<link href="bootstrap3.3.7/css/bootstrap.min.css" rel="stylesheet">
<script src="bootstrap3.3.7/js/jquery-3.1.1.min.js"></script>
<script src="bootstrap3.3.7/js/bootstrap.min.js"></script>
</head>
<script src="jQ/jquery-1.11.1.min.js"></script>
<script>
    function changeCode(){
		var img=document.getElementById("valiImg");
		var ran=Math.floor(Math.random()*100000);
		img.src="${ctxPath}/CodeServlet?ran="+ran;
	}
</script>
<script>
    function addQRCode(){
        if($("input[id='b']").length!='1'){
        	$("#pd").after("<div class='form-group' style='width: 160px;display: inline-block;line-height:50px;'><input id='b' type='text' name='validate' autocomplete='off' class='form-control' placeholder='请输入验证码' style='padding-left: 5px;width: 160px;'/><span class='glyphicon glyphicon-remove form-control-feedback' aria-hidden='true'></span><span id='inputError2Status' class='sr-only'>(error)</span></div><div style='margin-bottom:2px;cursor:pointer;display:inline-block;vertical-align:middle;line-height:50px;margin-left:10px;width:80px;height:34px;' onclick='changeCode()'><img id='valiImg' style='display:inline-block;vertical-align:top;line-height:50px;' src='${ctxPath}/CodeServlet'/></div>");
        }
    }
</script>
<script>
        function tologin() {
            $.ajax({
                type: "POST",
                dataType: "json",
                url: "${ctxPath}/LoginServlet/checkLogin",
                data: $("#f").serialize(),
                success: function (data) {
                    var obj=data;
                    console.log(obj.reason);
                    if(obj.reason=="success"){
                    	var login_status="true";
                    	tologin_status(login_status);
                    	window.location.href='${ctxPath}/system_web.jsp';
                	}else{
                		var login_status="false";
                		tologin_status(login_status);
                        if(obj.reason=="无此账号"){
                        	document.getElementById("u").value="";
                        	inputTs("u",obj.reason,"请输入账号");
                        }else if(obj.reason=="密码错误"){
                        	inputTs("p",obj.reason,"请输入密码");
                        }else if(obj.reason=="验证码错误"){
                        	inputTs("b",obj.reason,"请输入验证码");
                        }
                        if($("input[id='b']").length=='1'){
                        	changeCode();
                        }
                        var p = document.getElementById("p");
                        p.value="";
                        if($("input[id='b']").length=='1'){
							var b = document.getElementById("b");
							b.value="";
                        }
                        addQRCode();
                	}
                }
            });
        }
        function inputTs(id,reason,after){
        	$("#"+id).focus(function (){
        		$("#"+id).css("border-color","#A94442");
        		$("#"+id).addClass("invalid");
        		$("#"+id).attr("placeholder",reason);
        		$("#"+id).parent().addClass("has-error");
        		$("#"+id).parent().addClass("has-feedback");
        	});
        	document.getElementById(id).focus();
        	$("#"+id).blur(function (){
        		$("#"+id).css("border-color","");
        		$("#"+id).removeClass("invalid");
        		$("#"+id).parent().removeClass("has-error");
        		$("#"+id).parent().removeClass("has-feedback");
        		$("#"+id).attr("placeholder",after);
        	});
        	$("#"+id).focus(function (){
        		$("#"+id).css("border-color","#339BFF");
        		$("#"+id).removeClass("invalid");
        		$("#"+id).parent().removeClass("has-error");
        		$("#"+id).parent().removeClass("has-feedback");
        		$("#"+id).attr("placeholder",after);
        	});
        }
</script>
<body>
    <div id="login_box" style="background-image: url('img/loginweb_photo1.jpg');background-repeat: no-repeat;">
    	<div id="denglu" style="width: 500px;height: 360px;">
    	<div id="datetime">
    		<div id="date1"></div>
    		<div id="date2"></div>
    	</div>
		<form id="f" action="${ctxPath}/LoginServlet/checkLogin" method="post">
		<div class="form-group" style="width: 250px;">
    		<input id="u" type="text" name="username" autocomplete="off" class="form-control" placeholder="请输入账号" style="padding-left: 5px;"/>
  			<span class='glyphicon glyphicon-remove form-control-feedback' aria-hidden='true'></span>
			<span id='inputError2Status' class='sr-only'>(error)</span>
  		</div>
  		<div id="pd" class="form-group" style="width: 250px;">
    		<input id="p" type="password" name="password" autocomplete="off" class="form-control" placeholder="请输入密码" style="padding-left: 5px;"/>
  			<span class='glyphicon glyphicon-remove form-control-feedback' aria-hidden='true'></span>
			<span id='inputError2Status' class='sr-only'>(error)</span>
  		</div>
  		<div style="margin-bottom: 3px;">
      	<input id="remember" type="checkbox" name="remember" value="1" style="height: 13px;width: 13px;margin-top:0px;">
  		<label style="margin-bottom: 0px;color: white;font-size: 5px;font-weight: 400;vertical-align: top;">记住密码</label>
  		</div>
		<button id="c" type="button" class="btn btn-primary" onclick="tologin()">登录</button>
		</form>
		</div>
	</div>
</body>
<script>
setInterval(gettime, 1000);
function gettime(){
	var myDate = new Date();
	var h=myDate.getHours()<10?"0"+myDate.getHours():myDate.getHours();
	var m=myDate.getMinutes()<10?"0"+myDate.getMinutes():myDate.getMinutes();
	document.getElementById('date1').innerHTML=h+":"+m;
	var chinesedate=["日","一","二","三","四","五","六"];
	document.getElementById('date2').innerHTML=myDate.getFullYear()+"年"+(myDate.getMonth()+1)+"月"+myDate.getDate()+"日"+"&nbsp&nbsp周"+chinesedate[myDate.getDay()];
}
gettime();
</script>
<script>
var pw=document.body.clientWidth;
var ph=window.screen.availHeight;
console.log("屏宽"+document.body.clientWidth);
console.log("屏高"+window.screen.availHeight);
var fh=$("#login_box form").outerHeight();
var fw=$("#login_box form").outerWidth();
var dh=$("#datetime").outerHeight();
var dw=$("#datetime").outerWidth();
$("#login_box").css("padding-top",(ph-fh-dh)/2+"px");
$("#login_box").css("padding-left",(pw-fw)/2+"px");
$("#login_box").css("height",ph+"px");
$("#login_box").css("width",(pw-17)+"px");
console.log("#login_box高"+$("#login_box").outerHeight());
console.log("#login_box宽"+$("#login_box").outerWidth());
console.log("form高"+fh);
console.log("form宽"+fw);
console.log("datetime高"+dh);
console.log("datetime宽"+dw);

</script>
<script>
function tologin_status(login_status){
	$.ajax({
        type: "POST",
        url: "${ctxPath}/LoginServlet/loginStatus",
        data: {"login_status":login_status}
    });
}
</script>
<script>
$(function () {
    var status=<%=session.getAttribute("login_status")%>
    console.log("loginstatus:"+status);
    if(!status&&status!=null){
    	$("#pd").after("<div class='form-group' style='width: 160px;display: inline-block;line-height:50px;'><input id='b' type='text' name='validate' autocomplete='off' class='form-control' placeholder='请输入验证码' style='padding-left: 5px;width: 160px;'/><span class='glyphicon glyphicon-remove form-control-feedback' aria-hidden='true'></span><span id='inputError2Status' class='sr-only'>(error)</span></div><div style='margin-bottom:2px;cursor:pointer;display:inline-block;vertical-align:middle;line-height:50px;margin-left:10px;width:80px;height:34px;' onclick='changeCode()'><img id='valiImg' style='display:inline-block;vertical-align:top;line-height:50px;' src='${ctxPath}/CodeServlet'/></div>");
    }
});
</script>
<script>
$(function (){
	var cookie=document.cookie;
	var cs=cookie.split(";");
	for(var i=0;i<cs.length;i++){
		var remuser=cs[i];
		remuser=remuser.split("=");
		var remname=remuser[0];
		var remvalue=remuser[1];
		var remusername=remvalue.split(":")[0];
		var rempassword=remvalue.split(":")[1];
		if(remname=="remember"){
			console.log("join remrember username");
			console.log("remname为"+remname+",remvalue为"+remvalue);
			document.getElementById("u").value=remusername;
			if(rempassword!=""&&rempassword!=null){
				console.log("join remrember password");
				document.getElementById("p").value=rempassword;
				document.getElementById("remember").checked=true;
			}
		}
	}
});
</script>
</html>