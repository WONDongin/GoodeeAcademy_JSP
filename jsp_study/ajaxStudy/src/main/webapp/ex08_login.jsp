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
    select * from member where id=? and pass=?
    <sql:param>${param.id}</sql:param>
    <sql:param>${param.pass}</sql:param>
</sql:query>

<%-- rs.rows : 조회된 결과 레코드들 배열의 형태 --%>
<c:if test="${!empty rs.rows}"> <%-- 조회된 내용 존재 --%>
    <h1>반갑습니다 ${rs.rows[0].name}님</h1>
</c:if>
<c:if test="${empty rs.rows}">
    <h1><font color="red">아이디 또는 비밀번호가 틀립니다.</font></h1>
</c:if>