<%@page import="java.lang.annotation.Documented"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	int num = Integer.parseInt(request.getParameter("num"));
	int val = Integer.parseInt(request.getParameter("val"));
	int sum = 0;
	
	switch(val){
	case 0 : 
		for(int i = 0; i <= num; i++){
			sum += i;
		}
		break;
	case 1 : 
		
		for(int i = 0; i <= num; i++){
			if(i % 2 == 0){
				sum += i;
			}
		}
		break;
	default : 
		for(int i = 0; i <= num; i++){
			if(i % 2 != 0){
				sum += i;
			}
		}
	}

%>
<h2><%= sum %></h2>