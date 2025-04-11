<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>아이디 찾기</title>
	</head>
	<body>
		<table>
			<tr><th>아이디</th><td>${mem.id}.substring(0,${mem.id}.length()-2) + "**"></td></tr>
			<tr>
				<td colspan="2">
					<input type="button" value="아이디전송" onclick="idsend('${mem.id}.substring(0,${mem.id}.length()-2))">
				</td>
			</tr>
		</table>
		<script type="text/javascript">
			function idsend(id){ // id : 실제 id에서 뒤의 2자리를 제외한 값
				// opener : loginForm.jsp
				opener.document.f.id.value = id; // loginForm.jsp 페이지에 id값 입력.
				self.close(); // 현재 페이지를 닫기
			}
		</script>
	</body>
</html>
