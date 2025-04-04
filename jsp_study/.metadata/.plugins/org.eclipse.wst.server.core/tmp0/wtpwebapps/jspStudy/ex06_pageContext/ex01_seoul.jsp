<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>서울의 도시정보</title>
	</head>
	<body>
		<h1>서울</h1>
		<h3>서울은 대한민국의 수도다</h3>
		<h3>서울의 인구는 약 1000만명이다.</h3>
		<h3><%= request.getParameter("city") %></h3>
	</body>
</html>