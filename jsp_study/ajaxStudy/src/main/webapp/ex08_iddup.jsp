<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix ="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- database 접근 --%> 
<%@ taglib prefix ="sql" uri="http://java.sun.com/jsp/jstl/sql" %>

<%-- database Connection 객체 --%> 
<sql:setDataSource 
	var="conn" driver="org.mariadb.jdbc.Driver"
    url="jdbc:mariadb://localhost:3306/gdjdb"
    user="gduser" password="1234"/>
    
<%-- SQL 문장실행 --%> 
<%-- rs : select 구문 실행 결과. 레코드 정보 --%>
<sql:query var="rs" dataSource="${conn}">
    select * from member where id=?
    <sql:param>${param.id}</sql:param>
</sql:query>

<%-- rs.rows : 조회된 결과 레코드들 배열의 형태 --%>
<c:if test="${!empty rs.rows}"> <%-- 존재하는 아이디 --%>
    <h1 id="result" class="find" style="color:red;">${param.id} : 존재하는 아이디 입니다.</h1>
</c:if>
<c:if test="${empty rs.rows}"> <%-- 사용가능 아이디 --%>
    <h1 id="result" class="notfind">${param.id} : 회원가입이 가능한 아이디 입니다.</h1>
</c:if>