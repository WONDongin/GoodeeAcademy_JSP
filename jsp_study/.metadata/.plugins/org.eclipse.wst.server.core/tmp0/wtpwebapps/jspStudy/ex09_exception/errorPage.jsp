<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page isErrorPage = "true" %>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>error</title>
	</head>
	<body>
		<h1>입력값을 확인해 주세요</h1>
		<h3>숫자만 입력가능합니다.</h3>
		<%-- 오류메세지 브라우저의 화면에 출력하기 --%>
		<%= exception.getMessage() %><br/>
		<% exception.printStackTrace(response.getWriter()); %>
	</body>
</html>