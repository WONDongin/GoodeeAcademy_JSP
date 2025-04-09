<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- /jspstudy2/src/main/webapp/test/test1.jsp --%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>두개의 파라미터값을 계산하기</title>
</head>
<body>
<form method="post" >
  x:<input type="text" name="x" value="${param.x}"><br>
  y:<input type="text" name="y" value="${param.y}">
   <input type="submit" value="더하기"><br/>
   
   <c:set var="result" value="${param.x + param.y}" />
   합계 : <c:out value="${result}" />
   
  <h3>if 태그를 이용하여 출력하기</h3>
  <c:if test="${result > 0}">
  	<p>${result}은 양수 입니다.</p>
  </c:if>
  <c:if test="${result < 0}">
  	<p>${result}은 음수 입니다.</p>
  </c:if>
  
  <h3>choose when 태그를 이용하여 출력하기</h3>
  <c:choose>
  	<c:when test="${result > 0}">
  		<p>${result}은 양수 입니다.</p>
  	</c:when>
  	<c:when test="${result < 0}">
  		<p>${result}은 음수 입니다.</p>
  	</c:when>
  	<c:otherwise>
  		<p>아무것도 입력하지 않았습니다.</p>
  	</c:otherwise>
  </c:choose>
  
</form>
</body>
</html>