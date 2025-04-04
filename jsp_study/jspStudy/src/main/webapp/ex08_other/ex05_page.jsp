<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>page 객체</title>
</head>
<body>
<%!
   String msg = "test";
%>
<%= msg %><br>
<%= this.msg %><br>
<%
   if(page == this) {
	   out.println("page 객체와 this 객체는 같은 객체임");
   }
%>
</body>
</html>