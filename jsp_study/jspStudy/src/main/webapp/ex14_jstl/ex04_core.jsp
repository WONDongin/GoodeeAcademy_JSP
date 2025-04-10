<%@page import="java.util.Arrays"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>forEach를 이용하여 Collection 객체 조회하기</title>
	</head>
	<body>
		<h3>forEach 태그를 이용하여 Map 객체 출력하기</h3>
		<%
			Map<String,Object> map = new HashMap<>();
			map.put("name", "홍길동");
			map.put("today", new Date());
			map.put("age", 20);
			pageContext.setAttribute("map1", map);
		%>
		<%-- count : 숫자 카운트 0, 1, 2... --%>
		<c:forEach var="m" items="${map1}" varStatus="stat">
			${stat.count} : ${m.key}=${m.value}<br/>
		</c:forEach>
		
		<h3>map 객체를 EL을 이용하여 출력</h3>
		이름 : ${map1.name}<br/>
		나이 : ${map1.age}<br/>
		오늘날짜 : ${map1.today}<br/>
		
		<h3>forEach를 이용하여 배열 객체 출력하기</h3>
		<c:set var="arr" value="<%= new int[]{10,20,30,40,50} %>"></c:set>
		<c:forEach var="a" items="${arr}" varStatus="stat">
			arr[${stat.index}]=${a}<br/>
		</c:forEach>
		
		<h3>배열 객체를 EL을 이용하여 출력하기</h3>
		arr[0] = ${arr[0]}<br/>
		arr[1] = ${arr[1]}<br/>
		arr[2] = ${arr[2]}<br/>
		arr[3] = ${arr[3]}<br/>
		arr[4] = ${arr[4]}<br/>
		
		<h3>List 객체를 EL을 이용하여 출력하기</h3>
		<c:set var="list" value="<%= Arrays.asList(10,20,30,40,50,60) %>" />
		list[0] = ${list[0] }<br/>
		list[2] = ${list[2] }<br/>
		list[4] = ${list[4] }<br/>
		
		<h3>forEach 태그를 이용하여 배열 객체의 두번째 요소부터 세번째 요소만 출력하기</h3>
		<%-- begin/end : 배열의 index 값을 의미 --%>
		<c:forEach var="a" items="${arr }" varStatus="stat" begin="1" end="2">
			arr[${stat.index}] = ${a}<br/>
		</c:forEach>
		
		<h3>forEach 태그를 이용하여 배열 객체의 짝수 인덱스 요소만 출력하기</h3>
		<c:forEach var="a" items="${arr }" varStatus="stat" step="2">
			arr[${stat.index}] = ${a}<br/>
		</c:forEach>
		
		<h3>forEach 태그를 이용하여 배열 객체의 홀수 인덱스 요소만 출력하기</h3>
		<c:forEach var="a" items="${arr }" varStatus="stat" begin="1" step="2">
			arr[${stat.index}] = ${a}<br/>
		</c:forEach>
	</body>
</html>