<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>아이디 찾기</title>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
	</head>
	<body>
		<table class="table">
			<tr>
				<th>아이디</th>
				<td>
					<c:set var="trimmedId" value="${fn:substring(id, 0, fn:length(id) - 2)}" />
					<c:out value="${trimmedId}**" />
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="button" value="아이디전송" onclick="idsend(`${trimmedId}**`)" class="btn btn-dark" >
				</td>
			</tr>
		</table>
		<script type="text/javascript">
			function idsend(id){ // id : 실제 id에서 뒤의 2자리를 제외한 값
				opener.document.f.id.value = id; 
				self.close(); // 현재 페이지를 닫기
			}
		</script>
	</body>
</html>
