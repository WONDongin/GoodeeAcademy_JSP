<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>Teste4</title>
	</head>
	
	<body>
		이름 : ${param.name}<br/>
		나이 : ${param.age}살<br/>
		성별 : ${param.gender == 1? "남" : "여"}<br/>
		출생년도 : ${param.year}년<br/>
		나이 : 만 ${2025 - param.year}살<br/>
	</body>
</html>