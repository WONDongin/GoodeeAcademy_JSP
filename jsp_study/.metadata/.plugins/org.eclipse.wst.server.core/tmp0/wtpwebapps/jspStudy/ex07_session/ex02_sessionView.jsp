<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>장바구니 보기</title>
	</head>
	<body>
		<%
		List<String> cart = (List<String>)session.getAttribute("cart");
		if(cart == null || cart.size() == 0){
		%>
		<h3>장바구니에 상품이 없습니다.</h3>
		<% } else { %>
		<h3>장바구니 상품</h3>
		<% for(String p : cart) { %>
		<h4><%= p %></h4>
		<% } } 
		
			// 장바구니 상품 제거
			session.removeAttribute("cart");
		%>
	</body>
</html>