<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!--/webapp/ex01_directive/ex02_includeDirective.jsp -->
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	</head>
	<body>
	<% String msg = "ex02_includeDirective.jsp 페이지의 msg 변수";%>
	<h1>ex02_includeDirective.jsp 입니다.</h1>
	
	<%@ include file="ex02_inclubDirective2.jsp" %>
	
	<h3>
	include 지시어를 이용하여 다른 페이지의 내용을 가져올 수 있습니다.<br/>
	2개의 jsp 페이지는 하나의 서블릿으로 변환 되므로 변수의 공유가 가능합니다
	</h3>
	</body>
</html>