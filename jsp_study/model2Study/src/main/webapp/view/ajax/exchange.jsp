<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h3 class="text-center">수출입 은행<br>${date}</h3> <%-- 조회 기준일 --%>
<table class="table">
	<tr>
		<th>통화</th>
		<th>기준율</th>
		<th>받을실때</th>
		<th>보내실때</th>
	</tr>
	<%--  0:통화코드, 1:통화명, 2:받으실때, 3:보내실때 --%>
	<c:forEach items="${list}" var="tdlist">
		<tr>
			<td>${tdlist[0]}<br>${tdlist[1]}</td>
			<td>${tdlist[4]}</td>
			<td>${tdlist[2]}</td>
			<td>${tdlist[3]}</td>
		</tr>
	</c:forEach>
</table>