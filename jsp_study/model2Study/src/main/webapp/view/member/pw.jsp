<%@page import="model.member.MemberDao"%>
<%@page import="java.lang.reflect.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>비밀번호 찾기</title>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
	</head>
	<body>
		<table class="table">
			<tr><th>비밀번호</th><td>**${pass}</td></tr>
			<tr>
				<td colspan="2" align="center">
					<input type="button" value="닫기" onclick="idsend()" class="btn btn-dark">
				</td>
			</tr>
		</table>
		<script type="text/javascript">
			function idsend(){ 
				self.close();
			}
		</script>
	</body>
</html>