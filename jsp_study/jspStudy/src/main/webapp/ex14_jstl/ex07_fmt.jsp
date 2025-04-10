<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>JSTL 형식화 태그 : parse 관련 태그</title>
	</head>
	<body>
		<h3>Format 된 숫자를 숫자로 변경</h3>
		<%-- var="num1" 속성값으로 등록됨. 화면 출력 안됨 --%>
		<fmt:parseNumber value="20,000" var="num1" pattern="##,###" /> 
		<fmt:parseNumber value="10,000" var="num2" pattern="##,###" /> 
		합 : ${num1} + ${num2 } = ${num1 +num2 }<br/>
		
		<%-- var 속성이 없으면. 화면 출력됨 --%>
		<fmt:parseNumber value="20,000" pattern="##,###" /><br/> 
		<fmt:parseNumber value="10,000" pattern="##,###" /><br/> 
		
		문제 : 20,000 + 10,000 = 30,000 출력하기<br/>
		num1, num2 변수를 이용하기<br/>	
		<fmt:formatNumber value="${num1}" pattern="##,###" /> +
		<fmt:formatNumber value="${num2}" pattern="##,###" /> =
		<fmt:formatNumber value="${num1 + num2}" pattern="##,###" /><br/>
		
		<fmt:formatNumber value="${num1}" var="sum1" pattern="##,###" /> 
		<fmt:formatNumber value="${num2}" var="sum2" pattern="##,###" /> 
		<fmt:formatNumber value="${num1 + num2}"  var="sum3" pattern="##,###" />
		${sum1} + ${sum2} = ${sum3}<br/>
		
		<h3>Format 된 날짜를 날짜형으로 변경하기</h3>
		<fmt:parseDate value="2025-12-25 12:00:00" pattern="yyyy-MM-dd HH:mm:ss" var="day" />
		${day}<br/>
		<fmt:parseDate value="2025-12-25 12:00:00" pattern="yyyy-MM-dd HH:mm:ss" /><br/>
		
		<p>문제 : 2025-12-25일의 요일 출력</p><br/>
		<fmt:formatDate value="${day}" pattern="E"/>요일
		
		
	</body>
</html>