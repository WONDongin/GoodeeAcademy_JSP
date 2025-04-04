<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<h4>ex03_include2.jsp 페이지 입니다.</h4>
<p>
	ex03_include.jsp 페이지에 포함되는 페이지 입니다.
	include 지시어와 다른 점은 하나의 서블릿에 생성되지 않기 때문에 변수가 공유되지 않습니다.<br/>
	<%
		// System.out.println(msg);
	%>
	<br/>test 파라미터 :  <%= request.getParameter("test2") %>
</p>