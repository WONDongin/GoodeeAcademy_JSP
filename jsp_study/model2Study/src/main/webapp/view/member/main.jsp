<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>회원관리</title>
    <!-- Bootstrap 4 CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        .container {background-color: #f2f2f2; border-radius: 15px;padding: 30px;}
        h3 {margin-bottom: 20px;}
        a {color: #ffc107; text-decoration: none;}
        a:hover {color: #fff;text-decoration: underline;}
        .btn-custom {background-color: #343a40; border: none;color: white; width: 100%; margin-bottom: 10px;}
        .btn-custom:hover { background-color: #495057;}
    </style>
</head>
<body>
    <div class="container text-center">
        <h3><strong>${sessionScope.login}</strong>님이 로그인 하셨습니다.</h3>
        <a class="btn btn-custom" href="logout">로그아웃</a>
        <a class="btn btn-custom" href="info?id=${sessionScope.login}">회원 정보 보기</a>

        <c:if test="${sessionScope.login == 'admin'}">
            <a class="btn btn-custom" href="list">회원 목록 보기</a>
        </c:if>
    </div>
</body>
</html>
