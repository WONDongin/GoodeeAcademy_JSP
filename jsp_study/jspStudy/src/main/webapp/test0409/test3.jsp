<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html><head>
<meta charset="UTF-8">
<title>jstl을 이용한 간단한 연산</title>
</head>
	<body>
		<form method="post" name="f">
	 		 x:<input type="text" name="x" value="${param.x}" size="5">
	 		 <select name="op">
	     		<option selected>+</option><option>-</option>
	     		<option>*</option><option>/</option>
	  		</select>
	  		y:<input type="text" name="y" value="${param.y}" size="5">
	 		 <input type="submit" value="="><br/>
	 		 
	 		<c:set var="op1" value="+"></c:set>
	 		<c:set var="op2" value="-"></c:set>
	 		<c:set var="op3" value="*"></c:set>
	 		<c:set var="op4" value="/"></c:set>

	 		<c:choose>
	 			<c:when test="${param.op == op1 }">
	 				<p>${param.x} + ${param.y} = ${param.x + param.y}</p>
	 			</c:when>
	 			<c:when test="${param.op == op2 }">
	 				<p>${param.x} - ${param.y} = ${param.x - param.y}</p>
	 			</c:when>
	 			<c:when test="${param.op == op3 }">
	 				<p>${param.x} x ${param.y} = ${param.x * param.y}</p>
	 			</c:when>
	 			<c:when test="${param.op == op4 }">
	 				<p>${param.x} ÷ ${param.y} = ${param.x / param.y}</p>
	 			</c:when>
	 		</c:choose> 
		</form>
	</body>
</html>