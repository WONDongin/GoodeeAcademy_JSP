<%@page import="java.util.Enumeration"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- /webapp/ex08_other/ex04_config.jsp --%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>config 내장 객체 연습</h2>
<table border="1">
  <tr><td>초기파라미터이름</td>
      <td>초기파라미터값</td></tr>
<%
   Enumeration e = config.getInitParameterNames();
	while(e.hasMoreElements()) {
		String name = (String)e.nextElement();
%>
<tr><td><%=name %></td>
    <td><%=config.getInitParameter(name) %></td>
</tr>
<%	} %>
</table>

</body>
</html>