<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>아이디 찾기</title>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
	</head>
	<body>
	<h3 align="center">아이디 찾기</h3>
	<form action="id" method="post">
		<table class="table">
			<tr><th>이메일</th><td><input type="text" name="email" class="form-control"></td></tr>
			<tr><th>전화번호</th><td><input type="text" name="tel" class="form-control"></td></tr>
			<tr><td colspan="2" align="center"><input type="submit" value="아이디 찾기" class="btn btn-primary"></td></tr>
		</table>
	</form>
	</body>
</html>