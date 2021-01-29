<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equio:"Content-Type" content="text/html;charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/css.css"/>
</head>
<body>
<ul id="box">
    <c:forEach items="${menu}" var="e">
    <li>
        <div>${e.value.menuName}</div>
        <ul>
        	<c:forEach items="${e.value.itemSet}" var="l">
            <li>${l.itemName}</li>
            </c:forEach>
        </ul>
    </li>
    </c:forEach>
</ul>
</body>
<script src="jQ/jquery-1.11.1.min.js"></script>
<script>
    yin();
    function yin(){
    	for(var i=0;i<$("#box li div").length;i++){
    		$($("#box li div")[i]).next().addClass("n");
    	}
    }
    $("#box li div").click(
        function () {
            for(i=0;i<$("#box li div").length;i++){
				if(i!=$("#box li div").index($(this))&&!$($("#box li div")[i]).next().hasClass("n")){
            		$($("#box li div")[i]).next().toggleClass("n");
            	}
			}
            $(this).next().toggleClass("n");
    	}
    );

</script>
</html>