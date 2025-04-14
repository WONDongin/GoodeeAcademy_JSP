<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%-- 
로그인 한 경우만 이 페이지가 출력하도록 수정하기
로그아웃 상태 : 화면에 로그인 하세요 메세지 출력 + opener의 url을 loginForm.jsp 페이지로 이동하기.
			 현재 페이지 닫기
--%>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>비밀번호 변경</title>
	<%-- layout 제외 페이지이기 때문에 부트스트랩 안먹는다, 따로링크 연결 --%>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
	</head>
	<body>
		<h3 align="center">비밀번호 변경</h3>
		<form action="password" method="post" name="f" onsubmit="return input_check(this)">
			<table class="table">
				<tr>
					<th>현재 비밀번호</th>
					<td><input type="password" name="pass" class="form-control"></td>
				</tr>
				<tr>
					<th>변경 비밀번호</th>
					<td><input type="password" name="chgpass" class="form-control"></td>
				</tr>
				<tr>
					<th>변경 비밀번호 재입력</th>
					<td><input type="password" name="chgpass2" class="form-control"></td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input type="submit" value="비밀번호변경" class="btn btn-primary">
						<input type="reset" value="초기화" class="btn btn-secondary">
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