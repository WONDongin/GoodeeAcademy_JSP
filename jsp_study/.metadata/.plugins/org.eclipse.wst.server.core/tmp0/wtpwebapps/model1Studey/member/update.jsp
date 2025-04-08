<%@page import="model.member.MemberDao"%>
<%@page import="model.member.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- /webapp/member/update.jsp 
   1. 모든 파라미터를 Member 객체에 저장하기
   2. 입력된 비밀번호와 db에 저장된 비밀번호 비교.
       관리자로 로그인 한 경우 관리자비밀번호로 비교
       본인 정보 수정시 본인의 비밀번호로 비교
       불일치 : '비밀번호 오류' 메세지 출력후 updateForm.jsp 페이지 이동 
   3. Member 객체의 내용으로 db를 수정하기 : boolean MemberDao.update(Member)
       - 성공 : 회원정보 수정 완료 메세지 출력후 info.jsp로 페이지 이동
       - 실패 : 회원정보 수정 실패 메세지 출력 후 updateForm.jsp 페이지 이동     
--%>    
<%
  //1 모든 파라미터를 Member 객체에 저장하기
   request.setCharacterEncoding("utf-8");
   Member mem = new Member();
   mem.setId(request.getParameter("id"));
   mem.setPass(request.getParameter("pass"));
   mem.setName(request.getParameter("name"));
   mem.setGender(Integer.parseInt(request.getParameter("gender")));
   mem.setTel(request.getParameter("tel"));
   mem.setEmail(request.getParameter("email"));
   mem.setPicture(request.getParameter("picture"));
   //2 비밀번호를 위한 db의 데이터 조회. : login 정보로 조회하기
   String login = (String)session.getAttribute("login");
   MemberDao dao = new MemberDao();
   Member dbMem = dao.selectOne(login);
   String msg = "비밀번호 오류";
   String url = "updateForm.jsp?id=" + mem.getId();
   //mem.getPass() : 입력한 비밀번호
   //dbMem.getPass() : 로그인한 아이디의 비밀번호
   if(mem.getPass().equals(dbMem.getPass()))  {
	  if(dao.update(mem)) { // 회원 정보 수정 성공
		  msg = "수정 성공";
		  url = "info.jsp?id="+mem.getId();
	  } else {// 회원 정보 수정 실패
		  msg = "수정 실패";
	  }
   }
%>
<script>
  alert("<%=msg%>")
  location.href="<%=url%>"
</script>