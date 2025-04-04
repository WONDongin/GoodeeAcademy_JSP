<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%-- errorPage = "errorPage.jps" : 현재 페이지에서 오류발생하면 errorPage.jsp 호출 --%>    
<%-- 
Page isErrorPage = true %> 
- 현재 페이지는 오류페이지, exception 객체에 추가됨
--%>
<%@ page errorPage = "errorPage.jsp" %>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>오류 발생 페이지</title>
	</head>
	<body>
		<% int num = Integer.parseInt("abc"); %>
	</body>
</html>