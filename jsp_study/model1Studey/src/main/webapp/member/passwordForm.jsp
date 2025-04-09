<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%-- 
로그인 한 경우만 이 페이지가 출력하도록 수정하기
로그아웃 상태 : 화면에 로그인 하세요 메세지 출력 + opener의 url을 loginForm.jsp 페이지로 이동하기.
			 현재 페이지 닫기
--%>
<%
	String login = (String) session.getAttribute("login");
	if(login == null){
%>
<script type="text/javascript">
	alert("로그인 하세요");
	opener.location.href = "loginForm.jsp";
	self.close();
</script>

<% } else { %>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>비밀번호 변경</title>
	<link rel="stylesheet" href="../css/main.css">
	</head>
	<body>
		<form action="password.jsp" method="post" name="f" onsubmit="return input_check(this)">
			<table>
				<caption>비밀번호 변경</caption>
				<tr>
					<th>현재 비밀번호</th>
					<td><input type="password" name="pass"></td>
				</tr>
				<tr>
					<th>변경 비밀번호</th>
					<td><input type="password" name="chgpass"></td>
				</tr>
				<tr>
					<th>변경 비밀번호 재입력</th>
					<td><input type="password" name="chgpass2"></td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="submit" value="비밀번호변경">
						<input type="reset" value="초기화">
					</td>
				</tr>
			</table>
		</form>
		
		<script type="text/javascript">
			function input_check(f){
				if(f.chgpass.value != f.chgpass2.value){
					alert("변경 비밀번호와 변경 비밀번호 재입력이 다릅니다.");
					f.chgpass2.value="";
					f.chgpass2.focus();
					return false;
				}
				return true;
			}
		</script>
	</body>
</html>
<% } %>