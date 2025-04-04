<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>쿠키 삭제하기</title>
	</head>
	<body>
		<%
		Cookie cookie = new Cookie("name",""); // 삭제할 쿠키의 이름으로 쿠키 생성
		cookie.setMaxAge(0); // 유효시간을 0으로 설정
		response.addCookie(cookie); // 클라이언트로 전송
		/*
		유효기간이 종료한 쿠키를 받은 클라이언트는 쿠기 저장소에서 이름에 해당하는 쿠키를 제거함.
		*/
		%>
		<h2>쿠기가 삭제 되었습니다.</h2>
		<a href = "ex02_cookie.jsp">쿠키 조회하기</a>
	</body>
</html>