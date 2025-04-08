<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>아이디 찾기</title>
	</head>
	<body>
	<h3>아이디 찾기</h3>
	<link rel="stylesheet" href="../css/main.css">
	<form action="id.jsp" method="post">
		<table>
			<tr><th>이메일</th><td><input type="text" name="email"></td></tr>
			<tr><th>전화번호</th><td><input type="text" name="tel"></td></tr>
			<tr><td colspan="2"><input type="submit" value="아이디찾기"></td></tr>
		</table>
	</form>
	</body>
</html>