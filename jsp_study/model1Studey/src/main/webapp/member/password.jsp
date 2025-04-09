<%@page import="model.member.Member"%>
<%@page import="model.member.MemberDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
1. 파라미터 저장 (pass, chgpass)
2. 로그인한 사용자의 비밀번호 변경만 가능 => 로그인 부분 검증
   로그아웃 상태 : 로그인 하세요 메세지 출력 후
   				 opener 창을 loginForm.jsp 페이지로 이동. 현재 페이지 닫기
3. 비밀번호 검증 : 현재 비밀번호로 비교
   비밀번호 오류 : 비밀번호 오류 메세지 출력 후 현재 페이지를 passwordForm.jsp로 이동
4. db에 비밀번호 수정
	boolen MemberDao.updatePass(id, 변경 비밀번호)
	- 수정성공 : 성공 메세지 출력 후
			   (로그아웃 되었습니다. 변경된 비밀번호를 다시 로그인 하세요)
			   opener 페이지 info.jsp로 이동. 현재페이지 닫기
	- 수정실패 : 수정실패 메세지 출력 후 현재 페이지 닫기
--%>
<%	// 1. 
	String pass = request.getParameter("pass");
	String chgpass = request.getParameter("chgpass");
	// 2.
	String login = (String) session.getAttribute("login");
	boolean opener = true, selfclose = true;
	String msg = null, url = null;
	
	if(login == null){ // 로그아웃 상태
		msg = "로그인 하세요";
		url = "loginForm.jsp";
		opener = true;
		selfclose = true;
		
	} else { // 로그인 상태
		MemberDao dao = new MemberDao();
		Member dbMem = dao.selectOne(login);
		// 비밀번호 검증
		if(pass.equals(dbMem.getPass())) {
			// 변경 성공
			if(dao.updatePass(login, chgpass)){ 
				msg = "비밀번호 변경성공";
				url = "info.jsp?id=" + login;
				opener = true;
				selfclose = true;
			} else { // 변경실패
				msg = "비밀번호 변경실패";
				opener = false;
				selfclose = true;
			}
		} else { // 비밀번호 오류
			msg = "비밀번호가 틀립니다.";
			url = "passwordForm.jsp";
			opener = false;
			selfclose = false;
		}
	} 
%>
<script type="text/javascript">
	alert("<%= msg %>");
	
	if(<%= opener %> && <%= selfclose %>){
		opener.location.href= "<%=url%>";
		self.close();
	} else if (!<%= opener %> && !<%= selfclose %>){
		location.href = "<%= url %>";
	} else if(<%= selfclose %>) {
		self.close();
	}
</script>

<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	</head>
	<body>
	
	</body>
</html>