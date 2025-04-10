<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>JSTL core 태그 : forEach</title>
	</head>
	<body>
		<h3>반복 관련 태그 : forEach</h3>
		<h4>1에서 10까지 숫자 출력하기</h4>
		<%-- 반복문 --%>
		<c:forEach var="i" begin="1" end="10">
			${i}&nbsp;&nbsp;
		</c:forEach><br/>
		
		<%-- step : 2씩 증가 --%>
		<h4>1에서 10까지 홀수 출력하기</h4>
		<c:forEach var="i" begin="1" end="10" step="2">
			${i}&nbsp;&nbsp;
		</c:forEach><br/>
		
		<%-- + if 조건문 --%>
		<c:forEach var="i" begin="1" end="10">
			<c:if test="${i%2==1 }">
			${i}&nbsp;&nbsp;
			</c:if>
		</c:forEach><br/>
		
		<h4>1에서 10까지 짝수 출력하기</h4>
		<c:forEach var="i" begin="2" end="10">
			<c:if test="${i%2==0 }">
			${i}&nbsp;&nbsp;
			</c:if>
		</c:forEach><br/>
		
		<h4>1에서 10까지의 합 출력하기</h4>
		<c:forEach var="i" begin="1" end="10">
			<c:set var="sum" value="${sum + i}" />
		</c:forEach>
		1에서 10까지의 합 : ${sum}<br/>
		
		
		<%-- 상수값으로 초기화 --%>
		<c:set var="sum" value="${0}"/>
		<h4>1에서 10까지의 짝수합 출력하기</h4>
		<c:forEach var="i" begin="2" end="10" step="2">
			<c:set var="sum" value="${sum + i}" />
		</c:forEach>
		1에서 10까지의 짝수합 : ${sum}<br/>		
		
		<c:set var="sum" value="${0}"/>
		<h4>1에서 10까지의 홀수합 출력하기</h4>
		<c:forEach var="i" begin="1" end="10">
			<c:if test="${i%2==1 }">
				<c:set var="sum" value="${sum + i}" />
			</c:if>
		</c:forEach>
		1에서 10까지의 홀수합 : ${sum}<br/>
		
		<h3>구구단 출력</h3>
		<c:forEach var="i" begin="2" end="9">
			<h4>${i}단</h4>
			<c:forEach var="j" begin="2" end="9">
				${i} * ${j} = ${i*j}<br/>
			</c:forEach>
		</c:forEach>
		
		<h4>10에서 1까지 숫자 출력하기</h4>
		<c:set var="ii" value="${10}"></c:set>
		<c:forEach var="i" begin="1" end="10">
			${ii}&nbsp;&nbsp;
			<c:set var="ii" value="${ii -1}"></c:set>
		</c:forEach><br/>
		
		
		<%-- list 객체요소 --%>
		<h3>forEach 태그를 이용하여 List 객체의 요소 출력하기</h3>
		<%
			List<Integer> list = new ArrayList<>();
			for(int i=1; i<=10; i++){
				list.add(i*10);
			}
			pageContext.setAttribute("list", list);
		%>
		<c:forEach var="a" items="${list}" varStatus="s">
			${s.index} : ${a} &nbsp;&nbsp;&nbsp;
		</c:forEach><br/>
		
		<c:forEach var="i" items="${list}" varStatus="s">
			<c:if test="${s.index == 5 }"><br></c:if>
			${s.count} : ${i} &nbsp;&nbsp;&nbsp;
		</c:forEach><br/>
	</body>
</html>