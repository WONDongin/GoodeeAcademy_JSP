<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>      
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원정보</title>
</head>
<body>
<%--
request.setAttribute("mem",Member객체) 
${mem.picture} : mem.getPicture() 메서드가 호출됨
				 picture을 get 프로퍼티(preperty)
${mem.id} : mem.getId() 메서드 호출
--%>
<table>
<tr><td rowspan="6" width="30%">
  <img src="../picture/${mem.picture}" width="200" height="210"></td>
  <th width="20%">아이디</th><td>${mem.id}</td>
  <tr><th>이름</th><td>${mem.name}</td>
  <tr><th>성별</th><td>${mem.gender == 1?"남":"여"}</td>
  <tr><th>전화</th><td>${mem.tel}</td>
  <tr><th>이메일</th><td>${mem.email}</td>
  </tr>
  <tr><td colspan="2">
     <a href="updateForm?id=${mem.id}">수정</a>
     <c:if test="${param.id != 'admin'}">
     	<a href="deleteForm?id=${mem.id}">탈퇴</a>
     </c:if>
  </td></tr>
</table>
</body>
</html>