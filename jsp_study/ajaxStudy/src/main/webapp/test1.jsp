<%@page import="java.util.Arrays"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// StringBuilder
	StringBuilder sb = new StringBuilder();
	
	for(int i = 1; i <= 10; i++){
		int num = (int) (Math.random()*100) + 1;
		
		sb.append(num);
		if(i != 10) sb.append(",");
	}
	
	// 배열
	String[] arr = new String[10];
	
	for(int i = 0; i < arr.length; i++){
		int num = (int) (Math.random()*100) + 1;
		arr[i] = String.valueOf(num) ;
	}
	String join = String.join(",", arr);
	
%>
<%= sb %>