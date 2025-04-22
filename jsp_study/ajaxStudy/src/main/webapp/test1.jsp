<%@page import="java.util.Arrays"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	StringBuilder sb = new StringBuilder();
	
	for(int i = 1; i <= 10; i++){
		int num = (int)(Math.random()*100) + 1;
		sb.append(num);
		if(i != 10) sb.append(",");
	}
%>
<%= sb %>