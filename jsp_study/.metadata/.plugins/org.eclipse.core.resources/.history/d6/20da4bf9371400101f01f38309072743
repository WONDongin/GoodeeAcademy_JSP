<%@page import="model.member.MemberDao"%>
<%@page import="model.member.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- /webapp/member/info.jsp
   1. 로그인 상태 검증 
      로그아웃상태 : 로그인하세요 메세지, loginForm.jsp 페이지로 이동
      로그인 상태 :
         - 다른 id를 조회할 수 없음. 단 관리자는 다른 id 조회가능함
           내정보만 조회 가능합니다. 메세지 출력. main.jsp 페이지 이동
   2. id 파라미터 조회
   3. id에 해당하는 레코드를 조회하여 Member 객체에 저장(mem)   
--%>   
<%
   String login = (String)session.getAttribute("login");  //로그인 정보
   String id = request.getParameter("id");                //파라미터 정보
   if(login == null) { //=> 로그 아웃상태
%>
<script>
   alert("로그인이 필요합니다.");
   location.href="loginForm.jsp";
</script> 
<% } else if(!id.equals(login) && !login.equals("admin")) { %>
<%-- id : 파라미터값, login : session에 등록된 login id 값 --%>  
<script>
   alert("내정보만 조회 가능합니다.");
   location.href="main.jsp";
</script> 
<% }  else {  //로그인 된경우. 자기정보 또는 관리자인 경우 %>
<%
   Member mem = new MemberDao().selectOne(id);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원정보</title>
<link rel="stylesheet" href="../css/main.css" >
</head>
<body>
<table><caption>회원정보</caption>
<tr><td rowspan="6" width="30%">
  <img src="picture/<%=mem.getPicture() %>" width="200" height="210"></td>
  <th width="20%">아이디</th><td><%=mem.getId() %></td>
  <tr><th>이름</th><td><%=mem.getName() %></td>
  <tr><th>성별</th><td><%=mem.getGender()==1?"남":"여" %></td>
  <tr><th>전화</th><td><%=mem.getTel() %></td>
  <tr><th>이메일</th><td><%=mem.getEmail() %></td>
  </tr>
  <tr><td colspan="2">
     <a href="updateForm.jsp?id=<%=mem.getId()%>">수정</a>
     <% if (!id.equals("admin")) {%>
     <a href="deleteForm.jsp?id=<%=mem.getId()%>">탈퇴</a>
     <% } %>
  </td></tr>
</table>
</body>
</html>
<% } %>